package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.game.GameData;

import javax.swing.*;
import java.awt.*;

public class PlayerSideBarPanel extends JPanel {

    public static final int WIDTH = 150;
    public static final int HEIGHT = 500;

    private static final int SIDE_PANEL_WIDTH_INDENT = 20;
    private static final int SIDE_PANEL_HEIGHT_INDENT = 20;

    private final GameData gameData;
    private final PlayerSideNextShapePanel playerSideNextShapePanel;
    private final PlayerSideScorePanel playerSideScorePanel;
    private final PlayerSideLevelPanel playerSideLevelPanel;
    private final PlayerSideLineCountPanel playerSideLineCountPanel;

    public PlayerSideBarPanel(
            GameData gameData,
            PlayerSideNextShapePanel playerSideNextShapePanel,
            PlayerSideScorePanel playerSideScorePanel,
            PlayerSideLevelPanel playerSideLevelPanel,
            PlayerSideLineCountPanel playerSideLineCountPanel
    ) {
        this.gameData = gameData;
        this.playerSideNextShapePanel = playerSideNextShapePanel;
        this.playerSideScorePanel = playerSideScorePanel;
        this.playerSideLevelPanel = playerSideLevelPanel;
        this.playerSideLineCountPanel = playerSideLineCountPanel;

        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setBackground(Color.CYAN);

        int y = 0;
        this.playerSideNextShapePanel.setBounds(SIDE_PANEL_WIDTH_INDENT, 0, PlayerSideNextShapePanel.WIDTH, PlayerSideNextShapePanel.HEIGHT);
        add(this.playerSideNextShapePanel);
        y = y + PlayerSideNextShapePanel.HEIGHT + SIDE_PANEL_HEIGHT_INDENT;

        this.playerSideScorePanel.setBounds(SIDE_PANEL_WIDTH_INDENT, y, PlayerSideScorePanel.WIDTH, PlayerSideScorePanel.HEIGHT);
        add(this.playerSideScorePanel);
        y = y + PlayerSideScorePanel.HEIGHT + SIDE_PANEL_HEIGHT_INDENT;

        this.playerSideLevelPanel.setBounds(SIDE_PANEL_WIDTH_INDENT, y, PlayerSideLevelPanel.WIDTH, PlayerSideLevelPanel.HEIGHT);
        add(this.playerSideLevelPanel);
        y = y + PlayerSideLevelPanel.HEIGHT + SIDE_PANEL_HEIGHT_INDENT;

        this.playerSideLineCountPanel.setBounds(SIDE_PANEL_WIDTH_INDENT, y, PlayerSideLineCountPanel.WIDTH, PlayerSideLineCountPanel.HEIGHT);
        add(this.playerSideLineCountPanel);
    }

    public static class Factory {
        public PlayerSideBarPanel create(
                GameData gameData
        ) {
            final PlayerSideNextShapePanel playerSideNextShapePanel = new PlayerSideNextShapePanel.Factory().create(gameData);
            final PlayerSideScorePanel playerSideScorePanel = new PlayerSideScorePanel.Factory().create(gameData);
            final PlayerSideLevelPanel playerSideLevelPanel = new PlayerSideLevelPanel.Factory().create(gameData);
            final PlayerSideLineCountPanel playerSideLineCountPanel = new PlayerSideLineCountPanel.Factory().create(gameData);
            return new PlayerSideBarPanel(
                    gameData,
                    playerSideNextShapePanel,
                    playerSideScorePanel,
                    playerSideLevelPanel,
                    playerSideLineCountPanel
            );
        }
    }
}
