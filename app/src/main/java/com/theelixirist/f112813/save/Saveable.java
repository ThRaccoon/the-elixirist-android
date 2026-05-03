package com.theelixirist.f112813.save;

public interface Saveable<T> {
    T load();

    void save();
}
