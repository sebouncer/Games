package nz.willcox.games.tetris;

import nz.willcox.games.tetris.view.TetrisPanel;

import javax.inject.Inject;
import javax.swing.*;

import static nz.willcox.games.tetris.Constants.SCREEN_HEIGHT;
import static nz.willcox.games.tetris.Constants.SCREEN_WIDTH;

public class TetrisFrame extends JFrame {

    private TetrisPanel currentPanel;

    @Inject
    public TetrisFrame() { }

    public void initialise() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setVisible(true);
        setLayout(null);
    }

    public void switchPanel(TetrisPanel panel) {
        panel.initialise();
        add(panel.getPanel());
        if (currentPanel != null) {
            remove(currentPanel.getPanel());
            currentPanel.destroy();
        }
        setVisible(true);
        currentPanel = panel;
        currentPanel.getPanel().repaint();
    }
}
