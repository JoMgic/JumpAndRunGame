import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player implements Actor{
    private int x, y;
    private int xVelocity, yVelocity;
    private boolean jumping;
    private int playgroundWidth = 500;
    private int playgroundHeight = 800;
    private int targetY;
    private boolean traktorStrahl = false;
    private int moveSpeed = 2;    
    private boolean goLeft, goRight;
    private boolean boost = false;
    public Player() {
        x = 100;
        y = playgroundHeight;
        prepare();
    }
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        prepare();        
    }
    public void prepare(){
        xVelocity = 0;
        yVelocity = 0;
        jumping = false;
        playgroundWidth = JumpAndRunGame.getMaxX()-50;
        //playgroundHeight = JumpAndRunGame.getMaxY();
        goLeft = false; goRight = false;
    }

    public void update() {
        x += xVelocity;
        y += yVelocity;

        if (!traktorStrahl) {
            // Gravitation
            if (y < playgroundHeight) {
                yVelocity += 1; // Für Beschleunigung beim Fall
            } else {
                y = playgroundHeight;
                yVelocity = 0;
                jumping = false;
            }
        } else {
            if (y == targetY) {
                traktorStrahl = false;
                yVelocity = 0; // Reset yVelocity when target is reached
            } else if (y < targetY) {
                y += moveSpeed;
            } else if (y > targetY) {
                y -= moveSpeed;
            }
        }
        if (x < 0) x = 0;
        if (x > playgroundWidth) x = playgroundWidth;
    }
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            xVelocity = -10;
            goLeft = true;
            if(boost){
                xVelocity *= 3;
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
            xVelocity = 10;
            goRight = true;
            if(boost){
                xVelocity *= 3;
            }
        }
        if (key == KeyEvent.VK_J) {
            if(boost){
                return;
            }
            boost = true;
            xVelocity *= 3;
            
            new Thread(){
                @Override
                public void run(){
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                    xVelocity /= 3;
                    boost = false;
                }
            }.start();
        }
        if (key == KeyEvent.VK_SPACE && !jumping) {
            yVelocity = -15; // Sprunghöhe - warum negativ? weil rückwirkende Kraft. scheiß Physik in Games
            jumping = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT:
                goLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                goRight = false;
                break;
        }
        //if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            if(goLeft || goRight) return;
            xVelocity = 0;
        //}
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        targetY = y;
       
        
        //traktorStrahl = true;
        
        this.y = y;
    }

    public void smoothThroughRoof(){}
    
    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public int getHeight() {
        return 50;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
