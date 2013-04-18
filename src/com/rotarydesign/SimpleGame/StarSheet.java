package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class StarSheet {
	public Image background;
	float posX;
	float posY;
	int tile;
	String area = "space";
	int level = 0;

	public StarSheet(int x,int y, int tileNumber) throws SlickException  {
		background = new Image("assets/star_background.png");
		posY = y;
		posX = x;
		tile = tileNumber;
	    int deltaAverage = 0;
	    int deltaAdd = 0;
	    int deltaNumber = 0;
	}
	
	public void update(float deltaAverage){
		if((deltaAverage * .015) < 1){
		posY -= 1;
		}
		if(posY < -3200)
		{
			//posX=0;
		}

	}
}
