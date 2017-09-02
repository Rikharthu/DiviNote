package com.example.uberv.divinote.data.repository.datastore;

import com.example.uberv.divinote.data.cache.NoteCache;
import com.example.uberv.divinote.data.models.NoteEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link NoteDataStore} implementation based on a file system data store
 */
public class DiskNoteDataStore implements NoteDataStore {
    private final NoteCache mNoteCache;

    /**
     * Construct a {@link NoteDataStore} based file system data store.
     *
     * @param userCache A {@link NoteCache} to cache data retrieved from the api.
     */
    DiskNoteDataStore(NoteCache userCache) {
        mNoteCache = userCache;
    }

    @Override
    public Observable<List<NoteEntity>> noteEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of users.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<NoteEntity> noteEntityDetails(final int userId) {
        return mNoteCache.get(userId);
    }
}
