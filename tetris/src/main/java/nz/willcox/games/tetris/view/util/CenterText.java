package nz.willcox.games.tetris.view.util;

import java.awt.*;

public class CenterText {

    public static void drawStringCenter(Graphics g, int x, int y, int width, int height, String text) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        // Determine the X coordinate for the text
        int centerX = x + (width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int centerY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Draw the String
        g.drawString(text, centerX, centerY);
    }
}
