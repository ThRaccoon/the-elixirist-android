package com.theelixirist.f112813.game.definitions.parsers;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.definitions.CatalystDefinition;

import java.util.List;

public class CatalystDefinitionParser {
    public static List<CatalystDefinition> parse(Context context) {
        return BaseDefinitionParser.parse(context, "catalystDefinitions.json",
                new TypeToken<List<CatalystDefinition>>() {
                }.getType());
    }
}
