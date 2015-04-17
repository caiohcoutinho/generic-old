package gurps.states.universe;

import de.matthiasmann.twl.Table;
import de.matthiasmann.twl.renderer.AnimationState;
import gurps.application.Label;
import gurps.graphics.GraphicsFacade;
import gurps.graphics.components.*;
import gurps.states.State;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.AbstractComponent;

import javax.swing.table.TableModel;

/**
 * Created by Caio on 15/04/2015.
 */
public class UniverseGearState extends State {

    private String title;
    private String subMenuTitle;
    private MenuLink backLink;
    private State nextState;
    private TextInput name;
    private IconUploader icon;
    private TextInput description;
    private Table table;

    public UniverseGearState() {
        this.title = Label.UNIVERSE;
        this.subMenuTitle = Label.GEAR;
        this.backLink = new MenuLink(Label.BACK);
        this.name = new TextInput(Label.GEAR_NAME, Label.GEAR_NAME);
        this.icon = new IconUploader();
        this.description = new TextInput(Label.GEAR_DESCRIPTION, Label.GEAR_DESCRIPTION); // TODO: Usar TextArea
        this.table = new Table(new GearTableModel());
    }

    @Override
    public State iterate(GameContainer container, int delta) {
        this.getName().react();
        this.getDescription().react();
        if(this.getNextState() != null){
            return this.getNextState();
        }
        return this;
    }

    @Override
    public void stateDraw() {
        GraphicsFacade facade = getFacade();
        facade.drawBackground();

        facade.drawSubMenu(this, this.getTitle(), this.getSubMenuTitle(), this.getBackLink());
        facade.drawContent(this, this.getIcon(), this.getName(), this.getDescription(), this.getTable());

        facade.getGui().update();
    }

    public void componentActivated(AbstractComponent source) {
        if(this.getBackLink().getMouseOverArea().equals(source)){
            this.setNextState(new UniverseState());
        } else if (this.getIcon().getMouseOverArea().equals(source)){
            this.getIcon().addImage();
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

    public TextInput getName() {
        return name;
    }

    public void setName(TextInput name) {
        this.name = name;
    }


    public IconUploader getIcon() {
        return icon;
    }

    public void setIcon(IconUploader icon) {
        this.icon = icon;
    }

    public TextInput getDescription() {
        return description;
    }

    public void setDescription(TextInput description) {
        this.description = description;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
