package org.cis120.snake;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<SnakeBody> snake;
    private SnakeBody head;
    private Direction dir;

    public Snake() {
        snake = new LinkedList<>();
        add();
    }

    public void move(Direction dir) {
        Point newPos = head.getPos();
        if (dir == Direction.LEFT) {
            newPos = new Point(head.getPos().x - 1, head.getPos().y);
        } else if (dir == Direction.RIGHT) {
            newPos = new Point(head.getPos().x + 1, head.getPos().y);
        } else if (dir == Direction.DOWN) {
            newPos = new Point(head.getPos().x, head.getPos().y + 1);
        } else if (dir == Direction.UP) {
            newPos = new Point(head.getPos().x, head.getPos().y - 1);
        }

        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).setPos(snake.get(i - 1).getPos());
        }

        head.setPos(newPos);
    }

    public void add() {
        if (snake.size() == 0) {
            snake.add(new SnakeBody(new Point(0, 0)));
            head = snake.getFirst();
        }
        Direction dir = snake.getLast().getDirection();
        Point tail = snake.getLast().getPos();
        Point newPart = head.getPos();

        if (dir == Direction.LEFT) {
            newPart = new Point(tail.x - 1, tail.y);
        } else if (dir == Direction.RIGHT) {
            newPart = new Point(tail.x + 1, tail.y);
        } else if (dir == Direction.DOWN) {
            newPart = new Point(tail.x, tail.y + 1);
        } else if (dir == Direction.UP) {
            newPart = new Point(tail.x, tail.y - 1);
        }
        snake.addLast(new SnakeBody(newPart));
    }

    public void draw(Graphics g) {
        for (SnakeBody snake : snake) {
            snake.draw(g);
        }
    }

    public SnakeBody getHead() {
        return head;
    }

    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    public Direction getDirection() {
        return dir;
    }

    public LinkedList<SnakeBody> getSnake() {
        return snake;
    }

    public void setSnake(LinkedList<SnakeBody> snake) {
        this.snake = snake;
    }

}
