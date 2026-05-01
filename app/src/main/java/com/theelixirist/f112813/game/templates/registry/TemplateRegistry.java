package com.theelixirist.f112813.game.templates.registry;

import android.content.Context;

import com.theelixirist.f112813.game.templates.CatalystTemplate;
import com.theelixirist.f112813.game.templates.EffectTemplate;
import com.theelixirist.f112813.game.templates.GeneratorTemplate;
import com.theelixirist.f112813.game.templates.loader.TemplateLoader;
import com.theelixirist.f112813.game.templates.UpgradeTemplate;

import java.util.HashMap;

public class TemplateRegistry {
    private final HashMap<Integer, GeneratorTemplate> generatorTemplates = new HashMap<>();
    private final HashMap<Integer, UpgradeTemplate> upgradeTemplates = new HashMap<>();
    private final HashMap<Integer, CatalystTemplate> catalystTemplates = new HashMap<>();
    private final HashMap<Integer, EffectTemplate> effectTemplates = new HashMap<>();

    public TemplateRegistry(Context context) {
        TemplateLoader.load(context, "generators.json", GeneratorTemplate.class)
                .forEach(t -> generatorTemplates.put(t.id, t));

        TemplateLoader.load(context, "upgrades.json", UpgradeTemplate.class)
                .forEach(u -> upgradeTemplates.put(u.id, u));

        TemplateLoader.load(context, "catalysts.json", CatalystTemplate.class)
                .forEach(c -> catalystTemplates.put(c.id, c));

        TemplateLoader.load(context, "effects.json", EffectTemplate.class)
                .forEach(e -> effectTemplates.put(e.id, e));
    }

    public HashMap<Integer, GeneratorTemplate> getGeneratorTemplates() {
        return generatorTemplates;
    }

    public HashMap<Integer, UpgradeTemplate> getUpgradeTemplates() {
        return upgradeTemplates;
    }

    public HashMap<Integer, CatalystTemplate> getCatalystTemplates() {
        return catalystTemplates;
    }

    public HashMap<Integer, EffectTemplate> getEffectTemplates() {
        return effectTemplates;
    }

    public GeneratorTemplate getGeneratorTemplate(int id) {
        return generatorTemplates.get(id);
    }

    public UpgradeTemplate getUpgradeTemplate(int id) {
        return upgradeTemplates.get(id);
    }

    public CatalystTemplate getCatalystTemplate(int id) {
        return catalystTemplates.get(id);
    }

    public EffectTemplate getEffectTemplate(int id) {
        return effectTemplates.get(id);
    }
}
