package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Bullet{
	public Image bulletGraphic;
	int posX;
	int posY;
	int damage;
	float delta;
	
	public Rectangle bulletPoly;

	
	
	

	public Bullet(int x,int y, int type, int bulletDamage) throws SlickException  {
		bulletGraphic = new Image("assets/bullet"+type+".png");
		posY = y;
		posX = x;
		damage = bulletDamage;
		bulletPoly = new Rectangle( posX,
                 posY,
                 10,
                 5);
	}
	
	public void update(int speed){
		float bulletSpeed = 0;
		bulletSpeed = (speed/16f) * GameplayState.deltaTime;
		posX += bulletSpeed;
		bulletPoly.setX(posX);
		if(posX > 300)
		{
			//posX=0;
		}
	}
	

}

