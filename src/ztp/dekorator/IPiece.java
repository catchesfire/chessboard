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

interface IPiece {
    public static final int TILESIZE = 32;

    public void draw(Graphics2D var1);

    public int getX();

    public int getY();

    public void moveTo(int var1, int var2);

    public IPiece getDecorated();
}