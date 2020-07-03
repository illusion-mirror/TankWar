package com.z.strategy;

import com.z.*;

public class DoubleFireStrategy implements Strategy{
    private int tankWidth = ResourceMgr.goodTankU.getWidth(), tankHeight = ResourceMgr.goodTankU.getHeight();
    private int bulletWidth = ResourceMgr.bulletU.getWidth(), bulletHeight = ResourceMgr.bulletU.getWidth();

    public void fire(Player p){
        Dir d = p.getDir();
        int bulletx1 = 0, bulletx2 = 0;
        int bullety1 = 0, bullety2 = 0;
        switch (d){
            case L:
                bulletx1 = p.getX()+tankWidth/2-bulletWidth/2;
                bulletx2 = p.getX()+tankWidth/2-bulletWidth/2;
                bullety1 = p.getY()-bulletHeight/2;
                bullety2 = p.getY()+tankHeight-bulletHeight/2;
                break;
            case R:
                bulletx1 = p.getX()+tankWidth/2-bulletWidth/2;
                bulletx2 = p.getX()+tankWidth/2-bulletWidth/2;
                bullety1 = p.getY()-bulletHeight/2;
                bullety2 = p.getY()+tankHeight-bulletHeight/2;
                break;
            case U:
                bulletx1 = p.getX()-bulletWidth/2;
                bulletx2 = p.getX()+tankWidth-bulletWidth/2;
                bullety1 = p.getY()+tankHeight/2-bulletHeight/2;
                bullety2 = p.getY()+tankHeight/2-bulletHeight/2;
                break;
            case D:
                bulletx1 = p.getX()-bulletWidth/2;
                bulletx2 = p.getX()+tankWidth-bulletWidth/2;
                bullety1 = p.getY()+tankHeight/2-bulletHeight/2;
                bullety2 = p.getY()+tankHeight/2-bulletHeight/2;
                break;
        }
        TankFrame.INSTANCE.add(new Bullet(bulletx1,bullety1,p.getDir(),p.getGroup()));
        TankFrame.INSTANCE.add(new Bullet(bulletx2,bullety2,p.getDir(),p.getGroup()));

    }
}
