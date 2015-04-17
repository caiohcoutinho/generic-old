package gurps.graphics;

import de.matthiasmann.twl.FileSelector;
import gurps.application.Configuration;
import gurps.graphics.components.MenuLink;
import gurps.graphics.components.TextInput;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;

import javax.swing.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Caio on 14/04/2015.
 */
public class GraphicsFacade {

    private GameContainer gameContainer;
    private Graphics graphics;
    private Integer width;
    private Integer height;
    private boolean indo = true;
    private static final Logger LOGGER = Logger.getLogger(GraphicsFacade.class.getName());
    private static final Font DEFAULT_MENU_FONT = new TrueTypeFont(new java.awt.Font(Configuration.MENU_FONT_NAME,
            Configuration.MENU_FONT_STYLE, Configuration.MENU_FONT_SIZE), false);
    private static final Font MOUSE_OVER_MENU_FONT = new TrueTypeFont(new java.awt.Font(Configuration.MENU_FONT_NAME,
            Configuration.MENU_FONT_MOUSE_OVER_STYLE, Configuration.MENU_MOUSE_OVER_FONT_SIZE), false);

    public GraphicsFacade() {
    }

    public GameContainer getGameContainer() {
        return gameContainer;
    }

    public void setGameContainer(GameContainer gameContainer) {
        this.gameContainer = gameContainer;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void drawVerticalMenu() {
        Integer width = this.getWidth();
        Float leftBoarderLineX = width * Configuration.MENU_LEFT_BOARDER_SCREEN_RATIO;
        Float rightBoarderLineX = width * Configuration.MENU_RIGHT_BOARDER_SCREEN_RATIO;
        this.drawPairOfVerticalLines(0, this.getHeight(), leftBoarderLineX, rightBoarderLineX);
    }

    public void drawSubVerticalMenu() {
        Integer width = this.getWidth();
        Float leftBoarderLineX = width * Configuration.MENU_LEFT_BOARDER_SCREEN_RATIO;
        Float rightBoarderLineX = width * Configuration.MENU_RIGHT_BOARDER_SCREEN_RATIO;
        int height = (int) (this.getHeight() * Configuration.MENU_SUB_MENU_HEIGHT_RATIO);
        this.drawPairOfVerticalLines(0, height, leftBoarderLineX, rightBoarderLineX);
        graphics.drawLine(leftBoarderLineX, height, rightBoarderLineX, height);
    }

    private void drawPairOfVerticalLines(Integer initialY, Integer finalY, float leftBoarderLineX, float rightBoarderLineX) {
        // Set color
        Graphics graphics = this.getGraphics();
        graphics.setColor(Configuration.MENU_BOARDER_COLOR);

        // Draw left boarder line
        graphics.drawLine(leftBoarderLineX, initialY, leftBoarderLineX, finalY);

        // Draw right boarder line
        graphics.drawLine(rightBoarderLineX, initialY, rightBoarderLineX, finalY);
    }

    public Integer getWidth() {
        if(this.width == null){
            this.width = this.getGameContainer().getWidth();
        }
        return this.width;
    }

    public Integer getHeight() {
        if(this.height == null){
            this.height = this.getGameContainer().getHeight();
        }
        return this.height;
    }

    public void drawMenuBackground() {
        Graphics graphics = this.getGraphics();
        graphics.setBackground(Configuration.MENU_BACKGROUND_COLOR);
        graphics.clear();
    }

    public void drawMenuHeadings(ComponentListener listener, String title, List<MenuLink> links){
        this.drawMenuHeadings(listener, title, null, links);
    }

    public void drawMenuHeadings(ComponentListener listener, String title, MenuLink backLink, List<MenuLink> links){
        if(title == null && (links == null || links.isEmpty())){
            LOGGER.warning("attempt to draw empty links list");
        } else{
            Graphics graphics = this.getGraphics();
            GameContainer gameContainer = this.getGameContainer();
            int x = (int) (width * Configuration.MENU_HEADING_WIDTH_RATIO);
            int y = (int) (width * Configuration.MENU_HEADING_HEIGTH_RATIO);
            int linkWidth = Configuration.MENU_HEADING_MOUSE_OVER_AREA_WIDTH;
            int linkHeight = Configuration.MENU_HEADING_MOUSE_OVER_AREA_HEIGTH;
            int ySpace = (int) (height * Configuration.MENU_HEADING_HEIGTH_RATIO_SPACE);

            graphics.setFont(DEFAULT_MENU_FONT);

            graphics.setColor(Configuration.MENU_FONT_COLOR);
            graphics.drawString(title, x, y);

            if(backLink != null) {
                this.drawHeading(backLink, (int) (x + width * Configuration.MENU_HEADING_BACK_LINK_WIDTH_RADIO_SPACE),
                        y, linkWidth, linkHeight, listener);
            }
            y += ySpace;

            for(MenuLink link : links){
                y += ySpace;
                this.drawHeading(link, x, y, linkWidth, linkHeight, listener);
            }
        }
    }

    private void drawHeading(MenuLink link, int x, int y, int linkWidth, int linkWeight, ComponentListener listener) {
        if(link.getMouseOverArea() == null){
            link.setMouseOverArea(new MouseOverArea(this.getGameContainer(),
                    null, x, y, linkWidth, linkWeight, listener));
        }
        MouseOverArea mouseOverArea = link.getMouseOverArea();
        String text = link.getText();
        graphics.setColor(Configuration.MENU_FONT_COLOR);
        if(mouseOverArea.isMouseOver()) {
            graphics.setFont(MOUSE_OVER_MENU_FONT);
        } else{
            graphics.setFont(DEFAULT_MENU_FONT);
        }
        graphics.drawString(text, x, y);

        mouseOverArea = link.getMouseOverArea();
        mouseOverArea.setNormalColor(Color.transparent);
        mouseOverArea.setMouseOverColor(Color.transparent);
        link.render(gameContainer, graphics);
    }

    public void drawSubMenu(ComponentListener listener, String title, String subMenuTitle, MenuLink backLink) {
        Graphics graphics = this.getGraphics();
        GameContainer gameContainer = this.getGameContainer();
        int x = (int) (width * Configuration.MENU_HEADING_WIDTH_RATIO);
        int y = (int) (width * Configuration.MENU_HEADING_HEIGTH_RATIO);
        int linkWidth = Configuration.MENU_HEADING_MOUSE_OVER_AREA_WIDTH;
        int linkHeight = Configuration.MENU_HEADING_MOUSE_OVER_AREA_HEIGTH;
        int ySpace = (int) (height * Configuration.MENU_HEADING_HEIGTH_RATIO_SPACE);

        graphics.setFont(DEFAULT_MENU_FONT);

        graphics.setColor(Configuration.MENU_FONT_COLOR);
        graphics.drawString(title, x, y);
        int y1 = y + 2*ySpace;
        graphics.drawString(subMenuTitle, x, y1);
        this.drawHeading(backLink, (int) (x + width * Configuration.MENU_HEADING_BACK_LINK_WIDTH_RADIO_SPACE),
                y1, linkWidth, linkHeight, listener);
    }


    public void drawTextInput(TextInput textInput, ComponentListener listener) {
        if(textInput.getTextField() == null) {
            Integer width = this.getWidth();
            Integer descriptionX = (int) (width * Configuration.MID_FIELD_WIDTH_RATIO);
            Integer descriptionY = (int) (this.getHeight() * Configuration.MID_FIELD_HEIGTH_RATIO);
            Integer descriptionWidth = Configuration.FIELD_WIDTH;
            Integer descriptionHeight = Configuration.FIELD_HEIGTH;
            textInput.setTextField(new TextField(this.getGameContainer(), DEFAULT_MENU_FONT, descriptionX, descriptionY,
                    descriptionWidth, descriptionHeight));
        }
        graphics.setColor(Color.white);


        TextField textField = textInput.getTextField();
        textField.setFocus(true);
        textField.setTextColor(Color.black);
        textField.setBackgroundColor(Color.white);
        textField.render(this.getGameContainer(), this.getGraphics());

    }

    public void drawContentBox() {
        Integer width = this.getWidth();
        Float leftBoarderLineX = width * Configuration.MENU_LEFT_BOARDER_SCREEN_RATIO;
        Float rightBoarderLineX = width * (1 - Configuration.MENU_LEFT_BOARDER_SCREEN_RATIO);
        Integer height = this.getHeight();
        int minY = (int) (height * Configuration.MENU_CONTENT_BOX_HEIGHT_RATIO);
        this.drawPairOfVerticalLines(minY, height, leftBoarderLineX, rightBoarderLineX);
        graphics.drawLine(leftBoarderLineX, minY, rightBoarderLineX, minY);
    }

    public void quit() {
        this.getGameContainer().exit();
    }
}
