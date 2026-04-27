package com.theelixirist.f112813.game.registries;

import android.content.Context;

import com.theelixirist.f112813.game.definitions.BuffDefinition;
import com.theelixirist.f112813.game.definitions.CatalystDefinition;
import com.theelixirist.f112813.game.definitions.GeneratorDefinition;
import com.theelixirist.f112813.game.definitions.UpgradeDefinition;
import com.theelixirist.f112813.game.definitions.parsers.BuffDefinitionParser;
import com.theelixirist.f112813.game.definitions.parsers.CatalystDefinitionParser;
import com.theelixirist.f112813.game.definitions.parsers.GeneratorDefinitionParser;
import com.theelixirist.f112813.game.definitions.parsers.UpgradeDefinitionParser;

import java.util.HashMap;

public class DefinitionRegistry {
    private final HashMap<Integer, GeneratorDefinition> generatorDefinitions = new HashMap<>();
    private final HashMap<Integer, UpgradeDefinition> upgradeDefinitions = new HashMap<>();
    private final HashMap<Integer, CatalystDefinition> catalystDefinitions = new HashMap<>();
    private final HashMap<Integer, BuffDefinition> buffDefinitions = new HashMap<>();

    public DefinitionRegistry(Context context) {
        for (GeneratorDefinition definition : GeneratorDefinitionParser.parse(context)) {
            generatorDefinitions.put(definition.id, definition);
        }

        for (UpgradeDefinition definition : UpgradeDefinitionParser.parse(context)) {
            upgradeDefinitions.put(definition.id, definition);
        }

        for (CatalystDefinition definition : CatalystDefinitionParser.parse(context)) {
            catalystDefinitions.put(definition.id, definition);
        }

        for (BuffDefinition definition : BuffDefinitionParser.parse(context)) {
            buffDefinitions.put(definition.id, definition);
        }
    }

    public HashMap<Integer, GeneratorDefinition> getGeneratorDefinitions() {
        return generatorDefinitions;
    }

    public HashMap<Integer, UpgradeDefinition> getUpgradeDefinitions() {
        return upgradeDefinitions;
    }

    public HashMap<Integer, CatalystDefinition> getCatalystDefinitions() {
        return catalystDefinitions;
    }

    public HashMap<Integer, BuffDefinition> getBuffDefinitions() {
        return buffDefinitions;
    }

    public GeneratorDefinition getGeneratorDefinition(int id) {
        return generatorDefinitions.get(id);
    }

    public UpgradeDefinition getUpgradeDefinition(int id) {
        return upgradeDefinitions.get(id);
    }

    public CatalystDefinition getCatalystDefinition(int id) {
        return catalystDefinitions.get(id);
    }

    public BuffDefinition getBuffDefinition(int id) {
        return buffDefinitions.get(id);
    }
}
