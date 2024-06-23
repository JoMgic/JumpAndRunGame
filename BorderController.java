import java.awt.Rectangle;
import java.util.ArrayList;


public class BorderController
{
    private ArrayList <Platform> border;
    private Platform touching;
    private Player p;
    private Goal g;
    public BorderController(){
        border = new ArrayList<>();
    }
    public void add(Platform p){
        border.add(p);
    }
    public void update(){}
    public boolean touchesBorder(Rectangle rect){
        for(Platform p : border){
            if(rect.intersects(p.getBounds())) { touching = p; return true; }
        }
        return false;
    }
    public void draw(java.awt.Graphics g){
        for(Platform p : border){
            p.draw(g);
        }
    }
    public Platform getTouchingPlatform(){
        return touching;
    }
    public void setActors(int px, int py, int gx, int gy){
        p = new Player(px, py);
        g = new Goal(px, py);
    }
}
