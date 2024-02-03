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

    private static final LineBorder ENABLED_BORDER = new LineBorder(Color.BLACK);
    private static final LineBorder DISABLED_BORDER = new LineBorder(Color.GRAY);
    private static final LineBorder ENABLED_BORDER_BOLD = new LineBorder(Color.BLACK, 2);
    private static final LineBorder DISABLED_BORDER_BOLD = new LineBorder(Color.GRAY, 2);

    public SubGame(Game game, Pos subGamePos) {
        map = new HashMap<>();
        setLayout(new GridLayout(3, 3));

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
            cell.setBorder(ENABLED_BORDER);
            cell.setHorizontalAlignment(JLabel.CENTER);
            add(cell);
        }

        enableForPlay();
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
        this.setBorder(ENABLED_BORDER_BOLD);
        for (Component c : getComponents()) {
            c.setForeground(Color.BLACK);
            if (c instanceof JComponent jc) {
                jc.setBorder(ENABLED_BORDER);
            }
        }
    }

    public void disableForPlay() {
        disabled = true;
        this.setBorder(DISABLED_BORDER_BOLD);
        for (Component c : getComponents()) {
            c.setForeground(Color.GRAY);
            if (c instanceof JComponent jc) {
                jc.setBorder(DISABLED_BORDER);
            }
        }
    }

    private void checkWinner(Pos pos) {
        if (new HasWinner(map::get).apply(pos)) {
            winner = map.get(pos);
        }
    }
}
