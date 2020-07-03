package com.z;

import com.z.strategy.Strategy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

public class Player {
    int x=10,y=10;
    public static final int SPPED = 5;
    private boolean BL,BR,BU,BD;
    private Dir dir;
    private boolean moving;
    private Group group;
    private Strategy strategy= null;
    public Player(int x, int y, Dir dir, Group group){
        //dir = new Dir();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        //System.out.println(Dir.valueOf("dir"));
        try {
            initStrategy();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getSPPED() {
        return SPPED;
    }

    public boolean isBL() {
        return BL;
    }

    public boolean isBR() {
        return BR;
    }

    public boolean isBU() {
        return BU;
    }

    public boolean isBD() {
        return BD;
    }

    public Dir getDir() {
        return dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public Group getGroup() {
        return group;
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
   private void initStrategy() throws ClassNotFoundException, NoSuchMethodException {
        ClassLoader loader = Player.class.getClassLoader();
        String className = ConfigMgr.get("tankFireStrategy");
        Class clazz = loader.loadClass("com.z.strategy."+className);
       try {
           strategy = (Strategy)(clazz.getDeclaredConstructor().newInstance());
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
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
        strategy.fire(this);
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
