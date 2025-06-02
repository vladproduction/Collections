package com.vladproduction.ch07_generic.p07_generic_event_system;

public interface EventListener<T> {

    void onEvent(T event);

}
