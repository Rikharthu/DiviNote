package com.example.uberv.divinote.data.cache;

import android.content.Context;

import com.example.uberv.divinote.data.cache.serializer.Serializer;
import com.example.uberv.divinote.data.exceptions.NoteNotFoundException;
import com.example.uberv.divinote.data.models.NoteEntity;
import com.example.uberv.divinote.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * {@link NoteCache} implementation.
 */
@Singleton
public class NoteCacheImpl implements NoteCache {
    private static final String SETTINGS_FILE_NAME = "com.fernandocejas.android10.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "Note_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final Serializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link NoteCacheImpl}.
     *
     * @param context     A
     * @param serializer  {@link Serializer} for object serialization.
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    NoteCacheImpl(Context context, Serializer serializer,
                  FileManager fileManager, ThreadExecutor executor) {
        if (context == null || serializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = serializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<NoteEntity> get(final long NoteId) {
        return Observable.create(new ObservableOnSubscribe<NoteEntity>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<NoteEntity> emitter) throws Exception {
                final File NoteEntityFile = NoteCacheImpl.this.buildFile(NoteId);
                final String fileContent = NoteCacheImpl.this.fileManager.readFileContent(NoteEntityFile);
                final NoteEntity NoteEntity =
                        NoteCacheImpl.this.serializer.deserialize(fileContent, NoteEntity.class);

                if (NoteEntity != null) {
                    emitter.onNext(NoteEntity);
                    emitter.onComplete();
                } else {
                    emitter.onError(new NoteNotFoundException());
                }
            }
        });
    }

    @Override
    public void put(NoteEntity NoteEntity) {
        if (NoteEntity != null) {
            final File NoteEntityFile = this.buildFile(NoteEntity.getId());
            if (!isCached(NoteEntity.getId())) {
                final String jsonString = this.serializer.serialize(NoteEntity, NoteEntity.class);
                this.executeAsynchronously(new CacheWriter(this.fileManager, NoteEntityFile, jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(long NoteId) {
        final File NoteEntityFile = this.buildFile(NoteId);
        return this.fileManager.exists(NoteEntityFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @param NoteId The id Note to build the file.
     * @return A valid file.
     */
    private File buildFile(long NoteId) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(NoteId);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
