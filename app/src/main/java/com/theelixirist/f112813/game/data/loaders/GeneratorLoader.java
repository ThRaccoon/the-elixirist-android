package com.theelixirist.f112813.game.data.loaders;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.data.definitions.GeneratorDefinition;

import java.util.List;

public class GeneratorLoader {
    public static List<GeneratorDefinition> load(Context context) {
        return BaseLoader.load(context, "generators.json",
                new TypeToken<List<GeneratorDefinition>>() {
                }.getType());
    }
}
