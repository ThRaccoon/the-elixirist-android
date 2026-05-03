package com.theelixirist.f112813.save;

import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    private final List<Saveable<?>> handlers = new ArrayList<>();

    public void registerHandler(Saveable<?> handler) {
        handlers.add(handler);
    }

    public void save() {
        for (Saveable<?> handler : handlers) {
            handler.save();
        }
    }
}
