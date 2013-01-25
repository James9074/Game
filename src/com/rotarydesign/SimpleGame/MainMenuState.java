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


public class MainMenuState extends BasicGameState {
 
    int stateID = 0;
    static int levelSelect = 1;
    Sound fx = null;
    static Music menuMusic = null;
    boolean insideStartGame = false;
    boolean insideExit = false;
    Image startGameOption = null;
    Image exitOption = null;
    Image background = null;
 
    float startGameScale = 1;
    float exitScale = 1;
     
 
    MainMenuState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    
    
    
    @Override
    public int getID() {
        return stateID;
    }
    
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	background = new Image("assets/menu.jpg");
    	menuMusic = new Music("assets/menuMusic.wav");
    	menuMusic.loop();
    	gc.setShowFPS(false); 
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	background.draw(0,0);
    	g.setColor(Color.white);
    	g.drawString("Controls:\nWASD - Movement\nLeft SHIFT - Slow Ship\nSpace Or Left Click - Fire\n\n\nClick To Begin\n\nEscape To Exit",330,100);
    	g.drawString("Level: " + levelSelect, 330, 300);
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
    		GameplayState.level = levelSelect;
    		//GameplayState.health = 100;
    		menuMusic.fade(500, 0f, true);
    		sbg.enterState(SimpleGame.GAMEPLAYSTATE, new FadeOutTransition(Color.black), null);
    		
    	}
    	
    	if (input.isKeyPressed(Input.KEY_ESCAPE))
    	{
    		gc.exit();
    	}
    	
    	//Level Select
    	if (input.isKeyPressed(Input.KEY_LEFT))
    	{
    		if(levelSelect > 1){
    		levelSelect -= 1;
    		}
    	}
    	if (input.isKeyPressed(Input.KEY_RIGHT))
    	{
    		if(levelSelect < 2){
    		levelSelect += 1;
    		}
    	}
    }
 
}