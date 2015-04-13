package gurps;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends BasicGame {

    private TiledMap grassMap;
    private Animation sprite, up, down, left, right;
    private float x = 34f, y = 34f;

    public Main(String gamename) {
        super(gamename);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        grassMap = new TiledMap("sprites/tileset.tmx");
        String base = "sprites/chars/";
        String format = ".png";
        Image[] movementUp = {new Image(base + "90" + format), new Image(base + "91" + format), new Image(
                base + "92" + format), new Image(base + "91" + format)};
        Image[] movementDown = {new Image(base + "54" + format), new Image(base + "55" + format), new Image(
                base + "56" + format), new Image(base + "55" + format)};
        Image[] movementLeft = {new Image(base + "66" + format), new Image(base + "67" + format), new Image(
                base + "68" + format), new Image(base + "67" + format)};
        Image[] movementRight = {new Image(base + "78" + format), new Image(base + "79" + format), new Image(
                base + "80" + format), new Image(base + "79" + format)};
        int[] duration = {150, 150, 150, 150};

        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);

        sprite = right;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyDown(Input.KEY_UP)) {
            sprite = up;
            sprite.update(delta);
            // The lower the delta the slowest the sprite will animate.
            y -= delta * 0.1f;
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            sprite = down;
            sprite.update(delta);
            y += delta * 0.1f;
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            sprite = left;
            sprite.update(delta);
            x -= delta * 0.1f;
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            sprite = right;
            sprite.update(delta);
            x += delta * 0.1f;
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        grassMap.render(0, 0);
        sprite.draw((int) x, (int) y);
    }
}