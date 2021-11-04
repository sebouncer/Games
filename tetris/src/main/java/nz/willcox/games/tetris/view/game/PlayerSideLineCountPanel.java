package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.Listener;
import nz.willcox.games.tetris.model.game.GameData;

import javax.swing.*;
import java.awt.*;

public class PlayerSideLineCountPanel extends JPanel implements Listener {

    public static final int WIDTH = PlayerSidePanelBase.WIDTH;
    public static final int HEIGHT = PlayerSidePanelBase.HEIGHT;

    private static final String TITLE = "LINES";

    private final GameData gameData;
    private final PlayerSidePanelBase playerSidePanelBase;

    public PlayerSideLineCountPanel(
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
        gameData.getLines().addListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerSidePanelBase.drawBorder(g);
        playerSidePanelBase.drawTitle(g, TITLE);
        playerSidePanelBase.drawContentStringCenter(g, "" + gameData.getLines().getLines());
    }

    @Override
    public void eventTrigger() {
        repaint();
    }

    public static class Factory {
        public PlayerSideLineCountPanel create(
                GameData gameData
        ) {
            final PlayerSidePanelBase playerSidePanelBase = new PlayerSidePanelBase();
            return new PlayerSideLineCountPanel(gameData, playerSidePanelBase);
        }
    }
}
