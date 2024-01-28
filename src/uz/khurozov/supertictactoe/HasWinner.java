package uz.khurozov.supertictactoe;

import uz.khurozov.supertictactoe.constant.Player;
import uz.khurozov.supertictactoe.constant.Pos;

import java.util.function.Function;

public class HasWinner implements Function<Pos,Boolean> {
    private final Function<Pos, Player> func;

    public HasWinner(Function<Pos, Player> func) {
        this.func = func;
    }
    @Override
    public Boolean apply(Pos pos) {
        Player player = func.apply(pos);
        return switch (pos) {
            case TOP_LEFT ->
                    player == func.apply(Pos.TOP_CENTER) &&  player == func.apply(Pos.TOP_RIGHT)
                    || player == func.apply(Pos.CENTER) &&  player == func.apply(Pos.BOTTOM_RIGHT)
                    || player == func.apply(Pos.CENTER_LEFT) &&  player == func.apply(Pos.BOTTOM_LEFT);
            case TOP_CENTER ->
                    player == func.apply(Pos.TOP_LEFT) &&  player == func.apply(Pos.TOP_RIGHT)
                    || player == func.apply(Pos.CENTER) &&  player == func.apply(Pos.BOTTOM_CENTER);
            case TOP_RIGHT ->
                    player == func.apply(Pos.TOP_LEFT) &&  player == func.apply(Pos.TOP_CENTER)
                    || player == func.apply(Pos.CENTER) &&  player == func.apply(Pos.BOTTOM_LEFT)
                    || player == func.apply(Pos.CENTER_RIGHT) &&  player == func.apply(Pos.BOTTOM_RIGHT);
            case CENTER_LEFT ->
                    player == func.apply(Pos.TOP_LEFT) &&  player == func.apply(Pos.BOTTOM_LEFT)
                    || player == func.apply(Pos.CENTER) &&  player == func.apply(Pos.CENTER_RIGHT);
            case CENTER ->
                    player == func.apply(Pos.TOP_LEFT) &&  player == func.apply(Pos.BOTTOM_RIGHT)
                    || player == func.apply(Pos.TOP_CENTER) &&  player == func.apply(Pos.BOTTOM_CENTER)
                    || player == func.apply(Pos.TOP_RIGHT) &&  player == func.apply(Pos.BOTTOM_LEFT)
                    || player == func.apply(Pos.CENTER_LEFT) &&  player == func.apply(Pos.CENTER_RIGHT);
            case CENTER_RIGHT ->
                    player == func.apply(Pos.TOP_RIGHT) &&  player == func.apply(Pos.BOTTOM_RIGHT)
                    || player == func.apply(Pos.CENTER_LEFT) &&  player == func.apply(Pos.CENTER);
            case BOTTOM_LEFT ->
                    player == func.apply(Pos.TOP_LEFT) &&  player == func.apply(Pos.CENTER_LEFT)
                    || player == func.apply(Pos.CENTER) &&  player == func.apply(Pos.TOP_RIGHT)
                    || player == func.apply(Pos.BOTTOM_CENTER) &&  player == func.apply(Pos.BOTTOM_RIGHT);
            case BOTTOM_CENTER ->
                    player == func.apply(Pos.TOP_CENTER) &&  player == func.apply(Pos.CENTER)
                    || player == func.apply(Pos.BOTTOM_LEFT) &&  player == func.apply(Pos.BOTTOM_RIGHT);
            case BOTTOM_RIGHT ->
                    player == func.apply(Pos.TOP_RIGHT) &&  player == func.apply(Pos.CENTER_RIGHT)
                    || player == func.apply(Pos.TOP_LEFT) &&  player == func.apply(Pos.CENTER)
                    || player == func.apply(Pos.BOTTOM_LEFT) &&  player == func.apply(Pos.BOTTOM_CENTER);
        };
    }
}
