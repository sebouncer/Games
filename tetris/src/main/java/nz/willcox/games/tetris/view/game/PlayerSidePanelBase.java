package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.view.util.CenterText;

import java.awt.*;

public class PlayerSidePanelBase {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    private static final int BORDER_WIDTH = 2;
    private static final int TITLE_HEIGHT = 20;

    public void drawTitle(Graphics g, String title) {
        g.setColor(Color.BLACK);
        CenterText.drawStringCenter(g, 0, BORDER_WIDTH, WIDTH, TITLE_HEIGHT, title);
    }

    public void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(BORDER_WIDTH, BORDER_WIDTH, WIDTH - 2*BORDER_WIDTH, TITLE_HEIGHT - BORDER_WIDTH);
        g.fillRect(BORDER_WIDTH, getContentStartY(), WIDTH - 2*BORDER_WIDTH, getContentHeight());
    }

    public int getMidHeightOfThisPanel() {
        return TITLE_HEIGHT + (HEIGHT - TITLE_HEIGHT)/2;
    }

    public void drawContentStringCenter(
            Graphics g,
            int contentNumber
    ) {
        drawContentStringCenter(g, String.valueOf(contentNumber));
    }

    public void drawContentStringCenter(
            Graphics g,
            String contentString
    ) {
        CenterText.drawStringCenter(g, 0, getContentStartY(), WIDTH, getContentHeight(), contentString);
    }

    private int getContentStartY() {
        return TITLE_HEIGHT + BORDER_WIDTH;
    }

    private int getContentHeight() {
        return HEIGHT - (TITLE_HEIGHT + 2*BORDER_WIDTH);
    }
}
