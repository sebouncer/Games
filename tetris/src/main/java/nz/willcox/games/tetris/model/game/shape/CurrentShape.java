package nz.willcox.games.tetris.model.game.shape;

import nz.willcox.games.tetris.model.EventListener;

import javax.inject.Inject;

public class CurrentShape extends EventListener {

    private Shape shape;

    @Inject
    public CurrentShape() {}

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
        triggerListeners();
    }
}
