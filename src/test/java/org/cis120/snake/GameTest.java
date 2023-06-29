package org.cis120.snake;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private SnakeBody head;
    private LinkedList<SnakeBody> snake;
    private Apple apple;

    public GameTest() {
        head = new SnakeBody(new Point(0, 1));
        snake = new LinkedList<SnakeBody>();
        snake.add(head);
        snake.add(new SnakeBody(new Point(0, 2)));
        apple = new Apple(new Point(10, 10));
    }

    @Test
    public void testCollisionWithSnake() {
        assertFalse(head.intersects(snake.get(1)));
        head = new SnakeBody(new Point(0, 2));
        assertTrue(head.intersects(snake.get(1)));
    }

    @Test
    public void testCollisionWithFood() {
        assertFalse(head.intersects(apple));
        apple = new Apple(new Point(0, 1));
        assertTrue(head.intersects(apple));
    }

    @Test
    public void testCollisionsWithWall() {
        ArrayList<Point> left = new ArrayList<>(15);
        for (int i = 0; i < left.size(); i++) {
            left.add(new Point(-1, i));
        }

        for (Point point : left) {
            assertFalse(head.equals(point));
        }

        ArrayList<Point> right = new ArrayList<>(15);
        for (int i = 0; i < right.size(); i++) {
            left.add(new Point(16, i));
        }

        for (Point point : right) {
            assertFalse(head.equals(point));
        }

        ArrayList<Point> top = new ArrayList<>(15);
        for (int i = 0; i < top.size(); i++) {
            left.add(new Point(i, -1));
        }

        for (Point point : top) {
            assertFalse(head.equals(point));
        }

        ArrayList<Point> bottom = new ArrayList<>(15);
        for (int i = 0; i < bottom.size(); i++) {
            left.add(new Point(i, 16));
        }

        for (Point point : bottom) {
            assertFalse(head.equals(point));
        }
    }
}
