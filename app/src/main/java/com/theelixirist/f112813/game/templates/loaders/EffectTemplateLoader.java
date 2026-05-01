package com.theelixirist.f112813.game.templates.loaders;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.templates.EffectTemplate;

import java.util.List;

public class EffectTemplateLoader {
    public static List<EffectTemplate> load(Context context) {
        return BaseLoader.load(context, "effects.json",
                new TypeToken<List<EffectTemplate>>() {
                }.getType());
    }
}
