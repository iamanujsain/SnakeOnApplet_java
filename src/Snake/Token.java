package Snake;

import java.awt.*;

public class Token {

    private int x, y, score = 0;
    private Snake snake;

    // Constructor for the class Snake.Token.
    public Token(Snake s) {
        this.snake = s;
        x = (int) (Math.random() * 495);
        y = (int) (Math.random() * 495);
    }
    // This method changes the position of the token each time it's called.
    public void changePosition() {
        x = (int) (Math.random() * 495);
        y = (int) (Math.random() * 495);
    }

    // Returns the score.
    public int getScore() {
        return score;
    }

    public void setScore(int i) {
        score = i;
    }

    // Draws the token.
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(x, y, 6,6);
    }

    // Detects a collision and returns true.
    public boolean snakeCollision() {
        int snakeX = snake.getHeadX() + 2;
        int snakeY = snake.getHeadY() + 2;
        if (snakeX >= x-1 && snakeX <= x+7) {
            if (snakeY >= y-1 && snakeY <= y+7) {
                changePosition();
                score++;
                snake.setElongate(true);
                return true;
            }
        }
        return false;
    }
}
