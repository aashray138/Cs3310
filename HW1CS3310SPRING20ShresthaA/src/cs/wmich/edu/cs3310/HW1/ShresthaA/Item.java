package cs.wmich.edu.cs3310.HW1.ShresthaA;
import java.util.Random;

/**
 * Class represent the item.
 * @author Aashray
 */
public final class Item implements Comparable<Item>{

    /**
     * Name
     */
    public String name;
    /**
     * Minimum Strength
     */
    public int minStrength;
    /**
     * Maximum Strength
     */
    public int maxStrength;
    /**
     * Rarity of the item
     */
    public String rarity;
    /**
     * Randomly generated strength between the max strength and the min strength
     */
    public int currentStrength;
    /**
     * Random to create a variable.
     */
    private static Random rand = new Random();
    
    /**
     * Constructor to create an item.
     * @param name of the weapon.
     * @param minStrength of the weapon.
     * @param maxStrength of the weapon.
     * @param rarity of the weapon.
     */
    public Item(String name, int minStrength, int maxStrength, String rarity) {
        this.name = name;
        this.minStrength = minStrength;
        this.maxStrength = maxStrength;
        this.rarity = rarity;
        this.randomCurrent();
    }
    /**
     * Assign random generated number to the current strength.
     */
    public void randomCurrent(){
        currentStrength = minStrength + rand.nextInt(maxStrength-minStrength);
    }

    /**
     * Compare two element of an item
     * @param o the item to be compared to.
     * @return return number less than zero if less
     *  Zero if equal
     *   more than zero otherwise.
     *   It is the method to sort the items accoring to their name and power.
     */
    @Override
    public int compareTo(Item o) {
        if(this.name.compareTo(o.name) == 0){
            return new  Integer(this.currentStrength).compareTo(o.currentStrength);
        }
        else {
            return this.name.compareTo(o.name);
        }
    }
    
}
