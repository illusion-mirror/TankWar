import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankFrame extends Frame{
//    private int x = 0,y=0;
//    public static int SPPED = 10;
    private Tank tank;
    public TankFrame(){
        tank = new Tank(100,100,Dir.STOP);
        this.setTitle("Tank Frame");
        this.setLocation(400,100);
        this.setSize(800,600);
//        this.setVisible(true);
        this.addKeyListener(new TankListener());
    }

    private class TankListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            x++;
//            System.out.println(x);
            //int key = e.getKeyCode();
            tank.keyPress(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            tank.keyReleased(e);

        }
    }

    @Override
    public void paint(Graphics g) {
        tank.paint(g);
        //g.fillRect(x,y,50,50);
    }
}
