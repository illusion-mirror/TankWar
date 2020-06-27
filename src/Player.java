import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    int x=10,y=10;
    public static final int SPPED = 5;
    private boolean BL,BR,BU,BD;
    private Dir dir;
    private boolean moving;
    private Group group;
    public Player(int x, int y, Dir dir, Group group){
        //dir = new Dir();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        //System.out.println(Dir.valueOf("dir"));
    }

    public void keyPress(KeyEvent e){
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                BL=true;
                break;
            case KeyEvent.VK_RIGHT:
                BR=true;
                break;
            case KeyEvent.VK_UP:
                BU=true;
                break;
            case KeyEvent.VK_DOWN:
                BD=true;
                break;
            case KeyEvent.VK_CONTROL:
                //实例化一个子弹

        }
        setMainDir();
//        move();
//
    }


    public void paint(Graphics g){
        //g.fillRect(x,y,50,50);

        if(this.group == Group.GoodTank){
            switch (dir) {
                case U:
                    g.drawImage(ResourceMgr.goodTankU,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.goodTankD,x,y,null);
                    break;
                case L:
                    //System.out.println(dir);
                    g.drawImage(ResourceMgr.goodTankL,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.goodTankR,x,y,null);
                    break;
            }
        }
        if(this.group == Group.BadTank){
            switch (dir) {
                case U:
                    g.drawImage(ResourceMgr.badTankU,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.badTankD,x,y,null);
                    break;
                case L:
                    g.drawImage(ResourceMgr.badTankL,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.badTankR,x,y,null);
                    break;
            }
        }
        move();

    }

    private void move() {
        if(moving){
            switch (dir){
                case D:
                    y +=SPPED;
                    break;
                case U:
                    y -=SPPED;
                    break;
                case L:
                    x-=SPPED;
                    break;
                case R:
                    x +=SPPED;
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getExtendedKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                BL=false;
                break;
            case KeyEvent.VK_RIGHT:
                BR=false;
                break;
            case KeyEvent.VK_UP:
                BU=false;
                break;
            case KeyEvent.VK_DOWN:
                BD=false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        setMainDir();

    }

    private void fire() {
        int bulletx = x+ResourceMgr.goodTankU.getWidth()/2-ResourceMgr.bulletU.getWidth()/2;
        int bullety = y+ResourceMgr.goodTankU.getHeight()/2-ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.add(new Bullet(bulletx,bullety,dir,group));
    }

    private void setMainDir() {
        if (!BU && !BD && !BR && !BL){
            moving = false;
        }else {
            moving = true;
            if (BU && !BD && !BR && !BL) {
                dir = Dir.U;
            }
            if (!BU && BD && !BR && !BL) {
                dir = Dir.D;
            }
            if (!BU && !BD && BR && !BL) {
                dir = Dir.R;
            }
            if (!BU && !BD && !BR && BL) {
                dir = Dir.L;
            }
        }
    }
}
