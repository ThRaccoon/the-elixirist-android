package com.theelixirist.f112813.game.definitions.parsers;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.theelixirist.f112813.game.definitions.GeneratorDefinition;

import java.util.List;

public class GeneratorDefinitionParser {
    public static List<GeneratorDefinition> parse(Context context) {
        return BaseDefinitionParser.parse(context, "generatorDefinitions.json",
                new TypeToken<List<GeneratorDefinition>>() {
                }.getType());
    }
}
