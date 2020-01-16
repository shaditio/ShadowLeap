import org.newdawn.slick.SlickException;

/**
 * The type Grass tiles.
 */
public class GrassTiles extends Tiles{

    /**
     * Instantiates a new Grass tile.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @throws SlickException the slick exception
     */
    public GrassTiles(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /**
     * rendering the grass tiles
     */
    @Override
    public void render() {
        super.render();
    }

}
