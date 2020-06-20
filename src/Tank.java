import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    int x=10,y=10;
    public static final int SPPED = 5;
    private boolean BL,BR,BU,BD;
    private Dir dir;
    public Tank(int x, int y, Dir dir){
        //dir = new Dir();
        this.x = x;
        this.y = y;
        this.dir = dir;
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
        }
        setMainDir();
//        move();
//
    }


    public void paint(Graphics g){
        move();
        g.fillRect(x,y,50,50);

    }

    private void move() {
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
            case STOP:
                break;
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
        }
        setMainDir();

    }

    private void setMainDir() {
        if (!BU && !BD && !BR && !BL){
            dir = Dir.STOP;
        }
        if (BU && !BD && !BR && !BL){
            dir = Dir.U;
        }
        if (!BU && BD && !BR && !BL){
            dir = Dir.D;
        }
        if (!BU && !BD && BR && !BL){
            dir = Dir.R;
        }
        if (!BU && !BD && !BR && BL){
            dir = Dir.L;
        }
    }
}
