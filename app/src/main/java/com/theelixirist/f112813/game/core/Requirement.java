package com.theelixirist.f112813.game.core;

import com.theelixirist.f112813.game.math.BigDouble;

public class Requirement {
    public enum Gate {
        VISIBILITY,
        PURCHASABILITY
    }

    public enum Type {
        TOTAL_ELIXIRS_PRODUCED,
        GENERATORS_OWNED,
        UPGRADES_OWNED
    }

    private final Gate gate;
    private final Type type;
    private final int targetId;
    private final BigDouble threshold;

    public Requirement(
            Gate gate,
            Type type,
            int targetId,
            BigDouble threshold
    ) {
        this.gate = gate;
        this.type = type;
        this.targetId = targetId;
        this.threshold = threshold;
    }

    public Gate getGate() {
        return gate;
    }

    public Type getType() {
        return type;
    }

    public int getTargetId() {
        return targetId;
    }

    public BigDouble getThreshold() {
        return threshold;
    }

    public boolean isMet(GameState state) {
        switch (type) {
            case TOTAL_ELIXIRS_PRODUCED:
                return false; // TO Do
            case GENERATORS_OWNED:
                return false; // To Do
            case UPGRADES_OWNED:
                return false; // To Do
            default:
                return false;
        }
    }
}
