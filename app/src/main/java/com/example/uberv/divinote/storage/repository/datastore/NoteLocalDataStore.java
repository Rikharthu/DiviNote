package com.example.uberv.divinote.storage.repository.datastore;

import com.example.uberv.divinote.domain.models.Note;
import com.example.uberv.divinote.storage.models.NoteEntity;

import java.util.List;

import io.reactivex.Observable;

public class NoteLocalDataStore implements NoteDataStore {
    @Override
    public Observable<List<NoteEntity>> noteEntityList() {
        return null;
    }

    @Override
    public Observable<NoteEntity> noteEntityDetails(int userId) {
        return null;
    }
}
