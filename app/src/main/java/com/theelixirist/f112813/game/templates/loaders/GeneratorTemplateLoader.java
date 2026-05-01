package com.theelixirist.f112813.game.templates.loaders;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.templates.GeneratorTemplate;

import java.util.List;

public class GeneratorTemplateLoader {
    public static List<GeneratorTemplate> load(Context context) {
        return BaseLoader.load(context, "generators.json",
                new TypeToken<List<GeneratorTemplate>>() {
                }.getType());
    }
}
