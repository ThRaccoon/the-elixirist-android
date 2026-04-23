package com.theelixirist.f112813.game.data.loaders;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.data.definitions.UpgradeDefinition;

import java.util.List;

public class UpgradeLoader {
    public static List<UpgradeDefinition> load(Context context) {
        return BaseLoader.load(context, "upgrades.json",
                new TypeToken<List<UpgradeDefinition>>() {
                }.getType());
    }
}
