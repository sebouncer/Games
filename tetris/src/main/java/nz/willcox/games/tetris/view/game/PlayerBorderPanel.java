package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.view.controls.PlayerControls;

import javax.swing.*;
import java.awt.*;

import static nz.willcox.games.tetris.Constants.BLOCK_HEIGHT;
import static nz.willcox.games.tetris.Constants.BLOCK_WIDTH;
import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;

public class PlayerBorderPanel extends JPanel {

    private static final int BORDER_WIDTH = 2;
    public static final int WIDTH = BORDER_WIDTH * 2 + PlayerPanel.WIDTH;
    public static final int HEIGHT = BORDER_WIDTH * 2 + PlayerPanel.HEIGHT;

    private final GameData gameData;
    private final PlayerControls playerControls;
    private final PlayerPanel playerPanel;

    public PlayerBorderPanel(
            GameData gameData,
            PlayerControls playerControls,
            PlayerPanel playerPanel
    ) {
        this.gameData = gameData;
        this.playerControls = playerControls;
        this.playerPanel = playerPanel;

        setSize(WIDTH, HEIGHT);
        setLayout(null);

        this.playerPanel.setBounds(BORDER_WIDTH , BORDER_WIDTH, PlayerPanel.WIDTH, PlayerPanel.HEIGHT);
        add(this.playerPanel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawPanelOutline(g);
    }

    private void drawPanelOutline(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(BORDER_WIDTH, BORDER_WIDTH, WIDTH-(BORDER_WIDTH*2), HEIGHT-(BORDER_WIDTH*2));
    }

    public void destroy() {
        playerPanel.destroy();
    }

    public static class Factory {
        public PlayerBorderPanel create(
                GameData gameData,
                PlayerControls playerControls
        ) {
            final PlayerPanel playerPanel = new PlayerPanel.Factory().create(gameData, playerControls);
            return new PlayerBorderPanel(gameData, playerControls, playerPanel);
        }
    }
}
