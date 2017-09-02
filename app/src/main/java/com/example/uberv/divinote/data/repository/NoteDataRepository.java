package com.example.uberv.divinote.data.repository;

import com.example.uberv.divinote.data.database.DiviNoteDatabaseHelper;
import com.example.uberv.divinote.data.models.NoteEntity;
import com.example.uberv.divinote.data.models.mappers.NoteEntityDataMapper;
import com.example.uberv.divinote.data.repository.datastore.LocalNoteDataStore;
import com.example.uberv.divinote.data.repository.datastore.NoteDataStore;
import com.example.uberv.divinote.data.repository.datastore.NoteDataStoreFactory;
import com.example.uberv.divinote.domain.models.Note;
import com.example.uberv.divinote.domain.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * {@link NoteRepository} for retrieving {@link Note} data
 */
public class NoteDataRepository implements NoteRepository {

    private final NoteDataStoreFactory mNoteDataStoreFactory;
    private final NoteEntityDataMapper mNoteEntityDataMapper;
    private final DiviNoteDatabaseHelper mHelper;

    /**
     * Constructs a {@link NoteRepository}.
     *
     * @param noteDataStoreFactory A factory to construct different data source implementations.
     * @param noteEntityDataMapper {@link NoteEntityDataMapper}.
     */
    @Inject
    public NoteDataRepository(NoteDataStoreFactory noteDataStoreFactory, NoteEntityDataMapper noteEntityDataMapper, DiviNoteDatabaseHelper helper) {
        mNoteDataStoreFactory = noteDataStoreFactory;
        mNoteEntityDataMapper = noteEntityDataMapper;
        mHelper = helper;
    }

    @Override
    public Observable<List<Note>> notes() {
        //we always get all users from the cloud
//        final NoteDataStore userDataStore = mNoteDataStoreFactory.createCloudDataStore();
        // TODO for debug
        NoteDataStore noteDataStore = new LocalNoteDataStore(mHelper);
        return noteDataStore.noteEntityList().map(new Function<List<NoteEntity>, List<Note>>() {
            @Override
            public List<Note> apply(@NonNull List<NoteEntity> noteEntities) throws Exception {
                return mNoteEntityDataMapper.transform(noteEntities);
            }
        });
    }

    @Override
    public Observable<Note> note(long noteId) {
        // TODO
        return null;
    }

    @Override
    public void insert(Note note) {
        // TODO
    }

    @Override
    public void delete(Note note) {
        // TODO
    }

    @Override
    public void update(Note note) {
        // TODO
    }
}
