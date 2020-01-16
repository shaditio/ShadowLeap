import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Finish tiles.
 * @author Steven
 */
public class FinishTiles extends Tiles{

    /**
     * The constant finishWidth.
     */
    public static final int finishWidth = 96;
    /**
     * The constant finishHeight.
     */
    public static final int finishHeight = 48;

    // Variable to check if the finish tiles are occupied
    private boolean isOccupied = false;


    /**
     * Instantiates a new Finish tile.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @throws SlickException the slick exception
     */
    public FinishTiles(float x, float y, int width, int height) throws SlickException{
        super(x, y , width, height);
    }


    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);


    }

    @Override
    public void contactSprite(Sprite other) {
        // Check if the tile is occupied and fill in with player sprite
        if (!this.isOccupied) {
            this.isOccupied = true;
            this.setSpriteImage(other.getSpriteImage());
        }

        // Minus life if occupied
        else{
            if (other instanceof Player){
                super.contactSprite(other);
            }
        }
    }

    @Override
    public void render() {
        super.render();
    }

    /**
     * Check if finish tile is occupied .
     *
     * @return the boolean
     */
    public boolean isOccupied() {
        return isOccupied;
    }

}