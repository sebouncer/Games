package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.view.TetrisPanel;
import nz.willcox.games.tetris.view.controls.PlayerControls;

import javax.swing.*;
import java.awt.*;

public class PlayerGamePanel extends JPanel implements TetrisPanel {

    public static final int WIDTH = PlayerBorderPanel.WIDTH + PlayerSideBarPanel.WIDTH;
    public static final int HEIGHT = PlayerBorderPanel.HEIGHT;

    private final GameData gameData;
    private final PlayerControls playerControls;
    private final PlayerBorderPanel playerBorderPanel;
    private final PlayerSideBarPanel playerSideBarPanel;

    public PlayerGamePanel(
            GameData gameData,
            PlayerControls playerControls,
            PlayerBorderPanel playerBorderPanel,
            PlayerSideBarPanel playerSideBarPanel
    ) {
        this.gameData = gameData;
        this.playerControls = playerControls;
        this.playerBorderPanel = playerBorderPanel;
        this.playerSideBarPanel = playerSideBarPanel;

        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setBackground(Color.CYAN);

        this.playerBorderPanel.setBounds(0 , 0, PlayerBorderPanel.WIDTH, PlayerBorderPanel.HEIGHT);
        add(this.playerBorderPanel);

        this.playerSideBarPanel.setBounds(PlayerBorderPanel.WIDTH , 0, PlayerSideBarPanel.WIDTH, PlayerSideBarPanel.HEIGHT);
        add(this.playerSideBarPanel);
    }

    @Override
    public void initialise() {
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void destroy() {
        playerBorderPanel.destroy();
    }

    public static class Factory {
        public PlayerGamePanel create(
                GameData gameData,
                PlayerControls playerControls
        ) {
            final PlayerBorderPanel playerBorderPanel = new PlayerBorderPanel.Factory().create(gameData, playerControls);
            final PlayerSideBarPanel playerSideBarPanel = new PlayerSideBarPanel.Factory().create(gameData);
            return new PlayerGamePanel(gameData, playerControls, playerBorderPanel, playerSideBarPanel);
        }
    }
}
