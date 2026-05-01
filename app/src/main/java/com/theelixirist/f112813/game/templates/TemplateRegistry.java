package com.theelixirist.f112813.game.templates;

import android.content.Context;

import com.theelixirist.f112813.game.templates.loaders.EffectTemplateLoader;
import com.theelixirist.f112813.game.templates.loaders.CatalystTemplateLoader;
import com.theelixirist.f112813.game.templates.loaders.GeneratorTemplateLoader;
import com.theelixirist.f112813.game.templates.loaders.UpgradeTemplateLoader;

import java.util.HashMap;

public class TemplateRegistry {
    private final HashMap<Integer, GeneratorTemplate> generatorDefinitions = new HashMap<>();
    private final HashMap<Integer, UpgradeTemplate> upgradeDefinitions = new HashMap<>();
    private final HashMap<Integer, CatalystTemplate> catalystDefinitions = new HashMap<>();
    private final HashMap<Integer, EffectTemplate> buffDefinitions = new HashMap<>();

    public TemplateRegistry(Context context) {
        for (GeneratorTemplate definition : GeneratorTemplateLoader.load(context)) {
            generatorDefinitions.put(definition.id, definition);
        }

        for (UpgradeTemplate definition : UpgradeTemplateLoader.load(context)) {
            upgradeDefinitions.put(definition.id, definition);
        }

        for (CatalystTemplate definition : CatalystTemplateLoader.load(context)) {
            catalystDefinitions.put(definition.id, definition);
        }

        for (EffectTemplate definition : EffectTemplateLoader.load(context)) {
            buffDefinitions.put(definition.id, definition);
        }
    }

    public HashMap<Integer, GeneratorTemplate> getGeneratorDefinitions() {
        return generatorDefinitions;
    }

    public HashMap<Integer, UpgradeTemplate> getUpgradeDefinitions() {
        return upgradeDefinitions;
    }

    public HashMap<Integer, CatalystTemplate> getCatalystDefinitions() {
        return catalystDefinitions;
    }

    public HashMap<Integer, EffectTemplate> getBuffDefinitions() {
        return buffDefinitions;
    }


    public GeneratorTemplate getGeneratorDefinition(int id) {
        return generatorDefinitions.get(id);
    }

    public UpgradeTemplate getUpgradeDefinition(int id) {
        return upgradeDefinitions.get(id);
    }

    public CatalystTemplate getCatalystDefinition(int id) {
        return catalystDefinitions.get(id);
    }

    public EffectTemplate getBuffDefinition(int id) {
        return buffDefinitions.get(id);
    }
}
