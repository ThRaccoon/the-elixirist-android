package com.theelixirist.f112813.game.models;

import java.util.Set;

public class Catalyst {
    private final int id;

    private final int iconResId;
    private final int nameResId;
    private final int descResId;

    private float despawnDurationSec;

    private final Set<Integer> buffPoolIds;

    public Catalyst(
            int id,
            int iconResId,
            int nameResId,
            int descResId,
            float despawnDurationSec,
            Set<Integer> buffPoolIds
    ) {
        this.id = id;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
        this.descResId = descResId;
        this.despawnDurationSec = despawnDurationSec;
        this.buffPoolIds = buffPoolIds;
    }

    public int getId() {
        return id;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getNameResId() {
        return nameResId;
    }

    public int getDescResId() {
        return descResId;
    }

    public float getDespawnDurationSec() {
        return despawnDurationSec;
    }

    public void setDespawnDurationSec(float despawnDurationSec) {
        this.despawnDurationSec = despawnDurationSec;
    }

    public Set<Integer> getBuffPoolIds() {
        return buffPoolIds;
    }
}
