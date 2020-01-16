import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * The type Extra life.
 * @author Steven
 */
public class ExtraLife extends Sprite {
    /**
     * The constant LIFE_LAST_DURATION.
     */
    public static final int LIFE_LAST_DURATION = 14000;
    /**
     * The constant LIFE_MOVE_DURATION.
     */
    public static final int LIFE_MOVE_DURATION = 2000;

    /**
     * The constant LIFE_SPAWN_START.
     */
    public static final int LIFE_SPAWN_START = 25000;
    /**
     * The constant LIFE_SPAWN_END.
     */
    public static final int LIFE_SPAWN_END = 35000;
    /**
     * The constant LOG_ORIGIN.
     */
    public static final int LOG_ORIGIN = 0;

    /**
     * Other variables of the ExtraLife Class
     */
    private Movers log;
    private boolean direction;
    private int countLifeMove;

    /**
     * Instantiates a new Extra life.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param log       the log
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public ExtraLife(String imageSrc, float x, float y, Movers log, boolean direction) throws SlickException {
        super(imageSrc, x, y);
        this.log = log;
        this.direction = direction;
    }

    /**
     * Method to update the ExtraLife locations
     * @param input
     * @param delta
     */
    @Override
    public void update(Input input, int delta) {
        this.setX(this.log.getX() + (countLifeMove * App.TILE_SIZE));
        super.update(input, delta);
    }

    /**
     * Method to render ExtraLife
     */
    @Override
    public void render() {
        super.render();
    }


    /**
     * Move right.
     */
    public void moveRight(){
        countLifeMove ++;
    }

    /**
     * Move left.
     */
    public void moveLeft(){
        countLifeMove --;
    }

    /**
     * Get random spawn time.
     *
     * @return the int
     */
    public static int getRandomSpawnTime(){
        Random r = new Random();
        return (r.nextInt((LIFE_SPAWN_END - LIFE_SPAWN_START) + 1) + LIFE_SPAWN_START);
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public boolean getDirection() {
        return direction;
    }

    /**
     * Method to move the extra life.
     *
     * @param input the input
     * @param delta the delta
     */
    public void move(Input input, int delta){
        // Check the last move of the extra life
        int lastCountMove = this.countLifeMove;

        // move in direction in case next move collides with water
        if(this.getDirection()){
            this.moveRight();
        }
        else{
            this.moveLeft();
        }

        this.update(input, delta);

        // Checks if next move intersects with water tiles
        if(!this.getBoundingBox().intersects(this.log.getBoundingBox())) {

            // Change the direction
            this.direction = !this.direction;

            // If the extra life is going right and next move were to collide with water tiles
            if(lastCountMove > LOG_ORIGIN){
                moveLeft();
                moveLeft();
                this.update(input, delta);
            }
            // If extra life is going left and next move were to collide with water tiles
            else{
                moveRight();
                moveRight();
                this.update(input, delta);
            }
        }
    }


}
