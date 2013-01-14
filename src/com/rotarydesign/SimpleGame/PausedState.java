package com.rotarydesign.SimpleGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.GameState;


public class PausedState extends BasicGameState {
 
    int stateID = 2;
    Sound fx = null;
    boolean insideStartGame = false;
    boolean insideExit = false;
    Image startGameOption = null;
    Image exitOption = null;
    Image background = null;
 
    float startGameScale = 1;
    float exitScale = 1;
     
 
    PausedState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    
    
    
    @Override
    public int getID() {
        return stateID;
    }
    
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	background = new Image("assets/menu.jpg");
    	
    
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	background.draw(0,0);
    	g.drawString("Controls:\nWASD - Movement\nLeft Control - Slow Ship\nSpace Or Left Click - Fire\n\n\nClick To Begin\n\nEscape To Exit",330,100);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	int mouseX = input.getMouseX();
    	int mouseY = input.getMouseY();
    	System.out.println("PAUSED");
    	
    	if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
    		sbg.enterState(SimpleGame.GAMEPLAYSTATE);
    		//GameplayState.currentState = STATES.START_GAME_STATE;
    		//GameplayState.music.loop(1f,.3f);
    		//GameplayState.needReset = true;
    		//GameplayState.playing = false;
    		//GameplayState.health = 100;
    		
    	}
    	
    	if (input.isKeyPressed(Input.KEY_ESCAPE))
    	{
    		//gc.exit();
    	}
    }
}