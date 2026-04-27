package com.theelixirist.f112813.game.definitions.parsers;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.definitions.UpgradeDefinition;

import java.util.List;

public class UpgradeDefinitionParser {
    public static List<UpgradeDefinition> parse(Context context) {
        return BaseDefinitionParser.parse(context, "upgradeDefinitions.json",
                new TypeToken<List<UpgradeDefinition>>() {
                }.getType());
    }
}
