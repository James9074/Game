package com.rotarydesign.SimpleGame;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public class Bar {
	public Image barOutlinePoly;
	public int bars = 5;
	
	public Bar(int x,int y) throws SlickException  {
		
		barOutlinePoly = new Image("assets/bar"+bars+".png");
	}
	
	public void update(){
		//barOutlinePoly.reinit();
	}
}
