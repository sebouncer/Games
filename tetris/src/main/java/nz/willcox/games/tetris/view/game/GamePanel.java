package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.page.GameConfig;
import nz.willcox.games.tetris.service.GameRunner;
import nz.willcox.games.tetris.view.TetrisPanel;
import nz.willcox.games.tetris.view.controls.PlayerOneControls;
import nz.willcox.games.tetris.view.controls.PlayerTwoControls;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static nz.willcox.games.tetris.Constants.SCREEN_HEIGHT;
import static nz.willcox.games.tetris.Constants.SCREEN_WIDTH;

public class GamePanel extends JPanel implements TetrisPanel {

    private final GameConfig gameConfig;
    private final GameRunner gameRunner;
    private final PlayerOneControls playerOneControls;
    private final PlayerTwoControls playerTwoControls;

    private List<TetrisPanel> tetrisPanels;

    @Inject
    public GamePanel(
            GameConfig gameConfig,
            GameRunner gameRunner,
            PlayerOneControls playerOneControls,
            PlayerTwoControls playerTwoControls
    ) {
        this.gameConfig = gameConfig;
        this.gameRunner = gameRunner;
        this.playerOneControls = playerOneControls;
        this.playerTwoControls = playerTwoControls;
        this.tetrisPanels = new ArrayList<>();
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLayout(null);
        setBackground(Color.CYAN);
    }

    public void initialise() {
        gameRunner.initialiseOnePlayer();
        addKeyListener(playerOneControls);

        final PlayerGamePanel playerGamePanel = new PlayerGamePanel.Factory().create(gameRunner.getPlayerOneGameData(), playerOneControls);

//        final PlayerBorderPanel playerBorderPanel = new PlayerBorderPanel.Factory().create(gameRunner.getPlayerOneGameData(), playerOneControls);
        tetrisPanels.add(playerGamePanel);
        playerGamePanel.setBounds(50 , 50, PlayerGamePanel.WIDTH, PlayerGamePanel.HEIGHT);
        add(playerGamePanel);

        this.setFocusable(true);
        this.requestFocus();
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void destroy() {
        tetrisPanels.forEach(TetrisPanel::destroy);

        removeKeyListener(playerOneControls);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
