import org.newdawn.slick.SlickException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The type Level reader.
 */
public class LevelReader {
    private static ArrayList<Vehicles> vehicles;
    private static ArrayList<Tiles> tiles;
    private static ArrayList<Movers> movers;
    private static World world;


    /**
     * Initialise level.
     *
     * @param world    the world
     * @param fileName the file name
     * @throws IOException    the io exception
     * @throws SlickException the slick exception
     */
    public static void initialiseLevel(World world, String fileName) throws IOException, SlickException{
        vehicles = world.getVehicles();
        tiles = world.getTiles();
        movers = world.getMovers();
        File file = new File(fileName);
        LevelReader.world = world;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String text;

            // Read entire file
            while((text = br.readLine()) != null){
                if(text.contains(",")){
                    String[] levelData = text.split(",");

                    // Take and split data accordingly
                    String spriteName = levelData[0];
                    float xCoordinate = Float.parseFloat(levelData[1]);
                    float yCoordinate = Float.parseFloat(levelData[2]);

                    // If the data is a Tile Class, add it into Tiles
                    if(spriteName.equals("water") || spriteName.equals("grass") || spriteName.equals("tree")){
                        addTiles(spriteName, xCoordinate, yCoordinate);
                    }

                    // Vehicles and Movers have a boolean direction, we use this part to add them
                    // because they contain direction
                    else{
                        boolean direction = Boolean.parseBoolean(levelData[3]);
                        addVehicles(spriteName, xCoordinate, yCoordinate, direction);
                        addMovers(spriteName, xCoordinate, yCoordinate, direction);
                    }
                }
            }
        }
    }

    /**
     * Add vehicles.
     *
     * @param name      the name
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public static void addVehicles(String name, float x, float y, boolean direction) throws SlickException {
        if (name.equals("bus")) {
            vehicles.add(new Bus
                    ("assets/bus.png", x, y, direction)); // change dir to boolean
        } else if (name.equals("bike")) {
            vehicles.add(new Bike
                    ("assets/bike.png", x, y, direction));
        } else if (name.equals("racecar")) {
            vehicles.add(new RaceCar
                    ("assets/racecar.png", x, y, direction));
        }
    }

    /**
     * Add movers.
     *
     * @param name      the name
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public static void addMovers(String name, float x, float y, boolean direction) throws SlickException{
        if (name.equals("bulldozer")){
            movers.add(new Bulldozer
                    ("assets/bulldozer.png", x, y, direction));
        }

        else if (name.equals("turtle")){
            movers.add(new Turtles
                    ("assets/turtles.png", x, y, direction));
        }

        else if (name.equals("log")){
            movers.add(new ShortLog
                    ("assets/log.png", x, y, direction));
        }

        else if (name.equals("longLog")){
            movers.add(new LongLog
                    ("assets/longlog.png", x, y, direction));
        }
    }

    /**
     * Add tiles.
     *
     * @param name the name
     * @param x    the x
     * @param y    the y
     * @throws SlickException the slick exception
     */
    public static void addTiles(String name, float x, float y) throws SlickException {
        if (name.equals("water")){
            tiles.add(new WaterTiles
                    ("assets/water.png", x, y)); // change dir to boolean
        }

        else if (name.equals("grass")){
            tiles.add(new GrassTiles
                    ("assets/grass.png", x, y));
        }

        else if (name.equals("tree")){
            tiles.add(new TreeTiles
                    ("assets/tree.png", x, y));
        }
    }
}
