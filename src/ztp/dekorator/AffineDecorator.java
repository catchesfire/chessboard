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
/*
 * Decompiled with CFR 0_132.
 */
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

class AffineDecorator
extends Decorator {
    private AffineTransform transform;

    public AffineDecorator(IPiece iPiece, AffineTransform affineTransform) {
        super(iPiece);
        this.transform = affineTransform;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.transform(this.transform);
        this.piece.draw(graphics2D);
        graphics2D.setTransform(affineTransform);
    }
}