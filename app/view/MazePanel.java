package app.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import app.model.Node;

public class MazePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int timeSleep = 25;
    private JPanel[][] maze;
    private Color pathColor = new Color(232, 65, 24);
    private Color emptyPath = new Color(247, 241, 227);
    private Color wall = new Color(39, 60, 117);
    private Color end = new Color(247, 143, 179);

    public MazePanel(int matrix[][]) {
        maze = new JPanel[matrix.length][matrix.length];
        this.setLayout(new GridLayout(maze.length, maze.length));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                JPanel p = new JPanel();
                maze[i][j] = p;
                this.add(p);
                if (matrix[i][j] == 0) {
                    p.setBackground(wall);
                }
                if (matrix[i][j] == 3) {
                    maze[i][j].setBackground(pathColor);
                }
                if (matrix[i][j] == 1) {
                    p.setBackground(emptyPath);
                }
                if (matrix[i][j] == 4) {
                    p.setBackground(end);
                }
            }
        }
    }

    public void reload(int[][] matrix) {
        // maze = new JPanel[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // JPanel p = new JPanel();
                // maze[i][j] = p;
                maze[i][j].setBackground(emptyPath);
                if (matrix[i][j] == 0) {
                    maze[i][j].setBackground(wall);
                }
                if (matrix[i][j] == 3) {
                    maze[i][j].setBackground(pathColor);
                }
                if (matrix[i][j] == 4) {
                    maze[i][j].setBackground(end);
                }
            }
        }
    }

    public void move(ArrayList<Node> path) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < path.size() - 1; i++) {
                    int row1 = (int) path.get(i).getY();
                    int row2 = (int) path.get(i + 1).getY();
                    int col1 = (int) path.get(i).getX();
                    int col2 = (int) path.get(i + 1).getX();
                    if (row1 == row2) {
                        for (int j = 0; j <= Math.max(col1, col2) - Math.min(col1, col2); j++) {
                            maze[row1][Math.min(col1, col2) + j].setBackground(pathColor);
                            try {
                                Thread.sleep(timeSleep);
                            } catch (InterruptedException e) {

                                e.printStackTrace();
                            }
                        }
                    }
                    if (col1 == col2) {
                        for (int j = 0; j <= Math.max(row1, row2) - Math.min(row1, row2); j++) {
                            maze[Math.min(row1, row2) + j][col1].setBackground(pathColor);
                            try {
                                Thread.sleep(timeSleep);
                            } catch (InterruptedException e) {

                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();
        ;

    }

    public void step(int x1, int y1, int x2, int y2) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                if (x1 == x2) {
                    int i = y1;
                    while (i < y2) {
                        maze[i + 1][x1].setBackground(new Color(46, 139, 87));

                        maze[i][x1].setBackground(emptyPath);
                        try {
                            Thread.sleep(timeSleep);
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                        i++;

                    }
                }
                if (y1 == y2) {
                    int i = x1;
                    while (i < x2) {
                        maze[y1][i + 1].setBackground(new Color(46, 139, 87));

                        maze[y1][i].setBackground(emptyPath);
                        try {
                            Thread.sleep(timeSleep);
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                        i++;

                    }
                }
            }
        }).start();
    }
}
