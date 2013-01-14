package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {
	public Image background;
	int posX;
	int posY;

	public Background(int x,int y) throws SlickException  {
		background = new Image("assets/background_space.png");
		posY = y;
		posX = x;
	}
	
	public void update(){
		posX -= 1;
		if(posX < -3200)
		{
			//posX=0;
		}

	}
}
