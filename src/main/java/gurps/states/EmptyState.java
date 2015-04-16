package gurps.states;

import com.sun.deploy.util.ArrayUtil;
import gurps.graphics.components.MenuLink;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio on 15/04/2015.
 */
public class EmptyState extends State implements ComponentListener {

    private String name;
    private long bornTime;

    public EmptyState(String name) {
        this.name = name;
        this.bornTime = System.currentTimeMillis();
    }

    public void componentActivated(AbstractComponent source) {

    }

    @Override
    public State iterate(GameContainer container, int delta) {
        if(System.currentTimeMillis() - this.bornTime > 1000){
            return new MenuState();
        }
        return this;
    }

    @Override
    public void stateDraw() {
        getFacade().drawMenuHeadings(this, this.getName(), new ArrayList<MenuLink>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
