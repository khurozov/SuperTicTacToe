package uz.khurozov.supertictactoe.component;

import uz.khurozov.supertictactoe.HasWinner;
import uz.khurozov.supertictactoe.constant.Player;
import uz.khurozov.supertictactoe.constant.Pos;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Game extends JComponent {
    private static final String TITLE = "Super Tic-Tac-Toe";
    private Player player;
    private final HashMap<Pos, SubGame> subGameMap;
    private final JFrame frame;

    public Game(JFrame frame) {
        player = Player.X;
        setLayout(new GridLayout(3, 3));
        subGameMap = new HashMap<>();
        this.frame = frame;
        updateFrameTitle();

        for (Pos pos : Pos.values()) {
            SubGame subGame = new SubGame(this, pos);
            subGameMap.put(pos, subGame);
            add(subGame);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void enableNextSubGame(Pos pos) {
        player = player.next();
        updateFrameTitle();

        SubGame subGame = subGameMap.get(pos);

        if (subGame.isPlayable()) {
            subGameMap.values().forEach(SubGame::disableForPlay);
            subGame.enableForPlay();
        } else {
            subGameMap.values().forEach(SubGame::enableForPlay);
        }
    }

    public void checkWinner(Pos pos) {
        if (new HasWinner(p -> subGameMap.get(p).getWinner()).apply(pos)) {
            JOptionPane.showMessageDialog(frame, "Winner is " + subGameMap.get(pos).getWinner(), "Game over", JOptionPane.PLAIN_MESSAGE);
            frame.dispose();
        } else {
            enableNextSubGame(pos);
        }
    }

    public void updateFrameTitle() {
        frame.setTitle(TITLE + " (moves: "+player+")");
    }
}
