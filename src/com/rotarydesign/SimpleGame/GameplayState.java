package com.rotarydesign.SimpleGame;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class GameplayState extends BasicGameState{
	
	private enum STATES {
        START_GAME_STATE, INITIALIZE, MOVING_PIECE_STATE, PLAYING,
        PAUSE_GAME_STATE, HIGHSCORE_STATE, GAME_OVER_STATE
    }
	
	public static STATES currentState = null;
	
	@Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException
    {
		gc.setShowFPS(false); 
        super.enter(gc, sb);
        gc.setAlwaysRender(true);
        
        currentState = STATES.START_GAME_STATE;
        
 
        //score = 0;
    }
	
	int stateID = 1;
	 GameplayState( int stateID ) 
	    {
	       this.stateID = stateID;
	    }
	 
	 @Override
	    public int getID() {
	        return stateID;
	    }
	 
	public void reset() {

	}
	
	Image land = null;
	Image plane = null;
	Image shieldIcon;
	Bar healthBar = null;
	Bar chargeBar = null;
	Polygon planePoly;
	Ellipse planeShield;
	Rectangle messageBox;
	Rectangle healthMeter;
	Rectangle chargeMeter;
	GradientFill healthFill;
	GradientFill chargeFill;
	Image bullet = null;
	Image shield = null;
	Image hud = null;
	Image crack = null;
	static Sound laser = null;
	Sound smoke = null;
	Sound shieldOn = null;
	Level levelState = null;
	ArrayList <Bullet> bullets = new ArrayList <Bullet>();
	static ArrayList <Powerup> powerups = new ArrayList <Powerup>();
	static ArrayList <Bullet> enemyBullets = new ArrayList <Bullet>();
	ArrayList <Enemy> enemies = new ArrayList <Enemy>();
	static ArrayList <Background> backgrounds = new ArrayList <Background>();
	public Music music = null;
	int x = 0;
	int y = 100;
	float scale = 1.0f;
	float lastFire = 0 ;
	float lastSpawn = 0;
	float cooldownLimit = 200;
	float crackFade = 0;
	int spawnInterval = 500;
	int landX = 0;
	int score = 0;
	static int level = 1;
	int bulletRecharge = 0;
	float bulletPassing = 0;
	static boolean playing = true;
	static int health = 100;
	float speed = 0 ;
	float endTimer = 0;
	boolean cooldown = false;
	boolean powerupShield = false;
	static boolean needReset = false;
	int enemiesPassed = 0;
	int playerBulletDamage = 20;
	int timer;
	static float deltaTime = 0;
	int lastPowerupSpawn = 0;
	int chargeX = 160;
	int chargeY = 33;
	int healthX = 350;
	int healthY = 5;
	int powerupSpawnInterval = 0;
	
	float deltaAdd = 0;
	float deltaNumber = 0;
	static float deltaAverage = 0;
	static Color textColor = null;
	Color messageBoxColor = null;
	Color healthColor = null;
	
	 
 

 
	public void initialize()
	{
		
        healthBar.bars = 5;
     	chargeBar.bars = 0;
     	enemies.clear();
     	enemyBullets.clear();
     	backgrounds.clear();
     	powerups.clear();
     	bullets.clear();
     	x = 0;
     	y = 100;
     	scale = 1.0f;
     	lastFire = 0 ;
     	lastSpawn = 0;
     	spawnInterval = 0;
     	landX = 0;
     	score = 0;
     	level = MainMenuState.levelSelect;
     	bulletRecharge = 0;
     	bulletPassing = 0;
     	playing = true;
     	health = 100;
     	speed = 0 ;
     	timer = 0;
     	endTimer = 0;
     	cooldown = false;
     	powerupShield = false;
     	if(level == 1 || level == 2){
     			music.loop(1f,.3f);
     	}
     	needReset = false;
     	enemiesPassed = 0;
     	playerBulletDamage = 20;
     	textColor = null;
     	
     	levelState.update(level);
     	
	}
    public void init(GameContainer gc, StateBasedGame sbg) 
			throws SlickException {
    	
    	//File filename = new File("C:/levels/level01.txt");
    	//FileReader fw = new FileReader(filename);
    	/*FileReader reader;
    	//Scanner in = new Scanner(new FileReader("levels/level01.txt"));
        while (in.hasNextLine()) // NOT at the end of the stream, more input is available
        {
            String thisLine = in.nextLine(); // Get an entire line
            for (int index=0; index < thisLine.length(); index++)
            {
                char ch = thisLine.charAt(index);
                System.out.print(ch);
            }
            System.out.println();
        }
        in.close();*/

    	//land = new Image("assets/background_space.png");
    	plane = new Image("assets/ship.png");
    	
		planePoly = new Polygon(new float[]{
				x,y,
				x+113,y,
				x+113,y+34,
				x+38,y+35,
				x,y+38
		});
		
		shield = new Image("assets/shield.png");
		hud = new Image("assets/hud.png");
		crack = new Image("assets/crack.png");
		crack.setAlpha(0);
		
		textColor = new Color(22f,22f,22f,.5f);
		messageBoxColor = new Color(0f,0f,0f,.4f);
     	//Image.setColor(1,50f,50f,50f);
     	
		planeShield = new Ellipse(x+56.5f,y+19f,90f,44f);
		messageBox = new Rectangle(312,170,185,100);		
		healthBar = new Bar(400,5);
		chargeBar = new Bar(600,5);
		levelState = new Level(level);

     	levelState.update(level);
		
    	laser = new Sound("assets/laser.wav");

    	chargeMeter = new Rectangle(chargeX,chargeY,485*bulletPassing/((cooldownLimit/16) * deltaAverage),24);
    	healthMeter = new Rectangle(healthX,healthY,100*health/100,20);
    	healthColor = new Color(100,180,0);
    	healthFill = new GradientFill(x, 0, new Color(30, 16, 14),
                x , y+20, healthColor);
    	chargeFill = new GradientFill(x, 0, new Color(0, 16, 114),
                x + 485, 0, new Color(145, 34, 34));
    	bullet = new Image("assets/bullet.jpg");
    	//bulletGeneration = new BulletGeneration();
    	music = new Music("assets/bgmusic.wav");
    	smoke = new Sound("assets/smoke.wav");
    	shieldOn = new Sound("assets/shieldOn.wav");
    	
    	backgrounds.add(new Background(0,0,levelState.level,1));
    	backgrounds.add(new Background(400,0,levelState.level,2));
    	backgrounds.add(new Background(800,0,levelState.level,3));
    	backgrounds.add(new Background(1200,0,levelState.level,4));    	
    	backgrounds.add(new Background(1600,0,levelState.level,5));
    	backgrounds.add(new Background(2000,0,levelState.level,6));
    	backgrounds.add(new Background(2400,0,levelState.level,7));
    	backgrounds.add(new Background(2800,0,levelState.level,8));
    }
 
   
    public void update(GameContainer gc, StateBasedGame sbg, int delta) 
			throws SlickException     
    {
    	/*------------------------SET VARIABLES----------------------------*/
    	Input input = gc.getInput();
    	deltaTime = delta;
    	/*-----------------------------------------------------------------*/
    	
        
        
        
        /*------------------------------DETECT INPUT---------------------------*/
        //Left SHIFT
        if(input.isKeyDown(Input.KEY_LSHIFT) && currentState == STATES.PLAYING)
        {
        	speed = 0.2f * delta;
        } 
        else
        {
        	speed = 0.4f * delta;
        }
        //A
        if(input.isKeyDown(Input.KEY_A) && currentState == STATES.PLAYING)
        {
          x-= speed;
          planePoly.setX(x);
        }
        //D
        if(input.isKeyDown(Input.KEY_D) && currentState == STATES.PLAYING)
        {
          x+= speed;
          planePoly.setX(x);
        }
        //W
        if(input.isKeyDown(Input.KEY_W) && currentState == STATES.PLAYING)
        {
            y-= speed ;
            planePoly.setY(y);
        }
        //S
        if(input.isKeyDown(Input.KEY_S) && currentState == STATES.PLAYING)
        {
            y+= speed;
            planePoly.setY(y);
        }
        //2
        if(input.isKeyDown(Input.KEY_2))
        {
            scale += (scale >= 5.0f) ? 0 : 0.1f;
        }
        //ESCAPE
        if(input.isKeyPressed(Input.KEY_ESCAPE))
        {
        	if(currentState != STATES.PLAYING)
        	{
	        	music.pause();
	        	//currentState = STATES.GAME_OVER_STATE;
	        	sbg.enterState(SimpleGame.MAINMENUSTATE, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	        }
        	else if(currentState == STATES.PLAYING)
        	{
        		music.pause();
        		currentState = STATES.PAUSE_GAME_STATE;
        	}
        }
        //ENTER
        if(input.isKeyPressed(Input.KEY_ENTER) && currentState != STATES.GAME_OVER_STATE)
        {
        	if(currentState != STATES.PAUSE_GAME_STATE)
        	{
        		music.pause();
        		currentState = STATES.PAUSE_GAME_STATE;
        	}
        	else
        	{
        		currentState = STATES.PLAYING;
        		music.resume();
        	}
        }
        //SPACE
        if((input.isKeyDown(Input.KEY_SPACE) ||  input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) && currentState == STATES.PLAYING)
        {
            if(bulletPassing < (cooldownLimit/16) * deltaAverage && cooldown == false){
        	if(lastFire > 80)
        	{
        		bullets.add(new Bullet(x+112,y+26,2,20));
        		laser.play(1f,.3f);
        		lastFire = 0; 
        		bulletRecharge++;
        		bulletPassing += deltaAverage *.5f;
        	}
        }
        else
			{
        		cooldown = true;
        		if(bulletPassing > (cooldownLimit/16) * deltaAverage)
        		{
        			bulletPassing = (cooldownLimit/16) * deltaAverage;
        		}
        		if(bulletPassing > 0)
        		{
        			bulletPassing -= (cooldownLimit/16) * deltaAverage * .01f;
        			if(bulletPassing < 0)
        			{
        				bulletPassing = 0;
        			}
        		}
        		if(bulletPassing == 0)
    			{
    				cooldown = false;
    			}
			}
        }
        else
        {
        	if(bulletPassing > 0)
        	{
        		bulletPassing -= (cooldownLimit/16) * deltaAverage * .01f;
    			if(bulletPassing < 0)
    			{
    				bulletPassing = 0;
    			}
        	}
        	else
        	{
        		bulletPassing = 0;
        		cooldown = false;
        	}
        }
        //RIGHT CLICK
        if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)&&currentState == STATES.PLAYING)
        {
    		if(powerupShield)
    		{
    			powerupShield = false;
    		}
    		else
    		{
    			powerupShield = true;
    		}
    	}
        /*----------------------DETECT INPUT END---------------------------*/
    	
    	
    	
    	
    	/*------------------------UPDATE DELTA-----------------------------*/
    	if(deltaNumber < 30)
    	{
    		deltaAdd += delta;
    		deltaNumber += 1;
    	}
    	else
    	{
    		deltaAverage = deltaAdd/30;
    		//--Debugging
    		//System.out.println(deltaAverage);
    	}
    	/*----------------------UPDATE DELTA END---------------------------*/
    	
    	
    	
    	
    	/*--------------------------GAMESTATES-----------------------------*/
    	if(needReset)
    	{
    		currentState = STATES.START_GAME_STATE;
    		needReset = false;
    	}
    	 switch(currentState)
         {
             case START_GAME_STATE:
                currentState = STATES.INITIALIZE;
             case INITIALIZE:
                 initialize();
                 init(gc, sbg);
                 currentState = STATES.PLAYING;
                 break;
             case PLAYING:
                 break;
             case PAUSE_GAME_STATE:
            	 //sbg.enterState(SimpleGame.PAUSEDSTATE);
                 break;
             case GAME_OVER_STATE:
            	// Highscores.getInstance().addScore(score);
            	 //currentState = STATES.START_GAME_STATE;
                // sbg.enterState(SimpleGame.MAINMENUSTATE);
                 break;
             default:
            	 currentState = STATES.INITIALIZE;
                  break;
            
         }
    	 /*-----------------------GAMESTATES END----------------------------*/
    	 
    	 
    	 
    	 if(currentState != STATES.PAUSE_GAME_STATE)
    	 {
	    	 /*-------------------------COLLISION-------------------------------*/
	    	//Bullet and Enemy
	    	for(int i = 0; i < bullets.size(); i++)
	    	{
	    		for(int j=0; j< enemies.size();j++)
	    		{
	    			
	    		if(bullets.get(i).bulletPoly.intersects(enemies.get(j).enemyPoly))
	    			{
	    			score+=15;
	    			if(enemies.get(j).health - playerBulletDamage > 0)
	    			{
	    			enemies.get(j).health -= playerBulletDamage;
	    			bullets.remove(bullets.get(i));
	    			break;
	    			}
	    			else
	    			{
	    			bullets.remove(bullets.get(i));
	    			enemies.remove(enemies.get(j));
	    			}
	    			break;
	    			}
	    		}
	    	}
	    	
	    	//Bullets and Bullets
	    	for(int i = 0; i < bullets.size(); i++)
	    	{
	    		for(int j=0; j< enemyBullets.size();j++)
	    		{
	    		if(bullets.get(i).bulletPoly.intersects(enemyBullets.get(j).bulletPoly))
	    			{
	    			score+=15;
	    			bullets.remove(bullets.get(i));
	    			enemyBullets.remove(enemyBullets.get(j));
	    			break;
	    			}
	    		}
	    	}
	    	
	    	//Shield Collision
	    	if(powerupShield)
	    	{
	    		//Shield and Bullets
	    		for(int j=0; j< enemyBullets.size();j++)
	    		{
	    		if(enemyBullets.get(j).bulletPoly.intersects(planeShield))
	    			{
	    			enemyBullets.remove(enemyBullets.get(j));
	    			}
	    		 }
	    		//Shield and Enemies
	    		for(int j=0; j< enemies.size();j++)
	    		{
	    		if(enemies.get(j).enemyPoly.intersects(planeShield))
	    			{
	    			enemies.remove(enemies.get(j));
	    			}
	    		 }
	    	}
	    	if(!powerupShield)
	    	{
	    		//Plane and Enemy Collide
	    		for(int j=0; j< enemies.size();j++)
	    		{
	    			if(enemies.get(j).enemyPoly.intersects(planePoly))
					{
	    				if(health > 0)
	    				{
	    					health-=20;
	    				}
	    				if(healthBar.bars < 0)
	    				{
	    					health = 0;
	    				}
	    				enemies.remove(enemies.get(j));
					}
	    		}
	    	}
	    		//Plane and Powerup
	    			for(int j=0; j< powerups.size();j++)
	    			{
	    			if(powerups.get(j).powerupPoly.intersects(planePoly))
	    					{
	    						powerupShield = true;
	    						shieldOn.play(1f,.5f);
	    				powerups.remove(powerups.get(j));
	    					}
	    			 }
	    	    	if(powerupShield)
	    	    	{
	    	    		timer += deltaAverage;
	    	    		if(timer > 5000)
	    	    		{
	    	    			powerupShield = false;
	    	    			timer = 0;
	    	    		}
	    	    	}
	    		
	    	//Plane and Bullet Collide
	    	if(!powerupShield)
	    	{
	    		for(int j=0; j< enemyBullets.size();j++)
	    		{
	    			if(enemyBullets.get(j).bulletPoly.intersects(planePoly))
	    			{
	    				if(health > 0)
	    				{
	    					health -= enemyBullets.get(j).damage;
	    				}
				
	    				if(healthBar.bars < 0)
	    				{
	    					health = 0;
	    				}
	    				enemyBullets.remove(enemyBullets.get(j));
	    			}
	    		}
			}
	    	/*--------------------------COLLISION------------------------------*/
	    	
	    	
	    	
	    	
	    	/*----------------------RANDOM NUMBER RESET------------------------*/
	    	if(lastSpawn == 0)
	    	{
	        	java.util.Random random = new java.util.Random();
	    		spawnInterval = 300+random.nextInt(800);
	    	}
	    	lastSpawn += deltaAverage;
	    	
	    	//Reset Powerup Random Spawn
	    	if(lastPowerupSpawn == 0)
	    	{
	        	java.util.Random random = new java.util.Random();
	    		powerupSpawnInterval = 10000+random.nextInt(25000);
	    	}
	    	lastPowerupSpawn += deltaAverage;
	    	/*--------------------RANDOM NUMBER RESET END-----------------------*/
	    	
	    	
	    	
	    	
	    	/*----------------------HEALTH BAR UPDATE---------------------------*/
	    	if(health <= 0)
	    	{
	    		health = 0;
	    		healthBar.bars = 0; 
	    	}
	    	else if(health <= 20)
	    	{
	    		healthBar.bars = 1; 
	    	}
	    	else if(health <= 40)
	    	{
	    		healthBar.bars = 2; 
	    	}
	    	else if(health <= 60)
	    	{
	    		healthBar.bars = 3; 
	    	}
	    	else if(health <= 80)
	    	{
	    		healthBar.bars = 4;
	    	}
	    	else
	    	{
	    		healthBar.bars = 5; 
	    	}
	    	
	    	//healthColor = new Color(),(100 - (-1*(health-200))),0);
	    	healthColor = new Color(-1*((health*2.5f)-250),(250 - (-1*((health*2.5f)-250))),0);
	    	System.out.println("Red: " + -1*((health*2.5f)-250));
	    	System.out.println("Green: " + (250-(-1*((health*2.5f)-250))));
	    	healthFill = new GradientFill(healthX+50, healthY, new Color(30, 16, 14),
	                  healthX+50, healthY+20, healthColor);
	    	healthBar.barOutlinePoly = new Image("assets/bar"+healthBar.bars+".png"); 
	    	healthBar.update();
	    	healthMeter = new Rectangle(healthX,healthY,100*health/100,20);
	    	/*---------------------HEALTH BAR UPDATE END------------------------*/
	        
	        
	        
	        
	        /*-----------------------CHARGE BAR UPDATE---------------------------*/
	    /*	if(bulletPassing >= ((cooldownLimit*.8)/16) * deltaAverage)
	    	{
	    		chargeBar.bars = 5;
	    	}
	    	else if(bulletPassing > ((cooldownLimit*.6)/16) * deltaAverage)
	    	{
	    		chargeBar.bars = 4;
	    	}
	    	else if(bulletPassing > ((cooldownLimit*.4)/16) * deltaAverage)
	    	{
	    		chargeBar.bars = 3;
	    	}
	    	else if(bulletPassing > ((cooldownLimit*.2)/16) * deltaAverage)
	    	{
	    		chargeBar.bars = 2;
	    	}
	    	else if(bulletPassing > 0)
	    	{
	    		chargeBar.bars = 1;
	    	}
	    	else
	    	{
	    		chargeBar.bars = 0;
	    	}	
	    	if(cooldown == false)
			{
				chargeBar.barOutlinePoly = new Image("assets/bar"+chargeBar.bars+".png");
			}
			else
			{
				chargeBar.barOutlinePoly = new Image("assets/redbar"+chargeBar.bars+".png");
			}
	    	chargeBar.update();*/
	    	chargeMeter = new Rectangle(chargeX,chargeY,485*(bulletPassing/((cooldownLimit/16) * deltaAverage)),24);
	    	/*-------------------------CHARGE BAR UPDATE END-------------------------*/
	    	
	    	
	    	
	    	
	    	/*------------------------------SPAWNS------------------------------*/
	    	if(lastSpawn > spawnInterval){
	        enemies.add(new Enemy());
	        lastSpawn = 0;
	        }
	    	if(lastPowerupSpawn > powerupSpawnInterval){
	            
	            powerups.add(new Powerup());
	            lastPowerupSpawn = 0;
	            }
	    	/*----------------------------SPAWNS END-----------------------------*/
	    	
	    	
	    	
	    	
	    	/*--------------------------PLAYER UPDATE---------------------------*/
	    	float move = .001f * delta;
	        x-= move;
	        planePoly.setX(x);
	    	
	    	lastFire += delta;
	        planeShield.setX(x-31);
	        planeShield.setY(y-23);
	        /*--------------------------PLAYER UPDATE END-----------------------*/
	    	
	    	
	    	
	    	
	    	/*---------------------------UPDATES--------------------------------*/
	    	for(int i = 0; i < enemies.size(); i++)
	    	{
	    		enemies.get(i).timePassed += deltaAverage * .5f;
	    		enemies.get(i).updateInterval = deltaAverage;
	    	}    	
	    	if(currentState == STATES.GAME_OVER_STATE)
	    	{
	    		endTimer += deltaAverage * .5f;
	    	}
	    	/*--------------------------UPDATES END------------------------------*/
	    	
	    	
	    	
	    	
	    	/*------------------------UPDATE BACKGROUND--------------------------*/	
	    	for(int i = 0; i < backgrounds.size(); i++)
	    	{
	    		backgrounds.get(i).update();
	    		if(backgrounds.get(i).posX < -3200)
	    		{
	    			backgrounds.remove(backgrounds.get(i));
	    		}
	    		if(backgrounds.get(i).posX == -2400 && backgrounds.get(i).tile == 1)
	    		{
	    	    	backgrounds.add(new Background(800,0,levelState.level,1));
	    	    	backgrounds.add(new Background(1200,0,levelState.level,2));
	    	    	backgrounds.add(new Background(1600,0,levelState.level,3));
	    	    	backgrounds.add(new Background(2000,0,levelState.level,4));    	
	    	    	backgrounds.add(new Background(2400,0,levelState.level,5));
	    	    	backgrounds.add(new Background(2800,0,levelState.level,6));
	    	    	backgrounds.add(new Background(3200,0,levelState.level,7));
	    	    	backgrounds.add(new Background(3600,0,levelState.level,8));
	    		}
	    	}
	    	/*------------------UPDATE BACKGROUND END--------------------*/	
	    	
	    	
	    	
	    	
	    	/*------------------UPDATE PLAYER BULLETS--------------------*/	
	    	for(int i = 0; i < bullets.size(); i++)
	    	{
	    		bullets.get(i).update(10);
	    		if(bullets.get(i).posX > 800)
	    		{
	    			bullets.remove(bullets.get(i));
	    		}
	    	}
	    	/*------------------UPDATE PLAYER BULLETS END-----------------*/	
	    	
	    	
	    	
	    	
	    	/*------------------UPDATE ENEMY BULLETS----------------------*/	
	    	for(int i = 0; i < enemyBullets.size(); i++)
	    	{
	    		enemyBullets.get(i).update(-10);
	    		if(enemyBullets.get(i).posX < -10)
	    		{
	    			enemyBullets.remove(enemyBullets.get(i));
	    		}
	    	}
	    	/*------------------UPDATE ENEMY BULLETS END-------------------*/	
	    	
	    	
	    	
	    	
	    	/*------------------UPDATE POWERUPS----------------------------*/	
	    	for(int i = 0; i < powerups.size(); i++)
	    	{
	    		powerups.get(i).update();
	    		if(powerups.get(i).posX < -20)
	    		{
	    			powerups.remove(powerups.get(i));
	    		}
	    	}
	    	/*-----------------UPDATE POWERUPS END-------------------------*/	    	
	
	    	
	    	
	    	
	    	/*------------------UPDATE ENEMIES-----------------------------*/
	    	
	    	for(int i = 0; i < enemies.size(); i++)
	    	{
	    		enemies.get(i).update();
	    		if(enemies.get(i).posX < -20)
	    		{
	    			enemies.remove(enemies.get(i));
	    			if(health > 0)
	    			{
	    				enemiesPassed += 1;
	    			}
	    		}
	    	}
	    	/*------------------UPDATE ENEMIES END------------------------*/
	    	
	    	
	    	
	    	
	    	/*---------------------UPDATE STATUS--------------------------*/
	    	
	    	
	    	
	    	if(health<=0)
	    	{ 
	    		
	    		//Upon death, play end sound and stop playing
	    		if(currentState != STATES.GAME_OVER_STATE)
	    		{	
	    			smoke.play();
	    			//music.fade(2,.1f,true);
	    			currentState = STATES.GAME_OVER_STATE;
	    			System.out.println(currentState);
	    			//playing = false;
	    		}
	    	}
	    	/*------------------UPDATE STATUS END-------------------------*/
    	}
    	
    }
    
    public void render(GameContainer gc, StateBasedGame sbg ,Graphics g) 
			throws SlickException 
    {
    	
    	/*-------------------------INIT------------------------------*/
    	g.setColor(textColor);
    	/*-----------------------INIT END----------------------------*/

    	

    	
		/*------------------RENDER BACKGROUND------------------------*/	
    	for(int i = 0; i < backgrounds.size(); i++)
    	{
    		//backgrounds.get(i).posX-=1;
    		backgrounds.get(i).background.draw(backgrounds.get(i).posX ,backgrounds.get(i).posY);
    	}
    	/*------------------RENDER BACKGROUND END--------------------*/	
    	
    	
    	
    	
    	/*------------------RENDER PLAYER BULLETS--------------------*/	
    	for(int i = 0; i < bullets.size(); i++)
    	{
    		bullets.get(i).bulletGraphic.draw(bullets.get(i).posX ,bullets.get(i).posY);
    	}
    	/*------------------RENDER PLAYER BULLETS END-----------------*/	
    	
    	
    	
    	
    	/*------------------RENDER ENEMY BULLETS----------------------*/	
    	for(int i = 0; i < enemyBullets.size(); i++)
    	{
    		enemyBullets.get(i).bulletGraphic.draw(enemyBullets.get(i).posX ,enemyBullets.get(i).posY);
    	}
    	/*------------------RENDER ENEMY BULLETS END-------------------*/	
    	
    	
    	
    	
    	/*------------------RENDER POWERUPS----------------------------*/	
    	for(int i = 0; i < powerups.size(); i++)
    	{
    		powerups.get(i).powerupGraphic.draw(powerups.get(i).posX ,powerups.get(i).posY);
    		
    		//--Debug
    		// g.draw(powerups.get(i).powerupPoly);   // --- Render powerupPoly
    	}
    	/*------------------RENDER POWERUPS END-----------------------*/	    	

    	
    	
    	
    	/*------------------RENDER ENEMIES----------------------------*/
    	
    	for(int i = 0; i < enemies.size(); i++)
    	{
    		enemies.get(i).enemyGraphic.draw(enemies.get(i).posX ,enemies.get(i).posY);
    	//--Debugging 
    	//  g.draw(enemies.get(i).enemyPoly);     // --- DRAW HITBOX
    	}
    	/*------------------RENDER ENEMIES END------------------------*/

    	
    	
    	
    	/*------------------RENDER PLAYER-----------------------------*/
    	
    	plane.draw(x,y,scale);
		 if(powerupShield)
		 {
			 shield.draw(x-30,y-23);
		 }
		 //--Debugging
		 //	 g.draw(planePoly);                    // DRAW PLAYER HITBOX
		 //  g.draw(planeShield);                  // DRAW SHIELD HITBOX
    	/*------------------RENDER PLAYER END-------------------------*/

		 
		 
		 
    	/*---------------------RENDER HUD-----------------------------*/
		//Draw Charge Bar
	    	
			 g.setColor(Color.darkGray);
		        //g.fillRect(chargeX, chargeY, 485, 24);
		        g.fill(chargeMeter, chargeFill); 
		        g.setColor(Color.white);
		//Draw HUD background
		 hud.draw(0,0);

		// chargeBar.barOutlinePoly.draw(400,3);
		//Draw Level
		g.drawString("Level: "+level,20,5);
		
    	//Draw Score
    	g.drawString("Score: " + score,200,5);
    	
    	
    	//Draw Health Bar
    	healthBar.barOutlinePoly.draw(600,3);
		 g.setColor(Color.darkGray);
	        g.fillRect(healthX, healthY, 100, 20);
	        g.fill(healthMeter, healthFill); 
	        g.setColor(Color.white);
    	
    	
    	
    	//System.out.println("BulletPassing: " +bulletPassing);
    	//Draw Overload Or Heat Text
    	if(cooldown)
    	{
    		g.setColor(Color.red);
    		g.drawString("OVERLOAD!",350,35);
    		g.setColor(Color.white);
    	}
    	//g.drawString("Heat:",350,15);
    	
    	g.drawString("Health: " + health,680,5);
    	g.drawString("Pass: " + enemiesPassed,700,35);
    	
    	//=======UPON PAUSE==========
    	//Draw Pause Box
    	if(currentState == STATES.PAUSE_GAME_STATE)
    	{ 
    		//Set color for game over box, then change it back to text color
    		g.setColor(messageBoxColor);
    		g.fill(messageBox);
    		g.setColor(Color.white);
    		g.drawString("PAUSED",375,180);	

    		g.drawString("Enter - Resume",342,205);	

    		g.drawString("Escape - Main Menu",322,225);	
    	}    
    	
    	//=====UPON GAME OVER========
    	//Draw Game Over Box
    	if(currentState == STATES.GAME_OVER_STATE)
    	{ 
    		//Set color for game over box, then change it back to text color
    		g.setColor(messageBoxColor);
    		g.fill(messageBox);
    		g.setColor(Color.white);
    		g.drawString("GAME OVER",363,190);	

    		//After half a second, draw "Press Escape"
    		if(endTimer > 500)
        	{
        		g.drawString("Press Escape", 350, 210);
        	}
    		
    	}
    	//Render Glass Crack Effect
    	if(health < 30)
    	{
    		if(crackFade < (100/16)*deltaAverage && crack.getAlpha() < 1)
    		{
    			
    			crackFade += deltaAverage;
    		}
    		else if(crackFade > (100/16)*deltaAverage && crack.getAlpha() < 1)
    		{
    			crack.setAlpha(crack.getAlpha() + .1f);
    			crackFade = 0;
    		}
    		crack.draw(0,0);
    		
    	}
    	/*------------------RENDER HUD END-------------------------------*/	
    	
    	/*---------------------------DEBUG-------------------------------*/
    	
	    	//If FPS drops, how does it effect delta related effects
    		//System.out.println("BulletPassing: " + bulletPassing);
    		//System.out.println("Delta Average: " + deltaAverage);
	
    	/*-------------------------DEBUG END-----------------------------*/
    }
    
}
