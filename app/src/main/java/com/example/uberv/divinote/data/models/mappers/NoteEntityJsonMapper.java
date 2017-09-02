package com.example.uberv.divinote.data.models.mappers;

import com.example.uberv.divinote.data.models.NoteEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class NoteEntityJsonMapper {

    private final Gson mGson;

    @Inject
    public NoteEntityJsonMapper(Gson gson) {
        mGson = gson;
    }

    /**
     * Transform from valid json string to {@link NoteEntity}.
     *
     * @param userJsonResponse A json representing a user profile.
     * @return {@link NoteEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public NoteEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
        final Type userEntityType = new TypeToken<NoteEntity>() {
        }.getType();
        return mGson.fromJson(userJsonResponse, userEntityType);
    }

    /**
     * Transform from valid json string to List of {@link NoteEntity}.
     *
     * @param userListJsonResponse A json representing a collection of users.
     * @return List of {@link NoteEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<NoteEntity> transformUserEntityCollection(String userListJsonResponse)
            throws JsonSyntaxException {
        final Type listOfUserEntityType = new TypeToken<List<NoteEntity>>() {
        }.getType();
        return mGson.fromJson(userListJsonResponse, listOfUserEntityType);
    }
}
