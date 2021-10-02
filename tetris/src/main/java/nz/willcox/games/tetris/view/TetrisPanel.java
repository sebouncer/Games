package nz.willcox.games.tetris.view;

import javax.swing.*;

public interface TetrisPanel {
    void initialise();
    JPanel getPanel();
    void destroy();
}
