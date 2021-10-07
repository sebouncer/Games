package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.Listener;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.view.controls.PlayerControls;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static nz.willcox.games.tetris.Constants.BLOCK_HEIGHT;
import static nz.willcox.games.tetris.Constants.BLOCK_WIDTH;
import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;

public class PlayerPanel extends JPanel implements Listener {

    private static final int BLOCK_BORDER_WIDTH = 2;
    public static final int WIDTH = BLOCK_WIDTH * NUM_COLUMNS;
    public static final int HEIGHT = BLOCK_HEIGHT * NUM_ROWS;

    private final GameData gameData;
    private final PlayerControls playerControls;

    public PlayerPanel(
            GameData gameData,
            PlayerControls playerControls
    ) {
        this.gameData = gameData;
        this.playerControls = playerControls;
        gameData.getCurrentShape().addListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBlocks(g);
        drawCurrentShape(g);
    }

    private void drawCurrentShape(Graphics g) {
        final CurrentShape currentShape = gameData.getCurrentShape();
        if (currentShape != null) {
            final List<ShapeBlock> shapeBlocks = currentShape.getShapeBlocks();
            for (ShapeBlock shapeBlock : shapeBlocks) {
                final Block block = shapeBlock.getBlock();

                final LocationPoint locationPoint = shapeBlock.getLocationPoint();

                drawBlock(g, block, locationPoint);
            }
        }
    }

    private void drawBlocks(Graphics g) {
        final List<Row> rowData = gameData.getRowData();
        for (int i = 0; i < rowData.size(); i++) {
            final List<Block> blocks = rowData.get(i).getBlocks();
            for (int j = 0; j < blocks.size(); j++) {

                final LocationPoint locationPoint = new LocationPoint.Builder()
                        .topX(j*BLOCK_WIDTH)
                        .topY(HEIGHT - (i+1)*BLOCK_HEIGHT)
                        .build();
                drawBlock(g, blocks.get(j), locationPoint);
            }
        }
    }

    private void drawBlock(Graphics g, Block block, LocationPoint locationPoint) {
        g.setColor(getBlockBorderColour(block));
        g.fillRect(
                locationPoint.getTopX(),
                locationPoint.getTopY(),
                BLOCK_WIDTH,
                BLOCK_HEIGHT
        );
        g.setColor(getBlockColour(block));
        g.fillRect(
                locationPoint.getTopX() + BLOCK_BORDER_WIDTH,
                locationPoint.getTopY() + BLOCK_BORDER_WIDTH,
                BLOCK_WIDTH - (2*BLOCK_BORDER_WIDTH),
                BLOCK_HEIGHT -(2*BLOCK_BORDER_WIDTH)
        );
    }

    private Color getBlockColour(Block block) {
        if (block == BlockColours.YELLOW_BLOCK) {
            return Color.YELLOW;
        }
        if (block == BlockColours.GREEN_BLOCK) {
            return Color.GREEN;
        }
        return Color.WHITE;
    }

    private Color getBlockBorderColour(Block block) {
        if (block == BlockColours.YELLOW_BLOCK || block == BlockColours.GREEN_BLOCK) {
            return Color.BLACK;
        }
        return Color.ORANGE;
    }

    public void destroy() {
        gameData.getCurrentShape().removeListener(this);
    }

    @Override
    public void eventTrigger() {
        repaint();
    }

    public static class Factory {
        public PlayerPanel create(
                GameData gameData,
                PlayerControls playerControls
        ) {
            return new PlayerPanel(gameData, playerControls);
        }
    }
}
