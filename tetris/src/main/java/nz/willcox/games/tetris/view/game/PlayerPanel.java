package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.GameData;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private final GameData gameData;

    public PlayerPanel(GameData gameData) {
        this.gameData = gameData;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public class Factory {
        public PlayerPanel create(GameData gameData) {
            return new PlayerPanel(gameData);
        }
    }
}
