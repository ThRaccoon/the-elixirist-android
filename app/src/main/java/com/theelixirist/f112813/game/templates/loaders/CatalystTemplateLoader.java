package com.theelixirist.f112813.game.templates.loaders;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.templates.CatalystTemplate;

import java.util.List;

public class CatalystTemplateLoader {
    public static List<CatalystTemplate> load(Context context) {
        return BaseLoader.load(context, "catalysts.json",
                new TypeToken<List<CatalystTemplate>>() {
                }.getType());
    }
}
