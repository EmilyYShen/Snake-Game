package org.cis120.snake;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Apple extends GameObj {
    public static final String FILE = "files/apple.png";
    public static final int VEL_X = 0;
    public static final int VEL_Y = 0;
    public static final int POS_X = 50;
    public static final int POS_Y = 50;
    public static final int SIZE = 10;

    private static BufferedImage image;

    public Apple(Point point) {
        super(
                VEL_X, VEL_Y, POS_X, POS_Y, SIZE, SIZE, GameCourt.COURT_WIDTH,
                GameCourt.COURT_HEIGHT, point
        );
        gridPosition = point;

        try {
            if (image == null) {
                image = ImageIO.read(new File(FILE));
            }
        } catch (IOException e) {
            System.out.println("Cannot read apple file");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getPos().x * 10, getPos().y * 10, width, height, null);
    }
}
