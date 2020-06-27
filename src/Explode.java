import java.awt.*;
import java.awt.event.KeyEvent;

public class Explode {
    private int x, y;
    private int step = 0;

    public Explode(int x, int y) {
        //dir = new Dir();
        this.x = x;
        this.y = y;
        //System.out.println(Dir.valueOf("dir"));
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        //g.fillRect(x,y,50,50);
        if(step < ResourceMgr.explodes.length)
            g.drawImage(ResourceMgr.explodes[step],x,y,null);
        else{
            TankFrame.INSTANCE.getExplodes().remove(this);
        }
        step++;

    }

}