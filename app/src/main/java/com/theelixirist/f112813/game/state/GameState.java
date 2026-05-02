package com.theelixirist.f112813.game.state;

import com.theelixirist.f112813.definitions.DefinitionRegistry;

public class GameState {
    private final Chronicle chronicle;
    private final Inventory inventory;
    private final ActiveCatalysts activeCatalysts;
    private final ActiveEffects activeEffects;
    private final DefinitionRegistry definitionRegistry;

    public GameState(
            Chronicle chronicle,
            Inventory inventory,
            ActiveCatalysts activeCatalysts,
            ActiveEffects activeEffects,
            DefinitionRegistry definitionRegistry
    ) {
        this.chronicle = chronicle;
        this.inventory = inventory;
        this.activeCatalysts = activeCatalysts;
        this.activeEffects = activeEffects;
        this.definitionRegistry = definitionRegistry;
    }

    public Chronicle getChronicle() {
        return chronicle;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ActiveCatalysts getActiveCatalysts() {
        return activeCatalysts;
    }

    public ActiveEffects getActiveEffects() {
        return activeEffects;
    }
}
