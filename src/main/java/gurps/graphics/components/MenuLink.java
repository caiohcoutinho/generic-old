package gurps.graphics.components;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * Created by Caio on 14/04/2015.
 */
public class MenuLink {

    private String text;
    private MouseOverArea mouseOverArea;
    private float x, y, width, height;

    public MenuLink(String text){
        this.text = text;
    }

    public MouseOverArea getMouseOverArea() {
        return mouseOverArea;
    }

    public void setMouseOverArea(MouseOverArea mouseOverArea) {
        this.mouseOverArea = mouseOverArea;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void render(GameContainer gameContainer, Graphics graphics){
        this.getMouseOverArea().render(gameContainer, graphics);
    }

}
