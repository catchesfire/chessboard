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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

class Piece
implements IPiece {
    private static final Image image = new ImageIcon("pieces4.png").getImage();
    private int index;
    private int x;
    private int y;

    public Piece(int n, int n2, int n3) {
        this.index = n;
        this.x = n2;
        this.y = n3;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, this.x, this.y, this.x + 1, this.y + 1, this.index * 32, 0, (this.index + 1) * 32, 32, null);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void moveTo(int n, int n2) {
        this.x = n;
        this.y = n2;
    }

    @Override
    public IPiece getDecorated() {
        return null;
    }
}
