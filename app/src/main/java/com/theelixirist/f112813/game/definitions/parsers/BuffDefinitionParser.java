package com.theelixirist.f112813.game.definitions.parsers;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.definitions.BuffDefinition;

import java.util.List;

public class BuffDefinitionParser {
    public static List<BuffDefinition> parse(Context context) {
        return BaseDefinitionParser.parse(context, "buffDefinitions.json",
                new TypeToken<List<BuffDefinition>>() {
                }.getType());
    }
}
