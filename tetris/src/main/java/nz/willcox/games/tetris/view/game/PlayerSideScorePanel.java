package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.Listener;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.view.util.CenterText;

import javax.swing.*;
import java.awt.*;

public class PlayerSideScorePanel extends JPanel implements Listener {

    public static final int WIDTH = PlayerSidePanelBase.WIDTH;
    public static final int HEIGHT = PlayerSidePanelBase.HEIGHT;

    private static final String TITLE = "SCORE";

    private final GameData gameData;
    private final PlayerSidePanelBase playerSidePanelBase;


    public PlayerSideScorePanel(
            GameData gameData,
            PlayerSidePanelBase playerSidePanelBase
    ) {
        this.gameData = gameData;
        this.playerSidePanelBase = playerSidePanelBase;

        setSize(WIDTH, HEIGHT);
        setLayout(null);
        initialise();
    }

    public void initialise() {
        gameData.getScore().addListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerSidePanelBase.drawBorder(g);
        playerSidePanelBase.drawTitle(g, TITLE);
        drawScore(g);
    }

    private void drawScore(Graphics g) {
        final int score = gameData.getScore().getScore();
        playerSidePanelBase.drawContentStringCenter(g, score);
    }

    @Override
    public void eventTrigger() {
        repaint();
    }

    public static class Factory {
        public PlayerSideScorePanel create(
                GameData gameData
        ) {
            final PlayerSidePanelBase playerSidePanelBase = new PlayerSidePanelBase();
            return new PlayerSideScorePanel(gameData, playerSidePanelBase);
        }
    }
}
