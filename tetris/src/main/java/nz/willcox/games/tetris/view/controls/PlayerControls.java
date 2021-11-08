package nz.willcox.games.tetris.view.controls;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class PlayerControls {

    private static final int RELEASED = 50;

    private final List<PlayerEventListener> playerEventListeners;
    private final Map<Integer, Consumer<PlayerEventListener>> actions;

    public PlayerControls(
            int left,
            int down,
            int up,
            int right,
            int start,
            int buttonOne,
            int buttonTwo
    ) {
        this.playerEventListeners = new ArrayList<>();
        this.actions = new HashMap<>();
        actions.put(left, PlayerEventListener::leftAction);
        actions.put(down, PlayerEventListener::downAction);
        actions.put(down + RELEASED, PlayerEventListener::downActionReleased);
        actions.put(up, PlayerEventListener::upAction);
        actions.put(right, PlayerEventListener::rightAction);
        actions.put(start, PlayerEventListener::buttonStart);
        actions.put(buttonOne, PlayerEventListener::buttonOne);
        actions.put(buttonTwo, PlayerEventListener::buttonTwo);
    }

    public void addListener(PlayerEventListener playerEventListener) {
        playerEventListeners.add(playerEventListener);
    }

    public void removeListener(PlayerEventListener playerEventListener) {
        playerEventListeners.remove(playerEventListener);
    }

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        triggerListeners(actions.get(e.getKeyCode()));
    }

    public void keyReleased(KeyEvent e) {
        triggerListeners(actions.get(e.getKeyCode() + RELEASED));
    }

    private void triggerListeners(final Consumer<PlayerEventListener> playerEventListenerConsumer) {
        if (playerEventListenerConsumer != null) {
            final ArrayList<PlayerEventListener> playerEventListenersClone = new ArrayList<>(playerEventListeners);
            playerEventListenersClone.forEach(playerEventListenerConsumer);
        }
    }
}
