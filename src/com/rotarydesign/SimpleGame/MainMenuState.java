package com.rotarydesign.SimpleGame;


import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

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
	JInputJoystick joystick = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);
	float hatSwitchPosition = joystick.getHatSwitchPosition();
	int xbox = 1;
	



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
    	
    	//set xbox
    	
    	
    	
    	
    	
    	int conid = 0;
    	Controller[] cons = ControllerEnvironment.getDefaultEnvironment().getControllers();
    	System.out.println("--------------------");
    	for (Controller c : cons)
        {
        	System.out.println(c.getName() + c.getType());
           if (c.getName().contains("XBOX"))
           {
              for (net.java.games.input.Component ca : c.getComponents())
              {
                 if (ca.getName().contains("X Axis") || ca.getName().contains("Y Axis"))
                 {
                    //System.out.println(ca.getPollData());
                	 
                	 
                 }
              }
           }
           
           else{ 
        	   conid++;}
        }
    	
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

    	
    	if( !joystick.isControllerConnected() ){
    		   System.out.println("No controller found!");
    		   // Do some stuff.
    		}
    	else{
    		System.out.println("Controller Found!");
    	}
    	
    }
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	itemSelect = 1;
    }
    public void controllerButtonPressed(int controller, int button){
    	System.out.println(controller + " and " + button);    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	boolean focus = gc.hasFocus();
    	
         
         
    	//if(input.isButtonPressed(1, 1)){
    	//	System.out.println("lol");
    	//}
    	
    	//int mouseX = input.getMouseX();
    	//int mouseY = input.getMouseY();
    	/*------------------------Get Input-----------------------------*/

    	if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
    		
    		//GameplayState.playing = false;
    		//menuMusic.fade(500, 0f, true);
    		//sbg.enterState(SimpleGame.COMMANDMENUSTATE, new FadeOutTransition(Color.black), null);
    		
    	}
    	
    	
    	//Enter or Exit
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
    	if (input.isKeyPressed(Input.KEY_UP) || input.isControllerUp(xbox))
    	{
    		if(itemSelect > 1){
    			itemSelect -= 1;
    		}
    	}
    	if (input.isKeyPressed(Input.KEY_DOWN) || input.isControllerDown(xbox))
    	{
    		if(itemSelect < 2){
    			itemSelect += 1;
    		}
    	}
    	
    	
    	//Controller Input Detection
    	/*
    	// Get current state of joystick! And check, if joystick is disconnected.
    	if( !joystick.pollController() ) {
    	   System.out.println("Controller disconnected!");
    	   // Do some stuff.
    	}
    	
    	
    	// Left controller joystick
    	int xValuePercentageLeftJoystick = joystick.getXAxisPercentage();
    	int yValuePercentageLeftJoystick = joystick.getYAxisPercentage();
    	 
    	// Right controller joystick
    	int xValuePercentageRightJoystick, yValuePercentageRightJoystick;
    	 
    	// stick type controller
    	if(joystick.getControllerType() == Controller.Type.STICK)
    	{
    	   // Right controller joystick
    	   xValuePercentageRightJoystick = joystick.getZAxisPercentage();
    	   yValuePercentageRightJoystick = joystick.getZRotationPercentage();
    	}
    	// gamepad type controller
    	else
    	{
    	   // Right controller joystick
    	   xValuePercentageRightJoystick = joystick.getXRotationPercentage();
    	   yValuePercentageRightJoystick = joystick.getYRotationPercentage();
    	 
    	   // If Z Axis exists.
    	   if(joystick.componentExists(Component.Identifier.Axis.Z)){
    	      int zAxisValuePercentage = joystick.getZAxisPercentage();
    	   }
    	}
    	
    	
    	 
    	
    	
    	// Number of buttons.
    	int numberOfButtons = joystick.getNumberOfButtons();
    	 
    	// Button one on the controller.
    	boolean joystickButton = joystick.getButtonValue(0);

    	for(int i=0;i<numberOfButtons;i++){
        	joystickButton = joystick.getButtonValue(i);
        	if(joystickButton){
        		//System.out.println(i);
        		if(i==0){
        			/*if(itemSelect == 1){
            			sbg.enterState(SimpleGame.COMMANDMENUSTATE, new FadeOutTransition(Color.black), null);
            	    	}
            	    	else if(itemSelect == 2){
            	    		sbg.enterState(SimpleGame.COMMANDMENUSTATE, new FadeOutTransition(Color.black), null);
            	    	}
        		}
        		}

    	}
    	
    	//D-PAD Hat switch input
    	hatSwitchPosition = joystick.getHatSwitchPosition();
    	if(Float.compare(hatSwitchPosition, Component.POV.OFF) == 0){
    	  // Hat switch is not pressed. The same as Component.POV.CENTER
    	}else if(Float.compare(hatSwitchPosition, Component.POV.UP) == 0){
    	   // Do stuff when UP is pressed.
    		if(itemSelect > 1){
    			itemSelect -= 1;
    		}
    	}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN) == 0){
    	   // Do stuff when DOWN is pressed.
    		if(itemSelect < 2){
    			itemSelect += 1;
    		}
    	}else if(Float.compare(hatSwitchPosition, Component.POV.LEFT) == 0){
    	   // Do stuff when LEFT is pressed.
    	}else if(Float.compare(hatSwitchPosition, Component.POV.RIGHT) == 0){
    	   // Do stuff when RIGHT is pressed.
    	}else if(Float.compare(hatSwitchPosition, Component.POV.UP_LEFT) == 0){
    	   // Do stuff when UP and LEFT is pressed.
    	}else if(Float.compare(hatSwitchPosition, Component.POV.UP_RIGHT) == 0){
    	   // Do stuff when UP and RIGHT is pressed.
    	}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN_LEFT) == 0){
    	   // Do stuff when DOWN and LEFT is pressed.
    	}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN_RIGHT) == 0){
    	   // Do stuff when DOWN and RIGHT is pressed.
    	}
    	*/
    	/*---------------------Get Input End-----------------------------*/

    	
    	
    	
    	 
    	
    	
    	
    	
    	
    	/*------------------------UPDATE DELTA-----------------------------*/
        /*
        System.out.println("--------------Delta Results ------------");
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
    	}
    	/*----------------------UPDATE DELTA END---------------------------*/
    	
    	/*------------------------UPDATE STAR BACKGROUND--------------------------*/	
    	for(int i = 0; i < stars.size(); i++)
    	{
    		
    		if(focus){
    		stars.get(i).update(deltaAverage);
    		}
    		//System.out.println(stars.get(i).posY);
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
    	//System.out.println(deltaAverage *.1f);
    	/*------------------UPDATE STAR BACKGROUND END--------------------*/	
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