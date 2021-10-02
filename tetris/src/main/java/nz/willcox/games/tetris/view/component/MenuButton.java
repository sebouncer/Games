package nz.willcox.games.tetris.view.component;

import java.awt.*;

public class MenuButton {

    private static final int BUTTON_SELECTED = 1;
    private static final int BUTTON_NOT_SELECTED = 0;

    private final String buttonText;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Runnable action;
    private int selected = 0;

    private MenuButton(MenuButtonBuilder builder) {
        this.buttonText = builder.buttonText;
        this.x = builder.x;
        this.y = builder.y;
        this.width = builder.width;
        this.height = builder.height;
        this.action = builder.action;
    }

    public Runnable getAction() {
        return action;
    }

    public void setButtonSelected() {
        this.selected = BUTTON_SELECTED;
    }

    public void setButtonNotSelected() {
        this.selected = BUTTON_NOT_SELECTED;
    }

    public void paintComponent(Graphics g) {

        if (selected == BUTTON_SELECTED) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.CYAN);
        }
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.setColor(Color.BLACK);

        drawStringCenter(g, x, y, width, height, buttonText);
    }

    private void drawStringCenter(Graphics g, int x, int y, int width, int height, String text) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        // Determine the X coordinate for the text
        int centerX = x + (width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int centerY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Draw the String
        g.drawString(text, centerX, centerY);
    }

    public static class MenuButtonBuilder {
        private String buttonText;
        private int x;
        private int y;
        private int width;
        private int height;
        private Runnable action;

        public MenuButtonBuilder withText(String buttonText) {
            this.buttonText = buttonText;
            return this;
        }

        public MenuButtonBuilder withBounds(
                int x,
                int y,
                int width,
                int height
        ) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            return this;
        }

        public MenuButtonBuilder withAction(Runnable action) {
            this.action = action;
            return this;
        }

        public MenuButton build() {
            return new MenuButton(this);
        }
    }
}
