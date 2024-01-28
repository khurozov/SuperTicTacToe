package uz.khurozov.supertictactoe.component;

import uz.khurozov.supertictactoe.HasWinner;
import uz.khurozov.supertictactoe.constant.Player;
import uz.khurozov.supertictactoe.constant.Pos;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class SubGame extends JComponent {
    private Player winner;
    private final HashMap<Pos, Player> map;
    private boolean disabled;

    public SubGame(Game game, Pos subGamePos) {
        disabled = false;
        setLayout(new GridLayout(3, 3));
        map = new HashMap<>();

        for (Pos pos : Pos.values()) {
            JLabel cell = new JLabel();
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (disabled || !cell.getText().isEmpty()) return;

                    cell.setText(game.getPlayer().name());
                    map.put(pos, game.getPlayer());

                    disableForPlay();
                    checkWinner(pos);

                    if (hasWinner()) {
                        game.checkWinner(subGamePos);
                    } else {
                        game.enableNextSubGame(pos);
                    }
                }
            });
            cell.setBorder(new LineBorder(Color.BLACK));
            cell.setHorizontalAlignment(JLabel.CENTER);
            add(cell);
        }
    }

    public boolean isPlayable() {
        return !hasWinner() && map.size() != 9;
    }

    public boolean hasWinner() {
        return winner != null;
    }

    public Player getWinner() {
        return winner;
    }

    public void enableForPlay() {
        if (!isPlayable()) return;

        disabled = false;
        for (Component c : getComponents()) {
            c.setBackground(Color.YELLOW);
        }
    }

    public void disableForPlay() {
        disabled = true;
        for (Component c : getComponents()) {
            c.setBackground(Color.BLUE);
        }
    }

    private void checkWinner(Pos pos) {
        if (new HasWinner(map::get).apply(pos)) {
            winner = map.get(pos);
        }
    }
}
