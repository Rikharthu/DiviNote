package com.example.uberv.divinote.data.cache;

import com.example.uberv.divinote.data.models.NoteEntity;

import io.reactivex.Observable;

/**
 * An interface representing a note Cache.
 */
public interface NoteCache {

    /**
     * Gets an {@link Observable} which will emit a {@link NoteEntity}.
     *
     * @param noteId The user id to retrieve data.
     */
    Observable<NoteEntity> get(final long noteId);

    /**
     * Puts and element into the cache.
     *
     * @param userEntity Element to insert in the cache.
     */
    void put(NoteEntity userEntity);

    /**
     * Checks if an element (Note) exists in the cache.
     *
     * @param userId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final long userId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
