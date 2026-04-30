package com.theelixirist.f112813.game.runtime;

import com.theelixirist.f112813.game.Chronicle;
import com.theelixirist.f112813.game.GameState;
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
                return chronicle.getTotalElixirsBrewed().compareTo(threshold) >= 0;
            }

            // Generator
            case GENERATOR_COUNT_ALL: {
                int count = 0;
                for (Generator generator : gameState.getGenerators().values()) {
                    count += generator.getCurrentCount();
                }
                return new BigDouble(count).compareTo(threshold) >= 0;
            }
            case GENERATOR_COUNT_BY_ID: {
                Generator generator = gameState.getGenerator(targetId);
                return generator != null && new BigDouble(generator.getCurrentCount()).compareTo(threshold) >= 0;
            }

            // Upgrade
            case UPGRADE_COUNT_ALL: {
                return new BigDouble(gameState.getUpgrades().size()).compareTo(threshold) >= 0;
            }
            case UPGRADE_COUNT_BY_ID: {
                return gameState.getUpgrade(targetId) != null;
            }

            // Catalyst
            case CATALYST_TOTAL_COLLECTED: {
                return new BigDouble(chronicle.getTotalCatalystsCollected()).compareTo(threshold) >= 0;
            }

            default: {
                return false;
            }
        }
    }
}
