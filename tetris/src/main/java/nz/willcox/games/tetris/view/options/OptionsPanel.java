package nz.willcox.games.tetris.view.options;

import nz.willcox.games.tetris.model.page.CurrentPage;
import nz.willcox.games.tetris.model.page.PageEnum;
import nz.willcox.games.tetris.view.TetrisPanel;
import nz.willcox.games.tetris.view.controls.PlayerEventListener;
import nz.willcox.games.tetris.view.controls.PlayerOneControls;
import nz.willcox.games.tetris.view.controls.PlayerTwoControls;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

import static nz.willcox.games.tetris.Constants.SCREEN_HEIGHT;
import static nz.willcox.games.tetris.Constants.SCREEN_WIDTH;
import static nz.willcox.games.tetris.view.menu.ButtonPanel.BUTTON_PANEL_HEIGHT;
import static nz.willcox.games.tetris.view.menu.ButtonPanel.BUTTON_PANEL_WIDTH;

public class OptionsPanel extends JPanel implements TetrisPanel, PlayerEventListener {

    private static final int BUTTON_PANEL_Y = 600;

    private final CurrentPage currentPage;
    private final PlayerOneControls playerOneControls;
    private final PlayerTwoControls playerTwoControls;

    @Inject
    public OptionsPanel(
            CurrentPage currentPage,
            PlayerOneControls playerOneControls,
            PlayerTwoControls playerTwoControls
    ) {
        this.currentPage = currentPage;
        this.playerOneControls = playerOneControls;
        this.playerTwoControls = playerTwoControls;
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.BLACK);
        setLayout(null);
    }

    public void initialise() {
        addKeyListener(playerOneControls);
        addKeyListener(playerTwoControls);
        playerOneControls.addListener(this);
        playerTwoControls.addListener(this);
        this.setFocusable(true);
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void destroy() {
        this.removeKeyListener(playerOneControls);
        this.removeKeyListener(playerTwoControls);
        playerOneControls.removeListener(this);
        playerTwoControls.removeListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect((SCREEN_WIDTH/2)-(BUTTON_PANEL_WIDTH/2) , BUTTON_PANEL_Y, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
    }

    @Override
    public void leftAction() {}

    @Override
    public void downAction() {}

    @Override
    public void downActionReleased() {}

    @Override
    public void upAction() {}

    @Override
    public void rightAction() {}

    @Override
    public void buttonStart() {
        currentPage.setPage(PageEnum.MENU);
    }

    @Override
    public void buttonOne() {}

    @Override
    public void buttonTwo() {}
}
