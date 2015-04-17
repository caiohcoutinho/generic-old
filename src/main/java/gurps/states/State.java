package gurps.states;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;
import gurps.graphics.GraphicsFacade;
import org.lwjgl.LWJGLException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.ComponentListener;

import java.io.IOException;
import java.net.URL;

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
            try {
                LWJGLRenderer renderer = new LWJGLRenderer();
                GUI gui = new GUI(renderer);
                gui.applyTheme(ThemeManager.createThemeManager(getClass().getClassLoader().getResource(
                        "themes/theme.xml"), renderer));
                facade.setGui(gui);
            } catch (LWJGLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.stateDraw();
    }
}
