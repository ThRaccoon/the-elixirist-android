package com.theelixirist.f112813.game.models;

import com.theelixirist.f112813.game.math.BigDouble;

public class Clicker {
    private final BigDouble baseYieldPerClick;

    public Clicker(
            BigDouble baseYieldPerClick
    ) {
        this.baseYieldPerClick = baseYieldPerClick;
    }

    public BigDouble getBaseYieldPerClick() {
        return baseYieldPerClick;
    }
}
