package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Powerup {
	public Image powerupGraphic;
	int posX;
	int posY;
	float randomDirection = 0;
	int timePassed = 0;
	int health = 20;
	
	

	public Rectangle powerupPoly;


public Powerup() throws SlickException  {
	powerupGraphic = new Image("assets/shieldPowerup.png");
	java.util.Random random = new java.util.Random();
	posY = 40 + random.nextInt(350);
	posX = 800; //1200
	powerupPoly = new Rectangle( posX,
            posY,
            25,
            25);
}



public void update() throws SlickException{
	posX -= (1f/16f) * GameplayState.deltaAverage;
	powerupPoly.setX(posX);
	powerupPoly.setY(posY);
	
}


}