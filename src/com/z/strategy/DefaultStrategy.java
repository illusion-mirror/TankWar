package com.z.strategy;

import com.z.Bullet;
import com.z.Player;
import com.z.ResourceMgr;
import com.z.TankFrame;

public class DefaultStrategy implements Strategy{
    public void fire(Player p){
        int bulletx = p.getX()+ ResourceMgr.goodTankU.getWidth()/2- ResourceMgr.bulletU.getWidth()/2;
        int bullety = p.getY()+ ResourceMgr.goodTankU.getHeight()/2- ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.add(new Bullet(bulletx,bullety,p.getDir(),p.getGroup()));
    }
}
