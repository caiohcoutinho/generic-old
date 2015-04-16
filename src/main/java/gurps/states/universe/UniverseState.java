package gurps.states.universe;

import gurps.application.Label;
import gurps.graphics.GraphicsFacade;
import gurps.graphics.components.MenuLink;
import gurps.states.MenuState;
import gurps.states.State;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio on 15/04/2015.
 */
public class UniverseState extends State {

    private String title;
    private List<MenuLink> links;
    private MenuLink back;
    private State nextState;

    public UniverseState() {
        this.setTitle(Label.UNIVERSE);
        List<MenuLink> links = new ArrayList<MenuLink>();
        for(String link : new String[]{
                Label.ADVANTAGES,
                Label.DISADVANTAGES,
                Label.SKILLS,
                Label.MAGICS,
                Label.GEAR,
                Label.STATUS,
                Label.CONDITIONS,
                Label.RACES,
        }){
            MenuLink menulink = new MenuLink(link);
            links.add(menulink);
        }
        this.setLinks(links);
        this.setBack(new MenuLink(Label.BACK));
    }

    @Override
    public State iterate(GameContainer container, int delta) {
        if(this.getNextState() != null){
            return this.getNextState();
        }
        return this;
    }

    @Override
    public void stateDraw() {
        GraphicsFacade facade = getFacade();
        facade.drawMenuBackground();
        facade.drawVerticalMenu();
        facade.drawMenuHeadings(this, this.getTitle(), this.getBack(), this.getLinks());
    }

    public void componentActivated(AbstractComponent source) {
        if(this.getBack().getMouseOverArea().equals(source)){
            this.setNextState(new MenuState());
        }
        for(MenuLink link : this.getLinks()){
            MouseOverArea area = link.getMouseOverArea();
            if (area.equals(source)) {
                if(link.getText().equals(Label.GEAR)){
                    this.setNextState(new UniverseGearState());
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

    public MenuLink getBack() {
        return back;
    }

    public void setBack(MenuLink back) {
        this.back = back;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}
