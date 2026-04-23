package com.theelixirist.f112813.game.data.loaders;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.data.definitions.CatalystDefinition;

import java.util.List;

public class CatalystLoader {
    public static List<CatalystDefinition> load(Context context) {
        return BaseLoader.load(context, "catalysts.json",
                new TypeToken<List<CatalystDefinition>>() {
                }.getType());
    }
}
