import javax.swing.JFrame;

public class JumpAndRunGame {
    private static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame("Jump and Run Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800, 1000);
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
    public static int getMaxX(){
        return frame.getWidth();
    }
    public static int getMaxY(){
        return frame.getHeight();
    }
}
