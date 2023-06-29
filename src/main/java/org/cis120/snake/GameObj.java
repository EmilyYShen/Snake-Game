package org.cis120.snake;

import java.awt.Graphics;
import java.awt.Point;

/**
 * An object in the game.
 *
 * Game objects exist in the game court. They have a position,
 * velocity, size and bounds. Their velocity controls how they
 * move; their position should always be within their bounds.
 */
public class GameObj {

    public int px;
    public int py;
    public int vx;
    public int vy;
    public int width;
    public int height;
    public int maxX;
    public int maxY;
    public Point gridPosition;
    public Direction dir;

    public GameObj(
            int vx, int vy, int px, int py,
            int width, int height, int courtWidth, int courtHeight, Point point
    ) {
        this.vx = vx;
        this.vy = vy;
        this.px = px;
        this.py = py;
        this.width = width;
        this.height = height;
        this.maxX = courtWidth - width;
        this.maxY = courtHeight - height;
        gridPosition = point;
    }

    /**
     * Moves the object by its velocity. Ensures that the object does
     * not go outside its bounds by clipping.
     */
    public void move() {
        px += vx;
        py += vy;

        clip();
    };

    /**
     * Prevents the object from going outside the bounds.
     */
    public void clip() {
        if (px < 0) {
            px = 0;
        } else if (px > maxX) {
            px = maxX;
        }
        if (py < 0) {
            py = 0;
        } else if (py > maxY) {
            py = maxY;
        }
    }

    /**
     * Determine whether this game object is currently hitting
     * another object.
     */
    public boolean collide(GameObj obj) {
        return getPos().equals(obj.getPos());
    }

    /**
     * Determine whether two game objects will collide in the
     * next time step, assuming that both objects continue with their
     * current velocity.
     */
    public boolean willCollide(GameObj obj) {
        int nx = px + vx;
        int ny = py + vy;
        int objX = obj.px + obj.vx;
        int objY = obj.py + obj.vy;
        return (nx + width >= objX
                && ny + height >= objY
                && objX + obj.width >= nx
                && objY + obj.height >= ny);
    }

    public boolean intersects(GameObj obj) {
        return getPos().equals(obj.getPos());
    }

    /**
     * Default draw method that provides how the object should be drawn
     * in the GUI. This method does not draw anything. Subclass should
     * override this method based on how their object should appear.
     */
    public void draw(Graphics g) {
    }

    public Direction getDirection() {
        return dir;
    }

    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    public Point getPos() {
        return gridPosition;
    }

    public void setPos(Point point) {
        gridPosition = point;
    }

}