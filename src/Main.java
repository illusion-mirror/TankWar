import java.awt.*;

import java.awt.*;

public class Main {
    public  static void main(String args[]) throws InterruptedException {
//        Frame f = new Frame("Tank Frame");
//        f.setVisible(true);
        TankFrame tf = new TankFrame();
        tf.setVisible(true);
        for(;;){
            Thread.sleep(10);
            tf.repaint();

        }

//        tf.setVisible(true);
    }
}
