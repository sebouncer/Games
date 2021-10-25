package nz.willcox.games.tetris.model.game.shape;

import nz.willcox.games.tetris.model.EventListener;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class NextShape extends EventListener {

//    private final List<ShapeBlock> shapeBlocks;
//    private LocationPoint rotateLocationPoint;
    private TetrisShape tetrisShape;

    @Inject
    public NextShape() {
//        this.shapeBlocks = new ArrayList<>();
    }

    public TetrisShape getTetrisShape() {
        return tetrisShape;
    }

    public void setTetrisShape(TetrisShape tetrisShape) {
        this.tetrisShape = tetrisShape;
        triggerListeners();
    }

//    public void setNewShapeBlocks(List<ShapeBlock> newShapeBlocks) {
//        this.shapeBlocks.addAll(newShapeBlocks);
//        triggerListeners();
//    }

//    public void removeBlocks() {
//        this.shapeBlocks.clear();
//    }
//
//    public List<ShapeBlock> getShapeBlocks() {
//        return shapeBlocks;
//    }
//
//    public LocationPoint getRotateLocationPoint() {
//        return rotateLocationPoint;
//    }
//
//    public void setRotateLocationPoint(LocationPoint rotateLocationPoint) {
//        this.rotateLocationPoint = rotateLocationPoint;
//    }
}
