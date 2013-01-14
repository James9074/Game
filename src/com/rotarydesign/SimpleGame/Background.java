package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {
	public Image background;
	int posX;
	int posY;
	int tile;

	public Background(int x,int y, int tileNumber) throws SlickException  {
		background = new Image("assets/background_space"+tileNumber+".jpg");
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
