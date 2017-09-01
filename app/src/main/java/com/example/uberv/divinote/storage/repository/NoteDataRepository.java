package com.example.uberv.divinote.storage.repository;

import com.example.uberv.divinote.domain.models.Note;
import com.example.uberv.divinote.domain.repository.NoteRepository;
import com.example.uberv.divinote.storage.models.mappers.NoteEntityDataMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * {@link NoteRepository} for retrieving {@link Note} data
 */
public class NoteDataRepository implements NoteRepository {

//    private final NoteDataStoreFactory mNoteDataStoreFactory;
//    private final NoteEntityDataMapper mNoteEntityDataMapper;
//
//    /**
//     * Constructs a {@link NoteRepository}.
//     *
//     * @param noteDataStoreFactory A factory to construct different data source implementations.
//     * @param noteEntityDataMapper {@link NoteEntityDataMapper}.
//     */
//    @Inject
//    public NoteDataRepository(NoteDataStoreFactory noteDataStoreFactory, NoteEntityDataMapper noteEntityDataMapper) {
//        mNoteDataStoreFactory = noteDataStoreFactory;
//        mNoteEntityDataMapper = noteEntityDataMapper;
//    }

    @Override
    public Observable<List<Note>> notes() {
        // TODO
        return null;
    }

    @Override
    public Observable<Note> note(long noteId) {
        // TODO
        return null;
    }

    @Override
    public void insert(Note note) {

    }

    @Override
    public void delete(Note note) {

    }

    @Override
    public void update(Note note) {

    }
}
