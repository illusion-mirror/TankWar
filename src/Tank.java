import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank {
    private int x, y;
    private int oldX,oldY;
    public static final int SPPED = 5;
    //private boolean BL, BR, BU, BD;
    private Dir dir;
    private boolean moving = true;
    private Group group;
    private Random r = new Random();
    private int width = ResourceMgr.badTankU.getWidth(),hight = ResourceMgr.badTankU.getHeight();
    private int bulletWidth = ResourceMgr.bulletU.getWidth(),bulletHight = ResourceMgr.bulletU.getHeight();

    public Tank(int x, int y, Dir dir, Group group) {
        //dir = new Dir();
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.dir = dir;
        this.group = group;
        //System.out.println(Dir.valueOf("dir"));
    }

    public void paint(Graphics g) {
        //g.fillRect(x,y,50,50);
        switch (dir) {
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
            case L:
                //System.out.println(dir);
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
        }

        move();
        if (collisionWithBullet()){
           destoryThis();
           TankFrame.INSTANCE.add(new Explode(x,y));
        }
        randomChangeDir();
        randomFire();

    }

    private void randomFire() {
        if(r.nextInt(100)<10){
            fire();
        }

    }

    private void randomChangeDir() {
        if(r.nextInt(100)<10)
        this.dir = Dir.randomDir();
    }

    private void destoryThis() {
        TankFrame.INSTANCE.getEnemys().remove(this);
    }
    private boolean collisionWithBullet() {
        Rectangle r = new Rectangle(x,y,ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
        Rectangle b;
        for(int i = 0; i<TankFrame.INSTANCE.getBulletList().size();i++){
            b = new Rectangle(TankFrame.INSTANCE.getBulletList().get(i).getX(),TankFrame.INSTANCE.getBulletList().get(i).getY()
                    ,ResourceMgr.bulletU.getWidth(),ResourceMgr.bulletU.getHeight());
            if(r.intersects(b) && TankFrame.INSTANCE.getBulletList().get(i).getGroup()!=this.group){
                return true;
            }
        }
        return false;
    }

    private void move() {
        oldX = x;
        oldY = y;
        if (moving) {
            switch (dir) {
                case D:
                    y += SPPED;
                    break;
                case U:
                    y -= SPPED;
                    break;
                case L:
                    x -= SPPED;
                    break;
                case R:
                    x += SPPED;
                    break;
            }
        }
        if(!boundCheck()){
            x = oldX;
            y = oldY;

        }
    }

    private boolean boundCheck() {
        if((this.x + this.width > 800 || this.x<0)|| (this.y + hight > 600 || this.y < 0)) {
            //TankFrame.INSTANCE.getBulletList().remove(this);
            //TankFrame.INSTANCE.removeBullt(this);
            return false;
        }
        return true;
    }

    private void fire() {
        int bulletx = x + this.width / 2 -  bulletWidth/ 2;
        int bullety = y + this.hight / 2 -  bulletHight / 2;
        TankFrame.INSTANCE.add(new Bullet(bulletx, bullety, dir, group));
    }

}
