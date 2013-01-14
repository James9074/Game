package com.rotarydesign.SimpleGame;
 
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

 

public class SimpleGame extends StateBasedGame {
 
    public static final int MAINMENUSTATE          = 0;
    public static final int GAMEPLAYSTATE          = 1;
    public static final int PAUSEDSTATE          = 2;

    public static final int PAUSEDSDTATE          = 3;
 
    public SimpleGame()
    {
        super("Temp");
    }
 
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new SimpleGame());
 
         System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL","true");
         app.setDisplayMode(800, 400, false);
        // app.setVSync(true);
         app.setTargetFrameRate(60);
         app.setIcon("assets/icon32.png");
         app.start();
    }
 
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new GameplayState(GAMEPLAYSTATE));
        this.addState(new GameplayState(PAUSEDSTATE));
        

        this.addState(new GameplayState(PAUSEDSDTATE));
    }
}