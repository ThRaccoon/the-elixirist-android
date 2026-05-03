package com.theelixirist.f112813.domain;

import com.theelixirist.f112813.domain.models.Chronicle;
import com.theelixirist.f112813.game.math.BigDouble;

public class Requirement {
    public enum Gate {
        VISIBILITY,
        PURCHASABILITY
    }

    public enum Condition {
        // Elixir
        ELIXIR_TOTAL_PRODUCED,

        // Generator
        GENERATOR_COUNT_ALL,
        GENERATOR_COUNT_BY_ID,

        // Upgrade
        UPGRADE_COUNT_ALL,
        UPGRADE_COUNT_BY_ID,

        // Catalyst
        CATALYST_TOTAL_COLLECTED
    }

    private final Gate gate;
    private final Condition condition;
    private final int targetId;
    private final BigDouble threshold;

    public Requirement(Gate gate, Condition condition, int targetId, BigDouble threshold) {
        this.gate = gate;
        this.condition = condition;
        this.targetId = targetId;
        this.threshold = threshold;
    }

    public Gate getGate() {
        return gate;
    }

    public Condition getCondition() {
        return condition;
    }

    public int getTargetId() {
        return targetId;
    }

    public BigDouble getThreshold() {
        return threshold;
    }

    public boolean isMet(GameState gameState, Chronicle chronicle) {
        switch (condition) {
            // Elixir
            case ELIXIR_TOTAL_PRODUCED: {
                return false;
            }

            // Generator
            case GENERATOR_COUNT_ALL: {
                return false;
            }
            case GENERATOR_COUNT_BY_ID: {
                return false;
            }

            // Upgrade
            case UPGRADE_COUNT_ALL: {
                return false;
            }
            case UPGRADE_COUNT_BY_ID: {
                return false;
            }

            // Catalyst
            case CATALYST_TOTAL_COLLECTED: {
                return false;
            }

            default: {
                return false;
            }
        }
    }
}
