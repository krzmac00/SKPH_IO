package com.example.skph.observer;

public interface Observer<T> {
    void update(T event);
}
