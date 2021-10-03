package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.Shape;
import nz.willcox.games.tetris.view.controls.PlayerControls;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static nz.willcox.games.tetris.Constants.BLOCK_HEIGHT;
import static nz.willcox.games.tetris.Constants.BLOCK_WIDTH;
import static nz.willcox.games.tetris.Constants.NUM_BLOCKS_IN_COLUMN;
import static nz.willcox.games.tetris.Constants.NUM_BLOCKS_IN_ROW;

public class PlayerPanel extends JPanel {

    private static final int BLOCK_BORDER_WIDTH = 2;
    public static final int WIDTH = BLOCK_WIDTH * NUM_BLOCKS_IN_COLUMN;
    public static final int HEIGHT = BLOCK_HEIGHT * NUM_BLOCKS_IN_ROW;

    private final GameData gameData;
    private final PlayerControls playerControls;

    public PlayerPanel(
            GameData gameData,
            PlayerControls playerControls
    ) {
        this.gameData = gameData;
        this.playerControls = playerControls;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBlocks(g);
        drawCurrentShape(g);
    }

    private void drawCurrentShape(Graphics g) {
        final CurrentShape currentShape = gameData.getCurrentShape();
        if (currentShape != null) {
            final Shape shape = currentShape.getShape();
            shape.getBlocks();
        }
    }

    private void drawBlocks(Graphics g) {
        final List<Row> rowData = gameData.getRowData();
        for (int i = 0; i < rowData.size(); i++) {
            final List<Block> blocks = rowData.get(i).getBlocks();
            for (int j = 0; j < blocks.size(); j++) {
                g.setColor(Color.ORANGE);
                g.fillRect(
                        j*BLOCK_WIDTH,
                        HEIGHT - (i+1)*BLOCK_HEIGHT,
                        BLOCK_WIDTH,
                        BLOCK_HEIGHT
                );
                g.setColor(Color.WHITE);
                g.fillRect(
                        j*BLOCK_WIDTH+BLOCK_BORDER_WIDTH,
                        HEIGHT - (i+1)*BLOCK_HEIGHT + BLOCK_BORDER_WIDTH,
                        BLOCK_WIDTH-(2*BLOCK_BORDER_WIDTH),
                        BLOCK_HEIGHT-(2*BLOCK_BORDER_WIDTH)
                );
            }
        }
    }

    public void destroy() {

    }

    public static class Factory {
        public PlayerPanel create(
                PlayerControls playerControls
        ) {
            return new PlayerPanel(createGameData(), playerControls);
        }

        private GameData createGameData() {
            return new GameData.Builder()
                    .score(0)
                    .rowData(createRows())
                    .build();
        }

        private List<Row> createRows() {
            final ArrayList<Row> rows = new ArrayList<>(Constants.NUM_BLOCKS_IN_ROW);
            for (int i = 0; i < Constants.NUM_BLOCKS_IN_ROW; i++) {
                final Row row = new Row.Builder()
                        .blocks(createBlocks())
                        .build();
                rows.add(row);
            }
            return rows;
        }

        private List<Block> createBlocks() {
            final ArrayList<Block> blocks = new ArrayList<>(Constants.NUM_BLOCKS_IN_COLUMN);
            for (int i = 0; i < Constants.NUM_BLOCKS_IN_COLUMN; i++) {
                blocks.add(BlockColours.EMPTY_BLOCK);
            }
            return blocks;
        }
    }
}
