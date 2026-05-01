package com.theelixirist.f112813.game.templates.loaders;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.templates.UpgradeTemplate;

import java.util.List;

public class UpgradeTemplateLoader {
    public static List<UpgradeTemplate> load(Context context) {
        return BaseLoader.load(context, "upgrades.json",
                new TypeToken<List<UpgradeTemplate>>() {
                }.getType());
    }
}
