package com.vladproduction.ch07_generic.p07_generic_event_system;

import java.util.ArrayList;
import java.util.List;

public class EventManager<T> {

    private final List<EventListener<T>> listeners = new ArrayList<>();

    public void addListener(EventListener<T> listener){
        listeners.add(listener);
    }

    public void removeListener(EventListener<T> listener){
        listeners.remove(listener);
    }

    public void fireEvent(T event){
        listeners.forEach(listener -> listener.onEvent(event));
    }

}
