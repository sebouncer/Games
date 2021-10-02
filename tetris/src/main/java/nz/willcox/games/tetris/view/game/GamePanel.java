package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.GameConfig;
import nz.willcox.games.tetris.model.GameData;
import nz.willcox.games.tetris.view.TetrisPanel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

import static nz.willcox.games.tetris.Constants.SCREEN_HEIGHT;
import static nz.willcox.games.tetris.Constants.SCREEN_WIDTH;

public class GamePanel extends JPanel implements TetrisPanel {

    private final GameConfig gameConfig;
    private GameData gameData;

    @Inject
    public GamePanel(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.CYAN);
    }

    public void initialise() {}

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void destroy() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }
}
