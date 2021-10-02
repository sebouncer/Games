package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.Block;
import nz.willcox.games.tetris.model.GameData;
import nz.willcox.games.tetris.model.Row;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GameCreator {

    private static final int BLOCKS_WIDE = 10;
    private static final int BLOCKS_HIGH = 40;
    private static final int INITIAL_SCORE = 0;

    private final BlocksCreator blocksCreator;

    @Inject
    public GameCreator(BlocksCreator blocksCreator) {
        this.blocksCreator = blocksCreator;
    }

    public GameData createGame() {
        final List<Row> rowData = createRows();
        return new GameData.Builder().score(INITIAL_SCORE).rowData(rowData).build();
    }

    private List<Row> createRows() {
        final List<Row> rowData = new ArrayList<>();
        for (int i = 0; i < BLOCKS_HIGH; i++) {
            rowData.add(createRow());
        }
        return rowData;
    }

    private Row createRow() {
        final List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < BLOCKS_WIDE; i++) {
            blocks.add(blocksCreator.getBlankBlock());
        }
        return new Row.Builder().blocks(blocks).build();
    }
}
