import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Goal implements Actor{
    private int x, y;
    public Goal() {
        x = 750;
        y = 50;
    }
    public Goal(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 50, 50);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
