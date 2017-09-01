package com.example.uberv.divinote.storage.models.mappers;

import com.example.uberv.divinote.domain.models.Note;
import com.example.uberv.divinote.storage.models.NoteEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Mapper class used to transform {@Link NoteEntity} to
 */
public class NoteEntityDataMapper {

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
