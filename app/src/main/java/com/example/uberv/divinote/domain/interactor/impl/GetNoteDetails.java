package com.example.uberv.divinote.domain.interactor.impl;

import com.example.uberv.divinote.domain.executor.PostExecutionThread;
import com.example.uberv.divinote.domain.executor.ThreadExecutor;
import com.example.uberv.divinote.domain.interactor.base.UseCase;
import com.example.uberv.divinote.domain.models.Note;
import com.example.uberv.divinote.domain.repository.NoteRepository;
import com.google.common.base.Preconditions;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetNoteDetails extends UseCase<Note, GetNoteDetails.Params> {

    private final NoteRepository mNoteRepository;

    @Inject
    public GetNoteDetails(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, NoteRepository noteRepository) {
        super(threadExecutor, postExecutionThread);
        mNoteRepository = noteRepository;
    }

    @Override
    public Observable<Note> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return this.mNoteRepository.note(params.mNoteId);
    }

    /**
     * Wraps parameters required by {@link GetNoteDetails} UseCase
     */
    public static final class Params {
        private final long mNoteId;

        public Params(long noteId) {
            mNoteId = noteId;
        }

        public static Params forNote(long noteId) {
            return new Params(noteId);
        }
    }
}
