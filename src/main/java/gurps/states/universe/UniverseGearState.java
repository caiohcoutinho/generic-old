package gurps.states.universe;

import gurps.application.Label;
import gurps.graphics.GraphicsFacade;
import gurps.graphics.components.MenuLink;
import gurps.graphics.components.TextInput;
import gurps.states.State;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.AbstractComponent;

import javax.swing.*;

/**
 * Created by Caio on 15/04/2015.
 */
public class UniverseGearState extends State {

    private String title;
    private String subMenuTitle;
    private MenuLink backLink;
    private State nextState;
    private TextInput description;

    public UniverseGearState() {
        this.title = Label.UNIVERSE;
        this.subMenuTitle = Label.GEAR;
        this.backLink = new MenuLink(Label.BACK);
        this.description = new TextInput(Label.GEAR_DESCRIPTION);
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
        facade.drawTextInput(this.getDescription(), this);
        facade.drawSubVerticalMenu();
        facade.drawContentBox();
        facade.drawSubMenu(this, this.getTitle(), this.getSubMenuTitle(), this.getBackLink());
    }

    public void componentActivated(AbstractComponent source) {
        if(this.getBackLink().getMouseOverArea().equals(source)){
            this.setNextState(new UniverseState());
        } else if(this.getDescription().getTextField().equals(source)){
            
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubMenuTitle() {
        return subMenuTitle;
    }

    public void setSubMenuTitle(String subMenuTitle) {
        this.subMenuTitle = subMenuTitle;
    }

    public MenuLink getBackLink() {
        return backLink;
    }

    public void setBackLink(MenuLink backLink) {
        this.backLink = backLink;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public TextInput getDescription() {
        return description;
    }

    public void setDescription(TextInput description) {
        this.description = description;
    }
}
