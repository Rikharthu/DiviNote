package com.example.uberv.divinote.data.repository.datastore;

import com.example.uberv.divinote.data.models.NoteEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface NoteDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link NoteEntity}.
     */
    Observable<List<NoteEntity>> noteEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link NoteEntity} by its id.
     *
     * @param userId The id to retrieve user data.
     */
    Observable<NoteEntity> noteEntityDetails(final int userId);
}
