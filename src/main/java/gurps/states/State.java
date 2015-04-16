package gurps.states;

import gurps.graphics.GraphicsFacade;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.ComponentListener;

/**
 * Created by Caio on 14/04/2015.
 */
public abstract class State implements ComponentListener{

    protected static final GraphicsFacade facade = new GraphicsFacade();

    public static GraphicsFacade getFacade() {
        return facade;
    }

    public abstract State iterate(GameContainer container, int delta);

    public abstract void stateDraw();

    public void draw(GameContainer gc, Graphics g){
        GraphicsFacade facade = this.getFacade();
        if(facade.getGameContainer() == null || facade.getGraphics() == null) {
            facade.setGameContainer(gc);
            facade.setGraphics(g);
        }
        this.stateDraw();
    }
}
