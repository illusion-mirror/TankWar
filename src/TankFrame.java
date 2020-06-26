import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class TankFrame extends Frame{
    public static final TankFrame INSTANCE = new TankFrame();
    private static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
    //    private int x = 0,y=0;
//    public static int SPPED = 10;
    private Tank tank;
    private Tank enemy;
    Image offScreenImage;
    private List<Bullet> bulletsList = null;
    private Bullet bullet=null;

    private TankFrame(){
        tank = new Tank(150,100,Dir.U,Group.GoodTank);
        enemy = new Tank(100,100,Dir.U,Group.BadTank);
        this.setTitle("Tank Frame");
        this.setLocation(400,100);
        this.setSize(800,600);
        this.bulletsList = new ArrayList<>();
//        this.setVisible(true);
        this.addKeyListener(new TankListener());
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public void add(Bullet bullet) {
        this.bulletsList.add(bullet);
    }
    private class TankListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            x++;
//            System.out.println(x);
            //int key = e.getKeyCode();
            tank.keyPress(e);
            //enemy.keyPress(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            tank.keyReleased(e);
            //enemy.keyReleased(e);

        }
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullet num:"+bulletsList.size(),50,100);

        tank.paint(g);
        //g.fillRect(x,y,50,50);
        enemy.paint(g);
//        if(bullet!=null)
//            bullet.paint(g);
        //g.drawImage(ResourceMgr.goodTankL,200,200,null);
        for(int i=0; i < bulletsList.size();i++){
            bulletsList.get(i).paint(g);
        }
    }
    @Override
    public void update(Graphics g) {
        updateBullet();
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private void updateBullet() {
        for(int i=0; i < bulletsList.size();i++){
            if (!bulletsList.get(i).inRange()){
                bulletsList.remove(i);
                i--;
            }
        }
    }
}
