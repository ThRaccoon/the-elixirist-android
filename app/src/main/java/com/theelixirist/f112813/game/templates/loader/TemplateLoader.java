package com.theelixirist.f112813.game.templates.loader;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.math.BigDouble;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class TemplateLoader {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(
                    BigDouble.class,
                    (JsonDeserializer<BigDouble>) (json, type, context) ->
                            new BigDouble(json.getAsString())
            ).create();

    public static <T> List<T> load(Context context, String fileName, Class<T> clazz) {
        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        try {
            InputStream is = context.getAssets().open("templates/" + fileName);
            InputStreamReader reader = new InputStreamReader(is);
            return GSON.fromJson(reader, type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + fileName, e);
        }
    }
}
