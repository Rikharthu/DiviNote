package com.example.uberv.divinote.domain.repository;

import com.example.uberv.divinote.domain.models.Note;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting a
 * {@link Note} related data
 */
public interface NoteRepository {
    /**
     * Get an {@link io.reactivex.Observable} which will emit a List of {@link Note}
     */
    Observable<List<Note>> notes();

    /**
     * Get an {@link Observable} which will emit a {@link Note}.
     *
     * @param noteId The user id used to retrieve user data.
     */
    Observable<Note> note(long noteId);

    void insert(Note note);

    void delete(Note note);

    void update(Note note);
}
