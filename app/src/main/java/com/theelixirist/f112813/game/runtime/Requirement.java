package com.theelixirist.f112813.game.runtime;

import com.theelixirist.f112813.game.math.BigDouble;

public class Requirement {
    public enum Gate {
        VISIBILITY,
        PURCHASABILITY
    }

    public enum Criterion {
        // Elixir
        ELIXIR_TOTAL_PRODUCED,

        // Generator
        GENERATOR_TOTAL_OWNED,
        GENERATOR_TOTAL_OWNED_WITH_ID,

        // Upgrade
        UPGRADE_TOTAL_OWNED,
        UPGRADE_TOTAL_OWNED_WITH_ID,

        // Catalyst
        CATALYST_TOTAL_COLLECTED
    }

    private final Gate gate;
    private final Criterion criterion;
    private final int subjectId;
    private final BigDouble threshold;

    public Requirement(Gate gate, Criterion criterion, int subjectId, BigDouble threshold) {
        this.gate = gate;
        this.criterion = criterion;
        this.subjectId = subjectId;
        this.threshold = threshold;
    }

    public Gate getGate() {
        return gate;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public BigDouble getThreshold() {
        return threshold;
    }

    public boolean isMet() {
        switch (criterion) {
            // Elixir
            case ELIXIR_TOTAL_PRODUCED: {
                return false; // To Do
            }

            // Generator
            case GENERATOR_TOTAL_OWNED: {
                return false; // To Do
            }
            case GENERATOR_TOTAL_OWNED_WITH_ID: {
                return false; // To Do
            }

            // Upgrade
            case UPGRADE_TOTAL_OWNED: {
                return false; // To Do
            }
            case UPGRADE_TOTAL_OWNED_WITH_ID: {
                return false; // To Do
            }

            // Catalyst
            case CATALYST_TOTAL_COLLECTED: {
                return false; // To Do
            }

            default: {
                return false;
            }
        }
    }
}
