package uz.khurozov.supertictactoe.constant;

public enum Player {
    X, O;

    public Player next() {
        return this == X ? O : X;
    }
}
