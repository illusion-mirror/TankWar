import java.awt.*;
import java.awt.event.KeyEvent;

public class Bullet {
    private int x,y;
    private Dir dir;
    private Group group;
    public final static int SPEED = 6;
    public Bullet(int x, int y, Dir dir,Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public Dir getDir() {
        return dir;
    }

    public Group getGroup() {
        return group;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        boundCheck();
    }

    private void boundCheck() {
        if((this.x>800 || this.x<0)|| (this.y >600 || this.y<0)) {
            TankFrame.INSTANCE.getBulletList().remove(this);
            //TankFrame.INSTANCE.removeBullt(this);
        }
    }

    public boolean inRange() {
        if((this.x>TankFrame.INSTANCE.getWidth() || this.x<0)|| (this.y > TankFrame.INSTANCE.getHeight()|| this.y<0)){
            return false;
            //TankFrame.INSTANCE.removeBullt(this);
        }
        return true;
    }
}
