package com.example.uberv.divinote.domain.interactor.impl;

import com.example.uberv.divinote.domain.executor.PostExecutionThread;
import com.example.uberv.divinote.domain.executor.ThreadExecutor;
import com.example.uberv.divinote.domain.interactor.base.UseCase;
import com.example.uberv.divinote.domain.models.Note;
import com.example.uberv.divinote.domain.repository.NoteRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Note}.
 */
public class GetNoteList extends UseCase<List<Note>, Void> {

    private final NoteRepository mNoteRepository;

    public GetNoteList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, NoteRepository noteRepository) {
        super(threadExecutor, postExecutionThread);
        mNoteRepository = noteRepository;
    }

    @Override
    public Observable<List<Note>> buildUseCaseObservable(Void unused) {
        return mNoteRepository.notes();
    }
}
