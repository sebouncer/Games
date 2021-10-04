package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.view.controls.PlayerControls;

import javax.swing.*;
import java.awt.*;

import static nz.willcox.games.tetris.Constants.BLOCK_HEIGHT;
import static nz.willcox.games.tetris.Constants.BLOCK_WIDTH;
import static nz.willcox.games.tetris.Constants.NUM_BLOCKS_IN_COLUMN;
import static nz.willcox.games.tetris.Constants.NUM_BLOCKS_IN_ROW;

public class PlayerBorderPanel extends JPanel {

    private static final int BORDER_WIDTH = 2;
    public static final int WIDTH = BORDER_WIDTH * 2 + BLOCK_WIDTH * NUM_BLOCKS_IN_COLUMN;
    public static final int HEIGHT = BORDER_WIDTH * 2 + BLOCK_HEIGHT * NUM_BLOCKS_IN_ROW;

    private final GameData gameData;
    private final PlayerControls playerControls;
    private final PlayerPanel playerPanel;

    public PlayerBorderPanel(
            GameData gameData,
            PlayerControls playerControls
    ) {
        this.gameData = gameData;
        this.playerControls = playerControls;
        setSize(WIDTH + BORDER_WIDTH*2, HEIGHT + BORDER_WIDTH*2);
        setLayout(null);
//        setBackground(Color.BLACK);

        playerPanel = new PlayerPanel.Factory().create(gameData, playerControls);
        playerPanel.setBounds(BORDER_WIDTH , BORDER_WIDTH, PlayerPanel.WIDTH, PlayerPanel.HEIGHT);
        add(playerPanel);
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
            return new PlayerBorderPanel(gameData, playerControls);
        }
    }
}
