package gurps.application;

import gurps.states.MenuState;
import gurps.states.State;
import gurps.states.universe.UniverseGearState;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends BasicGame {

    private State state;

    public Main(String gamename) {
        super(gamename);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main(Label.GAME_TITLE));
            appgc.setShowFPS(Configuration.SHOW_FPS);
            appgc.setDisplayMode(Configuration.WIDTH, Configuration.HEIGHT, Configuration.FULLSCREEN);
            Display.setLocation(0,0);
            Integer targetFrameRate = Configuration.TARGET_FRAME_RATE;
            if(targetFrameRate != null){
                appgc.setTargetFrameRate(targetFrameRate);
            }
            appgc.start();

        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        //this.setState(new MenuState());
        this.setState(new UniverseGearState());
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        this.setState(this.getState().iterate(container, delta));
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        this.getState().draw(gc, g);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}