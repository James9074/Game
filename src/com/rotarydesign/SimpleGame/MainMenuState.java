package com.rotarydesign.SimpleGame;


import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class MainMenuState extends BasicGameState {
 
	/*------------------------Global Variables-----------------------------*/
	private enum STATES {
        MAIN_MENU_STATE, INITIALIZE, OPTIONS_STATE, PLAYING,
        PAUSE_GAME_STATE, HIGHSCORE_STATE, GAME_OVER_STATE
    }
	public static STATES currentState = null;
    int stateID = 0;
    static int itemSelect = 1;
    Sound fx = null;
    static Music menuMusic = null;
    boolean insideStartGame = false;
    boolean insideExit = false;
    Image startGameOption = null;
    Image exitOption = null;
    Image background = null;
    Image stars2 = null;
    float starX = 0;
    float starY = 0;    
    float star2X = 0;
    float star2Y = 0;
    static ArrayList <StarSheet> stars = new ArrayList <StarSheet>();
    UnicodeFont mainFont;
    UnicodeFont titleFont;
    float startGameScale = 1;
    float exitScale = 1;
    int newGameX = 50;
    int newGameY = 250;
    int loadGameX = 50;
    int loadGameY = 270;
    int deltaAverage = 0;
    int deltaAdd = 0;
    int deltaNumber = 0;
	Color grey = new Color(Color.gray);
	Color white = new Color(Color.white);
	String colorString = "white";
	/*------------------------Global Variables End-----------------------------*/
    
    MainMenuState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    
    
    
    @Override
    public int getID() {
        return stateID;
    }
    
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	//LoadingList.setDeferredLoading(true);
    	background = new Image("assets/menu.jpg");
    	menuMusic = new Music("assets/menuMusic.wav");
    	menuMusic.loop();
    	currentState = STATES.OPTIONS_STATE;	
    	gc.setShowFPS(false); 
    	stars.add(new StarSheet(0,0,1));
    	//stars.add(new StarSheet(0,1000,2));
    	
    	
    	/*------------------------Set Fonts-----------------------------*/
    	
    	mainFont = new UnicodeFont("assets/fonts/main.ttf", 20, false, false);
    	mainFont.addAsciiGlyphs();
    	mainFont.addGlyphs(400, 600);
    	mainFont.getEffects().add(new ColorEffect());
    	mainFont.loadGlyphs();
    	
    	titleFont = new UnicodeFont("assets/fonts/main.ttf", 50, false, false);
    	titleFont.addAsciiGlyphs();
    	titleFont.addGlyphs(400, 600);
    	titleFont.getEffects().add(new ColorEffect());
    	titleFont.loadGlyphs();
    	
    	/*------------------------Set Fonts End-----------------------------*/

    }
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	itemSelect = 1;
    }

 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	boolean focus = gc.hasFocus();
    	
    	//int mouseX = input.getMouseX();
    	//int mouseY = input.getMouseY();
    	
    	if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
    		
    		//GameplayState.currentState = STATES.START_GAME_STATE;
    		//GameplayState.music.loop(1f,.3f);
    		GameplayState.playing = false;
    		//GameplayState.health = 100;
    		menuMusic.fade(500, 0f, true);
    		sbg.enterState(SimpleGame.COMMANDMENUSTATE, new FadeOutTransition(Color.black), null);
    		
    	}
    	
    	
    	
    	if (input.isKeyPressed(Input.KEY_ESCAPE))
    	{
    		gc.exit();
    	}
    	if (input.isKeyPressed(Input.KEY_ENTER))
    	{
    		if(itemSelect == 1){
    			sbg.enterState(SimpleGame.COMMANDMENUSTATE, new FadeOutTransition(Color.black), null);
    	    	}
    	    	else if(itemSelect == 2){
    	    		sbg.enterState(SimpleGame.COMMANDMENUSTATE, new FadeOutTransition(Color.black), null);
    	    	}
    	}
    	
    	//Level Select
    	if (input.isKeyPressed(Input.KEY_UP))
    	{
    		if(itemSelect > 1){
    			itemSelect -= 1;
    		}
    	}
    	if (input.isKeyPressed(Input.KEY_DOWN))
    	{
    		if(itemSelect < 2){
    			itemSelect += 1;
    		}
    	}
    	
    	
    	
    	/*------------------------UPDATE DELTA-----------------------------*/
        /*
        System.out.println("Average: "+deltaAverage);
		System.out.println("Delta: "+delta);
		System.out.println("Add: "+deltaAdd);
		System.out.println("Number: "+deltaNumber);

		System.out.println("-------------");*/
        if(deltaAverage == 0){
        	deltaAverage = delta;
        }
    	if(deltaNumber < 30)
    	{
    		deltaAdd += delta;
    		deltaNumber += 1;
    	}
    	else
    	{
    		deltaAverage = deltaAdd/30;
    		deltaAdd = 0;
    		deltaNumber = 0;
    		//--Debugging
    		//System.out.println(deltaAverage);
    		//System.out.println(delta)
    	}
    	/*----------------------UPDATE DELTA END---------------------------*/
    	
    	/*------------------------UPDATE STAR BACKGROUND--------------------------*/	
    	for(int i = 0; i < stars.size(); i++)
    	{
    		
    		if(focus){
    		stars.get(i).update(deltaAverage);
    		}
    		System.out.println(stars.get(i).posY);
    		if(stars.get(i).posY < -1000)
    		{
    			stars.remove(stars.get(i));
    			System.out.println("Remove: "+i);
    		}
    		if(stars.get(i).posY == -590)
    		{
    			stars.add(new StarSheet(0,411,1));
    			System.out.println("Y: "+stars.get(i).posY);
    			System.out.println("Added one");
    		}
    	}
    	
    	/*------------------UPDATE STAR BACKGROUND END--------------------*/	
    	
    	starY -= deltaAverage *.01f;
    	//System.out.println(deltaAverage *.1f);
    	
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	background.draw(0,0);
    	
       	
    	/*------------------RENDER STARS------------------------*/	
    	for(int i = 0; i < stars.size(); i++)
    	{
    		//backgrounds.get(i).posX-=1;
    		stars.get(i).background.draw(stars.get(i).posX ,stars.get(i).posY);
    	}
    	/*------------------RENDER STARS END--------------------*/	
    	
        /*------------------------RENDER TITLE------------------------*/	
    	titleFont.drawString(28, 10, "GENERIC SPACE SHOOTER",Color.white );
        /*---------------------RENDER TITLE END-----------------------*/	

        /*---------------------RENDER OPTIONS-------------------------*/	
    	if(itemSelect == 1){
    	g.setColor(Color.gray);
    	mainFont.drawString(newGameX+20, newGameY, "NEW GAME",Color.white );
    	}
    	else {
    		g.setColor(Color.white);
    		mainFont.drawString(newGameX, newGameY, "NEW GAME",Color.gray);
    	}
    	//verdana.drawString(newGameX,newGameY,"New Game");
    	//mainFont.drawString(newGameX, newGameY, "NEW GAME",Color.white );

     	if(itemSelect == 2){
        	g.setColor(Color.white);
        	mainFont.drawString(loadGameX+20, loadGameY, "Load Game",Color.white);
        	}
        	else {
        		g.setColor(Color.gray);
            	mainFont.drawString(loadGameX, loadGameY, "Load Game",Color.gray);

        	}
    	//g.drawString("Load Game",loadGameX,loadGameY);
    	//mainFont.drawString(loadGameX, loadGameY, "Load Game",Color.white);
        /*------------------RENDER OPTIONS END-----------------------*/	

}
 
}