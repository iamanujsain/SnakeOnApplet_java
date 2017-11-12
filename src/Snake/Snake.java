package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    int xDir, yDir;
    List<Point> snakePoints;
    private boolean isMoving, elongate;
    final int STARTSIZE = 20, STARTX = 120, STARTY = 150;

    /** Constructor of the class Sanke. */
    public Snake() {
        snakePoints = new ArrayList<Point>();
        xDir = 0;
        yDir = 0;
        isMoving = false;
        for (int i = 0; i < STARTSIZE; i++) {
            snakePoints.add(new Point(STARTX - 5 * i, STARTY));
        }
    }

    /** Makes the ArrayList named 'snakePoints' empty. */
    public void resset() {
        snakePoints.clear();
    }

    /** Draws the snake. */
    public void draw(Graphics g) {
        g.setColor(Color.pink);
        for (Point p : snakePoints) {
            g.fillRect(p.getX(), p.getY(), 5, 5);
        }
    }

    /**
     * Makes the snake move.
     * The idea is to set every point at the position where it's previous point belonged to.
     */
    public void move() {
        if (isMoving) {
            Point temp = snakePoints.get(0); // Gets the current head of the snake.
            Point newStart = new Point(temp.getX() + xDir * 5, temp.getY() + yDir * 5);
            Point last = snakePoints.get(snakePoints.size() - 1);
            for (int i = snakePoints.size() - 1; i >= 1; i--) {
                snakePoints.set(i, snakePoints.get(i - 1));
            }
            snakePoints.set(0, newStart);
            if (elongate) {
                snakePoints.add(last);
                this.setElongate(false);
            }
        }
    }

    /** Returns true if the head of the snake collides with its body otherwise returns false. */
    public boolean snakeCollision() {
        int headX = snakePoints.get(0).getX();
        int headY = snakePoints.get(0).getY();
        for (int i = 1; i < snakePoints.size(); i++) {
            if (snakePoints.get(i).getX() == headX && snakePoints.get(i).getY() == headY) {
                this.isMoving = false;
                return true;
            }
        }
        return false;
    }

    /** Returns tru if the snake is moving otherwise returns false. */
    public boolean isMoving() {
        return isMoving;
    }

    /** Setter for the private boolean variable 'elongate'. */
    public void setElongate(boolean input) {
        elongate = input;
    }

    /** Getter for the integer variable 'xDir'. */
    public int getxDir() {
        return xDir;
    }

    /** Setter for the integer variable 'xDir'. */
    public void setxDir(int x) {
        xDir = x;
    }

    /** Getter for the integer variable 'yDir'. */
    public int getyDir() {
        return yDir;
    }

    /** Setter for the integer variable 'yDir'. */
    public void setyDir(int y) {
        yDir = y;
    }

    /** Setter for the boolean variable 'isMoving'. */
    public void setIsMoving(boolean b) {
        isMoving = b;
    }

    /** Returns the x-position of the first item in the ArrayList - 'snakePoints'. */
    public int getHeadX() {
        return snakePoints.get(0).getX();
    }

    /** Returns the Y-position of the first item in the ArrayList - 'snakePoints'. */
    public int getHeadY() {
        return snakePoints.get(0).getY();
    }
}
