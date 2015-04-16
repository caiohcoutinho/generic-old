package gurps.states;

import gurps.application.Label;
import gurps.graphics.GraphicsFacade;
import gurps.graphics.components.MenuLink;
import gurps.states.universe.UniverseState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Caio on 14/04/2015.
 */
public class MenuState extends State implements ComponentListener{

    private static final Logger LOGGER = Logger.getLogger(MenuState.class.getName());

    private String title;
    private List<MenuLink> links;
    private State nextState;

    public MenuState(){
        this.setTitle(Label.GAME_TITLE);
        List<MenuLink> links = new ArrayList<MenuLink>();
        for(String link : new String[]{Label.PLAY, Label.CHARACTER,
                Label.UNIVERSE, Label.CAMPAIGN, Label.CONFIGURATION, Label.EXIT}){
            links.add(new MenuLink(link));
        }
        this.setLinks(links);
    }

    public State iterate(GameContainer container, int delta) {
        return this.getNextState() == null? this: this.getNextState();
    }

    public void stateDraw() {
        GraphicsFacade facade = getFacade();
        facade.drawMenuBackground();
        facade.drawVerticalMenu();
        facade.drawMenuHeadings(this, this.getTitle(), this.getLinks());
    }

    public void componentActivated(AbstractComponent source) {
        for(MenuLink link : this.getLinks()){
            MouseOverArea area = link.getMouseOverArea();
            if (area.equals(source)) {
                if(link.getText().equals(Label.UNIVERSE)){
                    this.setNextState(new UniverseState());
                } else if(link.getText().equals(Label.EXIT)){
                    this.getFacade().quit();
                }
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MenuLink> getLinks() {
        return links;
    }

    public void setLinks(List<MenuLink> links) {
        this.links = links;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

}
