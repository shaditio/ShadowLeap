/**
 * Project for SWEN20003: Object Oriented Software Development 2018
 * by Steven Haditio and
 * by Eleanor McMurtry, University of Melbourne
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

import java.io.IOException;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
    /** screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;

    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;

    /** dimension pixels */
    public static final int TILE_SIZE = 48;

    /** x and y start coordinates*/
    public static final int START_COORDINATE_X = 0;
    public static final int START_COORDINATE_Y = 0;

    /** variable to initialise world */
    private World world;

    /** variable to terminate game */
    public static boolean gameOver = false;

    public App() {
        super("Shadow Leap");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        try {
            world = new World();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        try {
            world.update(input, delta);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if game ends
        if(gameOver){
            gc.exit();
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g) {
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(true);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}