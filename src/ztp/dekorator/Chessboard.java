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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Chessboard
extends JPanel {
    public static final int ZEROX = 23;
    public static final int ZEROY = 7;
    private HashMap<Point, IPiece> board = new HashMap();
    private Image image;
    private IPiece dragged = null;
    AffineTransform draggedTransform = null;
    JButton undo;
    JButton redo;
    private Point mouse = null;

    public void drop(IPiece iPiece, int n, int n2) {
        this.repaint();
        iPiece.moveTo(n, n2);
        this.board.put(new Point(n, n2), iPiece);
    }

    public IPiece take(int n, int n2) {
        this.repaint();
        return this.board.remove(new Point(n, n2));
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(this.image, 0, 0, null);
        for (Map.Entry<Point, IPiece> entry : this.board.entrySet()) {
            Point point = entry.getKey();
            IPiece iPiece = entry.getValue();
            iPiece.draw((Graphics2D)graphics);
        }
        if (this.mouse != null && this.dragged != null) {
            this.dragged.draw((Graphics2D)graphics);
        }
    }

    Chessboard() {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(23.0, 7.0);
        affineTransform.scale(32.0, 32.0);
        this.board.put(new Point(0, 2), new AffineDecorator(new Piece(11, 0, 2), affineTransform));
        this.board.put(new Point(0, 6), new AffineDecorator(new Piece(0, 0, 6), affineTransform));
        this.board.put(new Point(1, 4), new AffineDecorator(new Piece(6, 1, 4), affineTransform));
        this.board.put(new Point(1, 5), new AffineDecorator(new Piece(5, 1, 5), affineTransform));
        this.board.put(new Point(3, 7), new AffineDecorator(new Piece(1, 3, 7), affineTransform));
        this.board.put(new Point(4, 3), new AffineDecorator(new Piece(6, 4, 3), affineTransform));
        this.board.put(new Point(4, 4), new AffineDecorator(new Piece(7, 4, 4), affineTransform));
        this.board.put(new Point(5, 4), new AffineDecorator(new Piece(6, 5, 4), affineTransform));
        this.board.put(new Point(5, 6), new AffineDecorator(new Piece(0, 5, 6), affineTransform));
        this.board.put(new Point(6, 5), new AffineDecorator(new Piece(0, 6, 5), affineTransform));
        this.board.put(new Point(7, 4), new AffineDecorator(new Piece(0, 7, 4), affineTransform));
        this.image = new ImageIcon("board3.png").getImage();
        this.setPreferredSize(new Dimension(this.image.getWidth(null), this.image.getHeight(null)));
        this.addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Chessboard.this.dragged = Chessboard.this.take((mouseEvent.getX() - 23) / 32, (mouseEvent.getY() - 7) / 32);
                Chessboard.this.draggedTransform = new AffineTransform();
                Chessboard.this.dragged = new AffineDecorator(Chessboard.this.dragged, Chessboard.this.draggedTransform);
                Chessboard.this.mouse = mouseEvent.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                Chessboard.this.drop(Chessboard.this.dragged.getDecorated(), (mouseEvent.getX() - 23) / 32, (mouseEvent.getY() - 7) / 32);
                Chessboard.this.dragged = null;
                Chessboard.this.undo.setEnabled(true);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter(){

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                Chessboard.this.draggedTransform.setToTranslation((double)mouseEvent.getX() - Chessboard.this.mouse.getX(), (double)mouseEvent.getY() - Chessboard.this.mouse.getY());
                Chessboard.this.repaint();
            }
        });
    }

    class RedoButton
    implements ActionListener {
        RedoButton() {
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("REDO");
        }
    }

    class UndoButton
    implements ActionListener {
        UndoButton() {
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("UNDO");
            Chessboard.this.redo.setEnabled(true);
        }
    }

}
