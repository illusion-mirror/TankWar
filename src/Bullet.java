import java.awt.*;
import java.awt.event.KeyEvent;

public class Bullet {
    int x,y;
    Dir dir;
    Group group;
    public final static int SPEED = 7;
    public Bullet(int x, int y, Dir dir,Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }
    public void paint(Graphics g){
        //g.fillRect(x,y,50,50);
        move();
        switch (dir) {
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
        }


    }

    private void move() {
        switch (this.dir){
            case D:
                y+=SPEED;
                break;
            case U:
                y-=SPEED;
                break;
            case L:
                x-=SPEED;
                break;
            case R:
                x+=SPEED;
                break;

        }
    }

    public boolean inRange() {
        if((this.x>800 || this.x<0)|| (this.y >600 || this.y<0)){
            return false;
            //TankFrame.INSTANCE.removeBullt(this);
        }
        return true;
    }
}
