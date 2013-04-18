package com.rotarydesign.SimpleGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class StoreMenuState extends BasicGameState {
 
	
	private enum STATES {
        MAIN_MENU_STATE, INITIALIZE, OPTIONS_STATE, PLAYING,
        PAUSE_GAME_STATE, HIGHSCORE_STATE, GAME_OVER_STATE
    }
	
	public static STATES currentState = null;
	
    int stateID = 6;
    static int itemSelect = 1;
    Sound fx = null;
    static Music menuMusic = null;
    boolean insideStartGame = false;
    boolean insideExit = false;
    Image startGameOption = null;
    Image exitOption = null;
    Image background = null;
 
    float startGameScale = 1;
    float exitScale = 1;
    int levelSelectX = 50;
    int levelSelectY = 230;
    int storeX = 50;
    int storeY = 250;
    int optionsX = 50;
    int optionsY = 270;
     
 
    StoreMenuState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    
    
    
    @Override
    public int getID() {
        return stateID;
    }
    
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	background = new Image("assets/menu.jpg");
    	//menuMusic = new Music("assets/menuMusic.wav");
    	//menuMusic.loop();
    	currentState = STATES.OPTIONS_STATE;
    	gc.setShowFPS(false); 
    }
 
    
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	//int mouseX = input.getMouseX();
    	//int mouseY = input.getMouseY();
    	
    	if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
    		
    		//GameplayState.currentState = STATES.START_GAME_STATE;
    		//GameplayState.music.loop(1f,.3f);
    		GameplayState.needReset = true;
    		GameplayState.playing = false;
    		GameplayState.level = itemSelect;
    		//GameplayState.health = 100;
    		menuMusic.fade(500, 0f, true);
    		sbg.enterState(SimpleGame.GAMEPLAYSTATE, new FadeOutTransition(Color.black), null);
    		
    	}
    	
    	if (input.isKeyPressed(Input.KEY_ESCAPE))
    	{
    		sbg.enterState(SimpleGame.COMMANDMENUSTATE, new FadeOutTransition(Color.black), null);
    	}
    	if (input.isKeyPressed(Input.KEY_ENTER))
    	{
    		if(itemSelect == 1){
    			sbg.enterState(SimpleGame.LEVELSELECTMENUSTATE, new FadeOutTransition(Color.black), null);
    	    	}
    	    	else if(itemSelect == 2){
    	    		sbg.enterState(SimpleGame.STOREMENUSTATE, new FadeOutTransition(Color.black), null);
    	    	}
    	    	else if(itemSelect == 3){
    	    		sbg.enterState(SimpleGame.OPTIONSMENUSTATE, new FadeOutTransition(Color.black), null);
    	    	}
    		sbg.enterState(SimpleGame.LEVELSELECTMENUSTATE, new FadeOutTransition(Color.black), null);
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
    		if(itemSelect < 3){
    			itemSelect += 1;
    		}
    	}
    }
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	itemSelect = 1;
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	background.draw(0,0);
    	g.setColor(Color.white);
    	if(itemSelect == 1){
        	g.setColor(Color.gray);
        	}
        	else {
        		g.setColor(Color.white);
        	}
    	g.drawString("STORE STORE STORE",levelSelectX,levelSelectY);
    	if(itemSelect == 2){
        	g.setColor(Color.gray);
        	}
        	else {
        		g.setColor(Color.white);
        	}
    	g.drawString("Store",storeX,storeY);
    	if(itemSelect == 3){
        	g.setColor(Color.gray);
        	}
        	else {
        		g.setColor(Color.white);
        	}
    	g.drawString("Options",optionsX,optionsY);
}
 
}