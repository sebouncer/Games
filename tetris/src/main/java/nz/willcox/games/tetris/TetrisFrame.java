package nz.willcox.games.tetris;

import javax.inject.Inject;
import javax.swing.*;

public class TetrisFrame extends JFrame {

    private final GamePanel gamePanel;

    @Inject
    public TetrisFrame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setup() {
        add(gamePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 800);
        setResizable(false);
        setVisible(true);
    }
}
