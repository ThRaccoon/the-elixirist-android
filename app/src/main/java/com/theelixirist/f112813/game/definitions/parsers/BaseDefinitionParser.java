package com.theelixirist.f112813.game.definitions.parsers;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.theelixirist.f112813.game.math.BigDouble;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDefinitionParser {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(BigDouble.class, (JsonDeserializer<BigDouble>) (json, type, context) ->
                    new BigDouble(json.getAsString()))
            .create();

    public static <T> List<T> parse(Context context, String fileName, Type type) {
        try {
            InputStream is = context.getAssets().open("definitions/" + fileName);
            InputStreamReader reader = new InputStreamReader(is);
            return GSON.fromJson(reader, type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + fileName, e);
        }
    }
}
