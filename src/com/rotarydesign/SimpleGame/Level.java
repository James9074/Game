package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Level {
	int level = 1;
	static String music;
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
		music = "assets/music/level1.wav";
		
	}
	if(level == 2){
		area = "sky";
		level = 2;
		GameplayState.textColor = Color.white;
		music = "assets/music/level2.wav";
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
	music = "assets/level2.wav";
	
	}
	public void level3(){
	level = 3;
	
	}
}
