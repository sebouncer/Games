package nz.willcox.games.tetris.view.menu;

import nz.willcox.games.tetris.view.TetrisPanel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

import static nz.willcox.games.tetris.Constants.SCREEN_HEIGHT;
import static nz.willcox.games.tetris.Constants.SCREEN_WIDTH;
import static nz.willcox.games.tetris.view.menu.ButtonPanel.BUTTON_PANEL_HEIGHT;
import static nz.willcox.games.tetris.view.menu.ButtonPanel.BUTTON_PANEL_WIDTH;

public class MenuPanel extends JPanel implements TetrisPanel {

    private static final int BUTTON_PANEL_Y = 600;

    private final ButtonPanel buttonPanel;

    @Inject
    public MenuPanel(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.BLUE);
        buttonPanel.setBounds((SCREEN_WIDTH/2)-(BUTTON_PANEL_WIDTH/2) , BUTTON_PANEL_Y, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
        setLayout(null);
        add(buttonPanel);
    }

    public void initialise() {
        buttonPanel.initialise();
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void destroy() {
        buttonPanel.destroy();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
