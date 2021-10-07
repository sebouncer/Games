package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.NextShape;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;
import static nz.willcox.games.tetris.model.game.BlockColours.EMPTY_BLOCK;

public class GameCreator {

    private static final int INITIAL_SCORE = 0;
    private final RandomNextShapeService randomNextShapeService;

    @Inject
    public GameCreator(RandomNextShapeService randomNextShapeService) {
        this.randomNextShapeService = randomNextShapeService;
    }

    public GameData createGame() {

        final List<ShapeBlock> shapeBlocks = randomNextShapeService.getRandomNextShapeBlocks();

        final NextShape nextShape = new NextShape();
        nextShape.setNewShapeBlocks(shapeBlocks);

        final List<Row> rowData = createRows();
        return new GameData.Builder()
                .score(INITIAL_SCORE)
                .rowData(rowData)
                .currentShape(new CurrentShape())
                .nextShape(nextShape)
                .build();
    }

    private List<Row> createRows() {
        final List<Row> rowData = new ArrayList<>();
        for (int i = 0; i < NUM_ROWS; i++) {
            rowData.add(createRow());
        }
        return rowData;
    }

    private Row createRow() {
        final List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < NUM_COLUMNS; i++) {
            blocks.add(EMPTY_BLOCK);
        }
        return new Row.Builder().blocks(blocks).build();
    }
}
