package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Level {
	int level = 1;
	String area = "space";
	
	
	public Level(int levelInit) throws SlickException{
	level = levelInit;
	}
	
	public void update(int levelUpdate){
	level = levelUpdate;
	if(level == 1){
		area = "space";
		level = 1;
		GameplayState.textColor = Color.white;
	}
	if(level == 2){
		area = "sky";
		level = 2;
		GameplayState.textColor = Color.white;
	}
	else{
		area = "stars";
		level = 1;
	}
	
	}
	public void level1(){
	level = 1;
	area = "space";
	}
	public void level2(){
	level = 2;
	area = "stars";
	
	}
	public void level3(){
	level = 3;
	
	}
}
