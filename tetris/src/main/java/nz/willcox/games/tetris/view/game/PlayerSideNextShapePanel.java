package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.Listener;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.NextShape;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.view.util.CenterText;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static nz.willcox.games.tetris.Constants.MID_TOP_X;
import static nz.willcox.games.tetris.Constants.MID_TOP_Y;

public class PlayerSideNextShapePanel extends JPanel implements Listener {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    private static final int BORDER_WIDTH = 2;
    private static final int TITLE_HEIGHT = 20;
    private static final String NEXT_SHAPE = "NEXT SHAPE";
    private static final int BLOCK_HEIGHT = 10;
    private static final int BLOCK_WIDTH = 10;

    private final GameData gameData;
    private final PlayerBlockColours playerBlockColours;

    public PlayerSideNextShapePanel(
            GameData gameData,
            PlayerBlockColours playerBlockColours
    ) {
        this.gameData = gameData;
        this.playerBlockColours = playerBlockColours;

        setSize(WIDTH, HEIGHT);
        setLayout(null);
        initialise();
    }

    public void initialise() {
        gameData.getNextShape().addListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBorder(g);
        drawTitle(g);
        drawNextShape(g);
    }

    private void drawNextShape(Graphics g) {

        final NextShape nextShape = gameData.getNextShape();
        final List<ShapeBlock> shapeBlocks = nextShape.getTetrisShape().getShapeBlocks();
        for (ShapeBlock shapeBlock : shapeBlocks) {
            final LocationPoint locationPoint = shapeBlock.getLocationPoint();
            final LocationPoint blockPositionLocationPoint = new LocationPoint.Builder()
                    .topX(getNewX(locationPoint))
                    .topY(getNewY(locationPoint))
                    .build();
            drawBlock(g, shapeBlock.getBlock(), blockPositionLocationPoint, BLOCK_WIDTH, BLOCK_HEIGHT, 1);
        }
    }

    private int getNewX(LocationPoint locationPoint) {
        return (locationPoint.getTopX() - MID_TOP_X) / Constants.BLOCK_WIDTH * BLOCK_WIDTH + getMidOfThisPanel() - BLOCK_WIDTH/2;
    }

    private int getNewY(LocationPoint locationPoint) {
        return (locationPoint.getTopY() - MID_TOP_Y) / Constants.BLOCK_HEIGHT * BLOCK_HEIGHT + getMidHeightOfThisPanel();
    }

    private int getMidOfThisPanel() {
        return WIDTH/2;
    }

    private int getMidHeightOfThisPanel() {
        return TITLE_HEIGHT + (HEIGHT - TITLE_HEIGHT)/2;
    }

    private void drawBlock(
            Graphics g,
            Block block,
            LocationPoint locationPoint,
            int blockWidth,
            int blockHeight,
            int blockBorderWidth
    ) {
        g.setColor(playerBlockColours.getBlockBorderColour(block));
        g.fillRect(
                locationPoint.getTopX() - blockBorderWidth,
                locationPoint.getTopY() - blockBorderWidth,
                blockWidth + (2*blockBorderWidth),
                blockHeight + (2*blockBorderWidth)
        );
        g.setColor(playerBlockColours.getBlockColour(block));
        g.fillRect(
                locationPoint.getTopX() + blockBorderWidth,
                locationPoint.getTopY() + blockBorderWidth,
                blockWidth - (2*blockBorderWidth),
                blockHeight -(2*blockBorderWidth)
        );
    }

    private void drawTitle(Graphics g) {
        g.setColor(Color.BLACK);
        CenterText.drawStringCenter(g, 0, BORDER_WIDTH, WIDTH, TITLE_HEIGHT, NEXT_SHAPE);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(BORDER_WIDTH, BORDER_WIDTH, WIDTH - 2*BORDER_WIDTH, TITLE_HEIGHT - BORDER_WIDTH);
        g.fillRect(BORDER_WIDTH, TITLE_HEIGHT + BORDER_WIDTH, WIDTH - 2*BORDER_WIDTH, HEIGHT - (TITLE_HEIGHT + 2*BORDER_WIDTH));
    }

    @Override
    public void eventTrigger() {
        repaint();
    }

    public static class Factory {
        public PlayerSideNextShapePanel create(
                GameData gameData
        ) {
            final PlayerBlockColours playerBlockColours = new PlayerBlockColours();
            return new PlayerSideNextShapePanel(gameData, playerBlockColours);
        }
    }
}
