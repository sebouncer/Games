package nz.willcox.games.tetris.model.game.shape;

import nz.willcox.games.tetris.model.EventListener;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CurrentShape extends EventListener {

    private final List<ShapeBlock> shapeBlocks;

    @Inject
    public CurrentShape() {
        this.shapeBlocks = new ArrayList<>();
    }

    public void setNewShapeBlocks(List<ShapeBlock> newShapeBlocks) {
        this.shapeBlocks.clear();
        this.shapeBlocks.addAll(newShapeBlocks);
        triggerListeners();
    }

    public List<ShapeBlock> getShapeBlocks() {
        return shapeBlocks;
    }
}
