package nz.willcox.games.tetris.view.controls;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Singleton
public class PlayerTwoControls extends PlayerControls implements KeyListener {

    private static final int LEFT = KeyEvent.VK_LEFT;
    private static final int DOWN = KeyEvent.VK_DOWN;
    private static final int UP = KeyEvent.VK_UP;
    private static final int RIGHT = KeyEvent.VK_RIGHT;
    private static final int START = KeyEvent.VK_ENTER;
    private static final int BUTTON_ONE = KeyEvent.VK_HOME;
    private static final int BUTTON_TWO = KeyEvent.VK_END;

    @Inject
    public PlayerTwoControls() {
        super(
                LEFT,
                DOWN,
                UP,
                RIGHT,
                START,
                BUTTON_ONE,
                BUTTON_TWO
        );
    }
}
