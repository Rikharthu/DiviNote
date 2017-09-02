package com.example.uberv.divinote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.uberv.divinote.data.repository.NoteDataRepository;
import com.example.uberv.divinote.data.repository.datastore.LocalNoteDataStore;
import com.example.uberv.divinote.domain.models.Note;
import com.example.uberv.divinote.data.database.DiviNoteDatabaseHelper;
import com.example.uberv.divinote.data.models.NoteEntity;
import com.example.uberv.divinote.data.models.mappers.NoteEntityDataMapper;
import com.example.uberv.divinote.domain.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DiviNoteApp)getApplication()).getApplicationComponent().inject(this);

        mNoteRepository.notes().subscribe(new Consumer<List<Note>>() {
            @Override
            public void accept(List<Note> notes) throws Exception {

                Timber.d(notes.toString());
            }
        });
//        DiviNoteDatabaseHelper helper = new DiviNoteDatabaseHelper(this);
//        Timber.d(helper.toString());
//        LocalNoteDataStore dataStore = new LocalNoteDataStore(helper);
//        dataStore.noteEntityList().subscribe(new Consumer<List<NoteEntity>>() {
//            @Override
//            public void accept(List<NoteEntity> noteEntities) throws Exception {
//                List<Note> notes = new NoteEntityDataMapper().transform(noteEntities);
//
//                Timber.d(notes.toString());
//            }
//        });
    }
}
