package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Enemy {
	public Image enemyGraphic;
	int posX;
	int posY;
	int randomDirection = 0;
	int timePassed = 0;
	int health = 20;
	

	public Rectangle enemyPoly;


public Enemy() throws SlickException  {
	enemyGraphic = new Image("assets/enemyShip.png");
	java.util.Random random = new java.util.Random();
	posY = 20 + random.nextInt(370);
	posX = 800; //1200
	enemyPoly = new Rectangle( posX,
            posY,
            50,
            15);
}



public void update() throws SlickException{
	java.util.Random random = new java.util.Random();
	posX -= 3;
	if(timePassed > 500)
	{
		randomDirection += -1 + random.nextInt(3);
		timePassed = 0;
		GameplayState.enemyBullets.add(new Bullet(posX-3,posY+7,1,5));
	}
	posY += randomDirection;
	if(posY > 390)
	{
		randomDirection = -1;
	}
	if(posY < 10)
	{
		randomDirection = 1;
	}	
	enemyPoly.setX(posX);
	enemyPoly.setY(posY);
	
}


}