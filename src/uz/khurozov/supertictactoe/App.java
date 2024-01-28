package uz.khurozov.supertictactoe;

import uz.khurozov.supertictactoe.component.Game;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(new Game(frame));
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
