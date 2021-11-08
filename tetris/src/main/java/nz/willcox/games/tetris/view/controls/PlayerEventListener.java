package nz.willcox.games.tetris.view.controls;

public interface PlayerEventListener {

    void leftAction();
    void downAction();
    void downActionReleased();
    void upAction();
    void rightAction();
    void buttonStart();
    void buttonOne();
    void buttonTwo();
}
