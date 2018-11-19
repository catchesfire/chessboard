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

class Decorator
implements IPiece {
    protected final IPiece piece;

    protected Decorator(IPiece iPiece) {
        this.piece = iPiece;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        this.piece.draw(graphics2D);
    }

    @Override
    public int getX() {
        return this.piece.getX();
    }

    @Override
    public int getY() {
        return this.piece.getY();
    }

    @Override
    public void moveTo(int n, int n2) {
        this.piece.moveTo(n, n2);
    }

    @Override
    public IPiece getDecorated() {
        return this.piece;
    }
}