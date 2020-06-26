import java.awt.*;

import java.awt.*;

public class Main {
    public  static void main(String args[]) throws InterruptedException {
//        Frame f = new Frame("Tank Frame");
//        f.setVisible(true);
        //TankFrame tf = new TankFrame();

        TankFrame.INSTANCE.setVisible(true);
        for(;;){
            Thread.sleep(10);
            TankFrame.INSTANCE.repaint();

        }

//        tf.setVisible(true);
    }
}
