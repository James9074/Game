package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Powerup {
	public Image powerupGraphic;
	int posX;
	int posY;
	int randomDirection = 0;
	int timePassed = 0;
	int health = 20;
	
	

	public Rectangle powerupPoly;


public Powerup() throws SlickException  {
	powerupGraphic = new Image("assets/shieldPowerup.png");
	java.util.Random random = new java.util.Random();
	posY = 10 + random.nextInt(200);
	posX = 800; //1200
	powerupPoly = new Rectangle( posX,
            posY,
            25,
            25);
}



public void update() throws SlickException{
	java.util.Random random = new java.util.Random();
	posX -= 3;
	if(timePassed > 500)
	{
		randomDirection += -1 + random.nextInt(3);
		timePassed = 0;
		//GameplayState.powerups.add(new Powerup(posX-3,posY+7,1,5));
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
	powerupPoly.setX(posX);
	powerupPoly.setY(posY);
	
}


}