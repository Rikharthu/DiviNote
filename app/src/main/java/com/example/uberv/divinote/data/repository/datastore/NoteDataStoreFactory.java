package com.example.uberv.divinote.data.repository.datastore;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.uberv.divinote.data.cache.NoteCache;
import com.example.uberv.divinote.data.database.DiviNoteDatabaseHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link NoteDataStore}
 */
@Singleton
public class NoteDataStoreFactory {

    private final Context mContext;
    private final NoteCache mNoteCache;

    @Inject
    public NoteDataStoreFactory(@NonNull Context context, @NonNull NoteCache noteCache) {
        mContext = context;
        mNoteCache = noteCache;
    }

    /**
     * Create {@link NoteDataStore} from a note id.
     */
    public NoteDataStore create(int userId) {
        NoteDataStore userDataStore;

        if (!mNoteCache.isExpired() && mNoteCache.isCached(userId)) {
            userDataStore = new DiskNoteDataStore(mNoteCache);
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    /**
     * Create {@link NoteDataStore} to retrieve data from the Cloud.
     */
    public NoteDataStore createCloudDataStore() {
//        final NoteEntityJsonMapper userEntityJsonMapper = new NoteEntityJsonMapper();
//        final RestApi restApi = new RestApiImpl(mContext, userEntityJsonMapper);
//
//        return new CloudUserDataStore(restApi, mNoteCache);

        // TODO for debug
        return new LocalNoteDataStore(new DiviNoteDatabaseHelper(mContext));
    }
}
