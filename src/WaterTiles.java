/** The code for the ActiveTile Class
 *  handles the water tiles (for now) */

import org.newdawn.slick.SlickException;

/**
 * The type Water tiles.
 */
public class WaterTiles extends Tiles {

    /**
     * Instantiates a new Water tiles.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @throws SlickException the slick exception
     */
    public WaterTiles(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /**
     * method to render the water tiles
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * method to check contact sprite with water tiles
     * @param other the other
     */
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
    }
}
