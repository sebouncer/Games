package nz.willcox.games.tetris.model.game.shape;

import nz.willcox.games.tetris.model.EventListener;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class CurrentShape extends EventListener {

    private TetrisShape tetrisShape;

    @Inject
    public CurrentShape() { }

    public List<ShapeBlock> getShapeBlocks() {
        if (hasShape()) {
            return tetrisShape.getShapeBlocks();
        }
        return Collections.EMPTY_LIST;
    }

    public void setTetrisShape(TetrisShape nextTetrisShape) {
        this.tetrisShape = nextTetrisShape;
        triggerListeners();
    }

    public TetrisShape getTetrisShape() {
        return tetrisShape;
    }

    public boolean hasShape() {
        return tetrisShape != null
                && tetrisShape.getShapeBlocks() != null
                && tetrisShape.getShapeBlocks().size() > 0;
    }
}
