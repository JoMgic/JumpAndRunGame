import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Player player;
    private BorderController bc;
    private Goal goal;
    private int actualLevel = 1;
    private boolean passed = false;
    
    public GamePanel() {
        
        setFocusable(true);
        setBackground(Color.CYAN);
        addKeyListener(this);
        player = new Player();
        bc = LevelLoader.loadLevel(actualLevel, bc);
        goal = new Goal();
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        checkCollisions();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        bc.draw(g);
        goal.draw(g);
    }

    private void checkCollisions() {
        if (bc.touchesBorder(player.getBounds()) && player.getYVelocity() >= 0) {
            player.setY(bc.getTouchingPlatform().getY() - player.getHeight());
            player.setYVelocity(0);
            player.setJumping(false);
        }
        if (player.getBounds().intersects(goal.getBounds())) {
            //new JohannesList<Integer>().haveBigFun();
            System.out.println("Sehr gut Level gemeistert");
            
            //if(passed == false){
                actualLevel++;
                bc = LevelLoader.loadLevel(actualLevel, bc);
                //passed = true;
                player = new Player();
                try
                {
                    Thread.sleep(200);
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
                //passed = false;
            //}
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
}
