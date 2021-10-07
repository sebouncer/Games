package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.List;

import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;

public class CurrentShapeMovementService {

    private static final int DOWNWARDS_MOVEMENT = Constants.BLOCK_HEIGHT/4;

    @Inject
    public CurrentShapeMovementService() {}

    public boolean willCollideOnMove(GameData gameData) {
        final CurrentShape currentShape = gameData.getCurrentShape();

        for (ShapeBlock shapeBlock : currentShape.getShapeBlocks()) {
            final int newLocationYPoint = getNewLocationYPoint(shapeBlock);
            boolean collision = checkCollisions(gameData.getRowData(), shapeBlock.getLocationPoint().getTopX(), newLocationYPoint);
            if (collision) {
                return true;
            }
        }
        return false;
    }

    public void moveCurrentShapeDown(GameData gameData) {
        final CurrentShape currentShape = gameData.getCurrentShape();

        boolean hasCollided = false;

        for (ShapeBlock shapeBlock : currentShape.getShapeBlocks()) {
            final int newLocationYPoint = getNewLocationYPoint(shapeBlock);
            boolean collision = checkCollisions(gameData.getRowData(), shapeBlock.getLocationPoint().getTopX(), newLocationYPoint);
            if (collision) {
                hasCollided = true;
            }
            System.out.println(" Collision = " + collision);
        }

        if (hasCollided) {
            // If any block outside the top of grid, then game over
            saveCurrentShapeIntoGrid(gameData.getCurrentShape(), gameData.getRowData());
            gameData.getCurrentShape().removeBlocks();
        } else {
            for (ShapeBlock shapeBlock : currentShape.getShapeBlocks()) {
                final int newLocationYPoint = getNewLocationYPoint(shapeBlock);
                shapeBlock.getLocationPoint().setTopY(newLocationYPoint);
            }
        }
        currentShape.triggerListeners();
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

    private int getNewLocationYPoint(ShapeBlock shapeBlock) {
        final LocationPoint locationPoint = shapeBlock.getLocationPoint();
        return locationPoint.getTopY() + DOWNWARDS_MOVEMENT;
    }

    private boolean checkCollisions(
            List<Row> rowData,
            int newLocationXPoint,
            int newLocationYPoint
    ) {
        final int column = getColumn(newLocationXPoint);
        final int row = getRow(newLocationYPoint);

        System.out.print("row = " +row + "   column = " + column);

//        if (column < 0 || column >= NUM_COLUMNS || row < 0) {
        if (row < 0) {
            return true;
        }

        if (row >= NUM_ROWS) {
            return false;
        }

        final Block block = rowData.get(row).getBlocks().get(column);
        if (block != BlockColours.EMPTY_BLOCK) {
            return true;
        }

        return false;
    }

    private int getColumn(int topX) {
        return topX/Constants.BLOCK_WIDTH;
    }

    private int getRow(int topY) {
        return NUM_ROWS-1 - (topY-1 + Constants.BLOCK_HEIGHT)/Constants.BLOCK_HEIGHT;
    }
}
