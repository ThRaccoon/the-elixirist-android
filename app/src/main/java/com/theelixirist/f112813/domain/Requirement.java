package com.theelixirist.f112813.domain;

import com.theelixirist.f112813.AppContainer;
import com.theelixirist.f112813.domain.models.Generator;
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

    public boolean isMet(AppContainer appContainer) {
        switch (condition) {
            // Elixir
            case ELIXIR_TOTAL_PRODUCED: {
                return appContainer.getChronicle().getTotalElixirsBrewed().compareTo(threshold) >= 0;
            }

            // Generator
            case GENERATOR_COUNT_ALL: {
                BigDouble total = new BigDouble(BigDouble.ZERO);
                for (Generator generator : appContainer.getGeneratorRegistry().getGenerators().values()) {
                    total.add(generator.getCurrentCount(), 0);
                }
                return total.compareTo(threshold) >= 0;
            }
            case GENERATOR_COUNT_BY_ID: {
                Generator generator = appContainer.getGeneratorRegistry().getGenerators().get(targetId);
                if (generator == null) return false;
                return new BigDouble(generator.getCurrentCount()).compareTo(threshold) >= 0;
            }

            // Upgrade
            case UPGRADE_COUNT_ALL: {
                return new BigDouble(appContainer.getUpgradeRegistry().getUpgrades().size()).compareTo(threshold) >= 0;
            }
            case UPGRADE_COUNT_BY_ID: {
                return appContainer.getUpgradeRegistry().getUpgrades().containsKey(targetId);
            }

            // Catalyst
            case CATALYST_TOTAL_COLLECTED: {
                return new BigDouble(appContainer.getChronicle().getTotalCatalystsCollected()).compareTo(threshold) >= 0;
            }

            default: {
                return false;
            }
        }
    }
}
