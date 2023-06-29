package org.cis120.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class SnakeBody extends GameObj {

    public SnakeBody(Point p) {
        super(0, 0, 10, 10, 10, 10, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, p);
        gridPosition = p;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(getPos().x * 10, getPos().y * 10, 10, 10);
    }
}
