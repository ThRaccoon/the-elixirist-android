package com.theelixirist.f112813.game.data.loaders;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.data.definitions.BuffDefinition;

import java.util.List;

public class BuffLoader {
    public static List<BuffDefinition> load(Context context) {
        return BaseLoader.load(context, "buffs.json",
                new TypeToken<List<BuffDefinition>>() {
                }.getType());
    }
}
