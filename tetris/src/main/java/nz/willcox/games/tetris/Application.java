package nz.willcox.games.tetris;

import javax.inject.Inject;

public class Application {

    private final Controller controller;

    @Inject
    public Application(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        final TetrisComponent tetrisComponent = DaggerTetrisComponent.create();

        tetrisComponent.getApplication().initialise();
    }

    private void initialise() {
        controller.initialise();
    }
}
