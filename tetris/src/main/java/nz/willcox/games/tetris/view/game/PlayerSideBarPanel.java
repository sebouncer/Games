package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.game.GameData;

import javax.swing.*;

public class PlayerSideBarPanel extends JPanel {

    public static final int WIDTH = 150;
    public static final int HEIGHT = 400;

    private static final int SIDE_PANEL_WIDTH_INDENT = 20;
    private static final int SIDE_PANEL_HEIGHT_INDENT = 20;

    private final GameData gameData;
    private final PlayerSideNextShapePanel playerSideNextShapePanel;

    public PlayerSideBarPanel(
            GameData gameData,
            PlayerSideNextShapePanel playerSideNextShapePanel
    ) {
        this.gameData = gameData;
        this.playerSideNextShapePanel = playerSideNextShapePanel;

        setSize(WIDTH, HEIGHT);
        setLayout(null);

        this.playerSideNextShapePanel.setBounds(SIDE_PANEL_WIDTH_INDENT, 0, PlayerSideNextShapePanel.WIDTH, PlayerSideNextShapePanel.HEIGHT);
        add(this.playerSideNextShapePanel);
    }

    public static class Factory {
        public PlayerSideBarPanel create(
                GameData gameData
        ) {
            final PlayerSideNextShapePanel playerSideNextShapePanel = new PlayerSideNextShapePanel.Factory().create(gameData);
            return new PlayerSideBarPanel(gameData, playerSideNextShapePanel);
        }
    }
}
