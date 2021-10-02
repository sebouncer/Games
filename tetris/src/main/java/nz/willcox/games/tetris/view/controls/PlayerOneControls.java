package nz.willcox.games.tetris.view.controls;

import javax.inject.Inject;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerOneControls extends PlayerControls implements KeyListener {

    private static final int LEFT = KeyEvent.VK_A;
    private static final int DOWN = KeyEvent.VK_S;
    private static final int UP = KeyEvent.VK_W;
    private static final int RIGHT = KeyEvent.VK_D;
    private static final int START = KeyEvent.VK_ENTER;
    private static final int BUTTON_ONE = KeyEvent.VK_G;
    private static final int BUTTON_TWO = KeyEvent.VK_B;

    @Inject
    public PlayerOneControls() {
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
