package com.example.uberv.divinote.data.models.mappers;

import com.example.uberv.divinote.data.models.NoteEntity;
import com.example.uberv.divinote.domain.models.Note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@Link NoteEntity} to
 */
public class NoteEntityDataMapper {

    @Inject
    public NoteEntityDataMapper() {
    }

    /**
     * Transform a {@link NoteEntity} into a {@link Note}
     *
     * @param noteEntity Object to be transformed
     * @return {@link Note} if valid {@link NoteEntity} otherwise null
     */
    public Note transform(NoteEntity noteEntity) {
        Note note = null;
        if (noteEntity != null) {
            note = new Note(noteEntity.getId());
            note.setCreatedAt(new Date(noteEntity.getCreatedAt()));
            note.setRemindAt(new Date(noteEntity.getRemindAt()));
            note.setUpdatedAt(new Date(noteEntity.getUpdatedAt()));
            note.setTitle(noteEntity.getTitle());
            note.setDescription(noteEntity.getDescription());
            String entityStatus = noteEntity.getStatus();
            if (entityStatus != null) {
                switch (entityStatus) {
                    case NoteEntity.STATUS_COMPLETED:
                        note.setStatus(Note.Status.COMPLETED);
                        break;
                    case NoteEntity.STATUS_FINISHED:
                        note.setStatus(Note.Status.FINISHED);
                        break;
                    case NoteEntity.STATUS_TODO:
                        note.setStatus(Note.Status.TODO);
                        break;
                }
            } else {
            }
        }
        return note;
    }

    public List<Note> transform(Collection<NoteEntity> noteEntityCollection) {
        final List<Note> notes = new ArrayList<>();
        for (NoteEntity noteEntity : noteEntityCollection) {
            final Note note = transform(noteEntity);
            if (note != null) {
                notes.add(note);
            }
        }
        return notes;
    }
}
