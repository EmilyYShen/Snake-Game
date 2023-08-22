package org.cis120.snake;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 *
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
    private Snake snake;
    private Apple apple;
    private int score;

    private boolean playing = false; // whether the game is running
    private JLabel status; // Current status text (i.e. Running...)

    // Border constants
    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 400;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 100;

    public GameCourt(JLabel status) {
        snake = new Snake();
        spawnApple();

        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();

        setFocusable(true);

        // This key listener allows the square to move as long
        // as an arrow key is pressed, by changing the square's
        // velocity accordingly. (The tick method below actually
        // moves the square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT &&
                        snake.getDirection() != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                    snake.getSnake().getLast().setDirection(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT &&
                        snake.getDirection() != Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                    snake.getSnake().getLast().setDirection(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN &&
                        snake.getDirection() != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                    snake.getSnake().getLast().setDirection(Direction.DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_UP &&
                        snake.getDirection() != Direction.DOWN) {
                    snake.setDirection(Direction.UP);
                    snake.getSnake().getLast().setDirection(Direction.UP);
                }
            }
        });
        this.status = status;
    }

    /**
     * Reset the game to its initial state.
     */
    public void reset() {
        spawnApple();

        snake.getSnake().clear();
        snake.setDirection(Direction.RIGHT);
        snake.add();

        playing = true;
        score = 0;
        status.setText("Score: " + score);
        status.repaint();

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            snake.move(snake.getDirection());

            if (snake.getHead().collide(apple)) {
                snake.add();
                spawnApple();
                score += 1;
                status.setText("Score: " + score);
                status.repaint();
            }

            checkLose();

            // update the display
            repaint();
        }
    }

    public void checkLose() {
        boolean lost = false;

        if (snake.getHead().getPos().x < 0 ||
                snake.getHead().getPos().x > Tile.ROW ||
                snake.getHead().getPos().y < 0 ||
                snake.getHead().getPos().y > Tile.COL) {
            lost = true;
            playing = false;
            status.setText("You lost!");
        }

        for (int i = 1; i < snake.getSnake().size() - 4; i++) {
            if (snake.getHead().collide(snake.getSnake().get(i))) {
                lost = true;
                playing = false;
                status.setText("You lost!");
            }
        }

        try {
            if (lost) {
                writeHighScore();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void spawnApple() {
        apple = new Apple(null);

        Random random = new Random();
        int x = random.nextInt(Tile.ROW);
        int y = random.nextInt(Tile.COL);
        Point newPoint = new Point(x, y);

        ArrayList<Point> snakeObjPos = new ArrayList<>();

        for (SnakeBody snake : snake.getSnake()) {
            snakeObjPos.add(snake.getPos());
        }

        while (snakeObjPos.contains(newPoint)) {
            x = random.nextInt(Tile.ROW);
            y = random.nextInt(Tile.COL);
            newPoint = new Point(x, y);
        }
        apple.setPos(newPoint);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        apple.draw(g);
        Tile.draw(g);
        snake.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }

    public int readHighScore() throws IOException {
        FileReader file;
        BufferedReader reader = null;
        try {
            file = new FileReader("HighScore.txt");
            reader = new BufferedReader(file);
            String l = reader.readLine();
            String[] arr = l.split(" : ");
            return Integer.parseInt(arr[1]);
        } catch (IOException e) {
            return -1;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException called in readHighScore");
            }
        }
    }

    public void writeHighScore() throws IOException {
        if (score > readHighScore()) {
            String name = JOptionPane
                    .showInputDialog("Congratulations!  Enter your name to save your highscore: ");
            String highScore = name + " : " + score + "\n";

            File score = new File("HighScore.txt");
            if (!score.exists()) {
                try {
                    score.createNewFile();
                } catch (IOException e) {
                    System.out.println("IOException called in writeHighScore!");
                }
            }

            FileWriter write = new FileWriter("HighScore.txt", true);
            BufferedWriter writer = new BufferedWriter(write);
            try {
                writer.append(highScore);
                writer.flush();
            } catch (Exception e) {

            } finally {
                try {
                    if (write != null) {
                        write.close();
                    }
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }
}