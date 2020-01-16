import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Race car.
 */
public class RaceCar extends Vehicles {

    /**
     * The constant CAR_SPEED.
     */
    public static final float CAR_SPEED = 0.5f;

    // Direction of race car
    private int carDirection;

    /**
     * Instantiates a new Race car.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public RaceCar(String imageSrc, float x, float y, boolean direction) throws SlickException {
        super(imageSrc, x, y, direction);
        this.setSpeed(CAR_SPEED);
    }

    /**
     * check if car is in screen
     * @return
     */
    @Override
    public boolean isInScreen(){
        return this.getX() > App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2
                && this.getX() < App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2;
    }

    /**
     * method to update car positions
     * @param input
     * @param delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        if(this.getDirection()){
            carDirection = Sprite.RIGHT;
        }
        else{
            carDirection = Sprite.LEFT;
        }

        this.moveX(delta, this.carDirection);

        // If the car goes out of screen, set X back to the most end (depending on the direction)
        if(!isInScreen()){
            if(this.carDirection == Sprite.LEFT){
                this.setX(App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2);
            }
            else{
                this.setX(App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2);
            }
        }
    }

    /**
     * method to render the race cars
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * method to make the cars move
     * @param delta
     * @param direction
     */
    @Override
    public void moveX(int delta, int direction) {
        this.setX(this.getX() + delta * CAR_SPEED * direction);
    }

}