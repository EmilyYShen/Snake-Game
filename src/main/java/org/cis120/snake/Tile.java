package org.cis120.snake;

import java.awt.*;

import javax.swing.JPanel;

public class Tile extends JPanel {

    public static final int ROW = GameCourt.COURT_WIDTH / 10;
    public static final int COL = GameCourt.COURT_HEIGHT / 10;
    private static Rectangle[][] tile = new Rectangle[ROW][COL];
    private Point pos;

    public Tile() {
        pos = new Point();
        tile = new Rectangle[ROW][COL];
    }

    public static void draw(Graphics g) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                g.drawRect(x, y, 10, 10);
                tile[j][i] = new Rectangle();
                x += 10;
            }
            x = 0;
            y += 10;
        }
    }
}
