package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.NextShape;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static nz.willcox.games.tetris.Constants.NUM_ROWS;

public class CurrentShapeMovementService {

    private final GameCreator gameCreator;
    private final ShapeMovementService shapeMovementService;
    private final RandomNextShapeService randomNextShapeService;

    @Inject
    public CurrentShapeMovementService(
            GameCreator gameCreator,
            ShapeMovementService shapeMovementService,
            RandomNextShapeService randomNextShapeService
    ) {
        this.gameCreator = gameCreator;
        this.shapeMovementService = shapeMovementService;
        this.randomNextShapeService = randomNextShapeService;
    }

    public void moveCurrentShapeDown(GameData gameData) {
        final CurrentShape currentShape = gameData.getCurrentShape();

        boolean willCollide = shapeMovementService.willCollideOnMoveDown(gameData);

        if (willCollide) {
            // If any block outside the top of grid, then game over
            saveCurrentShapeIntoGrid(gameData.getCurrentShape(), gameData.getRowData());

            loadNextShapeIntoCurrent(gameData);

            checkForLines(gameData.getRowData());
            // Check for line/s
        } else {
            shapeMovementService.moveDown(currentShape);
        }
        currentShape.triggerListeners();
    }

    private void loadNextShapeIntoCurrent(GameData gameData) {
        final CurrentShape currentShape = gameData.getCurrentShape();
        final NextShape nextShape = gameData.getNextShape();
        final TetrisShape nextTetrisShape = nextShape.getTetrisShape();
        currentShape.setTetrisShape(nextTetrisShape);
        final TetrisShape randomShape = randomNextShapeService.createRandomShape();
        nextShape.setTetrisShape(randomShape);
    }

    private void checkForLines(List<Row> rowData) {
        final List<Row> fillRows = new ArrayList<>();
        for (Row row : rowData) {
            boolean fillLine = true;
            for (Block block : row.getBlocks()) {
                if (block == BlockColours.EMPTY_BLOCK) {
                    fillLine = false;
                    break;
                }
            }
            if (fillLine) {
                fillRows.add(row);
            }
        }
        for (Row row : fillRows) {
            rowData.remove(row);
            rowData.add(gameCreator.createRow());
        }
    }

    private void saveCurrentShapeIntoGrid(
            CurrentShape currentShape,
            List<Row> rowData
    ) {
        for (ShapeBlock shapeBlock : currentShape.getShapeBlocks()) {
            final int column = getColumn(shapeBlock.getLocationPoint().getTopX());
            final int row = getRow(shapeBlock.getLocationPoint().getTopY());
            rowData.get(row).getBlocks().set(column, shapeBlock.getBlock());
        }
    }

    private int getColumn(int topX) {
        return topX/Constants.BLOCK_WIDTH;
    }

    private int getRow(int topY) {
        return NUM_ROWS-1 - (topY-1 + Constants.BLOCK_HEIGHT)/Constants.BLOCK_HEIGHT;
    }
}
