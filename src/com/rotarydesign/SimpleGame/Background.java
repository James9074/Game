package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {
	public Image background;
	int posX;
	int posY;
	int tile;
	String area = "space";
	int level = 0;

	public Background(int x,int y,int level, int tileNumber) throws SlickException  {
		background = new Image("assets/backgrounds/level"+level+"/"+tileNumber+".jpg");
		posY = y;
		posX = x;
		tile = tileNumber;
	}
	
	public void update(){
		posX -= 1;
		if(posX < -3200)
		{
			//posX=0;
		}

	}
}
