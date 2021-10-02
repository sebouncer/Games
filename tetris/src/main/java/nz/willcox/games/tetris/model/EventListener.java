package nz.willcox.games.tetris.model;

import java.util.ArrayList;
import java.util.List;

public class EventListener {

    private final List<Listener> listeners;

    public EventListener() {
        this.listeners = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void triggerListeners() {
        final ArrayList<Listener> listenersClone = new ArrayList<>(listeners);
        listenersClone.forEach(Listener::eventTrigger);
    }
}
