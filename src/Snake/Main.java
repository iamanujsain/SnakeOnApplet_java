package Snake;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends Applet implements Runnable, KeyListener {

    Graphics gfx;
    Image img;
    Thread thread;
    Snake s;
    Token t;
    boolean gameOver, go, _pr, _display, _restart;

    public void init() {
        one();
        thread = new Thread(this);
        thread.start();
    }

    public void one() {
        _restart = false; /** Flag for restarting the game. */
        _display = true; /** Flag for displaying the game. */
        _pr = true; /** Flag for both pausing and resuming the game. */
        go = false; /** Used as a flag for detecting gameOver. */
        gameOver = false;
        this.resize(500, 500);
        img = createImage(500, 500);
        gfx = img.getGraphics();
        s = new Snake();
        t = new Token(s);
        addKeyListener(this);
    }

    public void restart() {
        if (gameOver && go) {
            s.resset();
            t.setScore(0);
            gameOver = false;

            one();
        }
    }

    public void paint(Graphics g) {
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 500, 500);

        if (img == null) {
            img = createImage(this.getSize().width, this.getSize().height);
            gfx = img.getGraphics();
        }

        write(gfx);

        /*if (!gameOver) {
            s.draw(gfx);
            t.draw(gfx);
        } else {
            if (go) {
                gfx.setColor(Color.RED);
                gfx.drawString("Game over!", 150, 150);
                gfx.drawString("Score: " + t.getScore(), 180, 200);
            } else {
                gfx.setColor(Color.yellow);
                gfx.drawString("Press UP/DOWN/LEFT/RIGHT to get started.", 190, 200);
            }
        }*/


        g.drawImage(img, 0, 0, null);
    }

    public void run() {
        while (true) {
            if (!gameOver) {
                if (s.isMoving()) {
                    s.move();
                    checkGameOver();
                    t.snakeCollision();
                }

                /** The following method will print the
                 * score on the console after the game is over. */
                /*if (go) {
                    System.out.println("Score: " + t.getScore());
                }*/

            }

            /** If both the flags, 'gameOver' and '_restart' are true,
             * restart() method will be in action. */
            if (gameOver && _restart) {
                restart();
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    /**
     * Checks if the game is over or not.
     */
    public void checkGameOver() {
        if (s.getHeadX() > 495 || s.getHeadX() < 0) {
            this.gameOver = true;
            go = true;
        }
        if (s.getHeadY() > 495 || s.getHeadY() < 0) {
            this.gameOver = true;
            go = true;
        }
        if (s.snakeCollision()) {
            this.gameOver = true;
            go = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void write(Graphics g) {
        if (!gameOver) {
            if (_display) {
                gfx.setColor(Color.yellow);
                gfx.drawString("Press UP/DOWN/LEFT/RIGHT to get started.", 190, 200);

            } else {
                s.draw(gfx);
                t.draw(gfx);
            }
        } else {
            gfx.setColor(Color.RED);
            gfx.drawString("Game over!", 150, 150);
            gfx.drawString("Score: " + t.getScore(), 180, 200);

            gfx.setColor(Color.green);
            gfx.drawString("Press 'S' to restart the game otherwise press 'Q' to quit.", 100,100);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /** When the game is over the player will be prompted
         * to press 'S' to play again otherwise press 'Q' to
         * quit the game.*/
        if (go && gameOver) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    _display = true;
                    _restart = true;
                    break;
                case KeyEvent.VK_Q:
                    System.exit(0);
                    break;
            }
        }

        /** Initially when the snake is not moving, if the human player presses
         * any of the following keys - Up/Down/Right/Left/Enter - the game will
         * start.*/
        if (!s.isMoving() && !go) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN
                    || e.getKeyCode() == KeyEvent.VK_UP
                    || e.getKeyCode() == KeyEvent.VK_RIGHT
                    || e.getKeyCode() == KeyEvent.VK_LEFT
                    || e.getKeyCode() == KeyEvent.VK_ENTER) {
                _display = false;
                s.setIsMoving(true);
            }
        }

        /** Keys to steer the snake. */
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (s.getyDir() != 1) {
                    s.setyDir(-1);
                    s.setxDir(0);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (s.getyDir() != -1) {
                    s.setyDir(1);
                    s.setxDir(0);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (s.getxDir() != -1) {
                    s.setxDir(1);
                    s.setyDir(0);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (s.getxDir() != 1) {
                    s.setyDir(0);
                    s.setxDir(-1);
                }
                break;
            case KeyEvent.VK_ENTER:
                if (!gameOver) {
                    if (s.getxDir() != -1) {
                        s.setxDir(1);
                        s.setyDir(0);
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                if (_pr) {
                    s.setIsMoving(false);
                    _pr = false;
                } else {
                    s.setIsMoving(true);
                    _pr = true;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}