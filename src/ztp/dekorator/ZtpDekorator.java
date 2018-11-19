/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztp.dekorator;

/**
 *
 * @author Wojtek
 */
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class ZtpDekorator {
    public static void main(String[] arrstring) {
        JFrame jFrame = new JFrame("Chess");
        jFrame.setDefaultCloseOperation(3);
        Chessboard chessboard = new Chessboard();
        JToolBar jToolBar = new JToolBar();
        chessboard.undo = new JButton(new ImageIcon("undo.png"));
        chessboard.redo = new JButton(new ImageIcon("redo.png"));
        chessboard.undo.addActionListener(chessboard.new UndoButton());
        chessboard.redo.addActionListener(chessboard.new RedoButton());
        chessboard.undo.setEnabled(false);
        chessboard.redo.setEnabled(false);
        jToolBar.add(chessboard.undo);
        jToolBar.add(chessboard.redo);
        jFrame.add((Component)jToolBar, "First");
        jFrame.add(chessboard);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
