package com.z;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class TankFrame extends Frame{
    public static final TankFrame INSTANCE = new TankFrame();

    private static  int GAME_WIDTH = Integer.parseInt(ConfigMgr.get("gameWidth")),GAME_HEIGHT = Integer.parseInt(ConfigMgr.get("gameHeight"));
    Image offScreenImage;
    //    private int x = 0,y=0;
//    public static int SPPED = 10;
    private Player tank;
    private List<Bullet> bulletsList;
    private List<Tank> enemys;
    private List<Explode> explodes;

    private TankFrame(){

        this.setTitle("Tank Frame");
        this.setLocation(400,100);
        this.setSize(800,600);
//        this.setVisible(true);
        this.addKeyListener(new TankListener());
        initGameObject();
    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    private void initGameObject() {
        tank = new Player(150,100, Dir.U, Group.GoodTank);
        enemys = new ArrayList<>();
        explodes = new ArrayList<>();
        this.bulletsList = new ArrayList<>();
        for(int i= 0; i < 10; i++){
            enemys.add(new Tank(100,100, Dir.L, Group.BadTank));
        }
    }

    public List<Explode> getExplodes() {
        return explodes;
    }

    public List<Bullet> getBulletList(){
        return bulletsList;
    }

    public List<Tank> getEnemys() {
        return enemys;
    }

    public void add(Bullet bullet) {
        this.bulletsList.add(bullet);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullet num:"+bulletsList.size(),50,100);
        g.drawString("enemy num"+enemys.size(),50,120);

        tank.paint(g);
        for(int i=0; i < bulletsList.size();i++){
            bulletsList.get(i).paint(g);
        }
        for(int i = 0; i< enemys.size(); i++){
            enemys.get(i).paint(g);
        }
        for(int i = 0; i< explodes.size(); i++){
            explodes.get(i).paint(g);
        }
    }

    @Override
    public void update(Graphics g) {
        //updateBullet();
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

    public void add(Explode explode) {
        this.explodes.add(explode);
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
}
