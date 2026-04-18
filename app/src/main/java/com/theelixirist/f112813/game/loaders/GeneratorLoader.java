package com.theelixirist.f112813.game.loaders;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class GeneratorLoader {
    public static List<GeneratorDefinition> load(Context context) {
        try {
            InputStream is = context.getAssets().open("data/generators.json");
            InputStreamReader reader = new InputStreamReader(is);
            Type listType = new TypeToken<List<GeneratorDefinition>>() {
            }.getType();
            return new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load generators.json", e);
        }
    }
}
