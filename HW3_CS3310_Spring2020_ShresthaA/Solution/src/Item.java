/**
 * Item with required parameters
 */

public class Item {

    private String name;
    private int minStrenght;
    private int maxStrenght;
    private String rarity;
    private int currentStrenght;

    /**
     * Typical constructor
     *
     * @param line with basic data
     */
    public Item(String line) {
        String[] data = line.split(",");
        name = data[0];
        minStrenght = Integer.parseInt(data[1]);
        maxStrenght = Integer.parseInt(data[2]);
        rarity = data[3];
    }

    /**
     * @return string representation
     */

    @Override
    public String toString() {
        return rarity + " " + name + ", " + currentStrenght;
    }

    /**
     * compare object by both parts of name
     *
     * @param item another object
     * @return result
     */
    public boolean equalsName(Item item) {
        if (name.equals(item.name)) {
            return rarity.equals(item.rarity);
        } else {
            return false;
        }
    }


    /*Getters and setters*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinStrenght() {
        return minStrenght;
    }

    public void setMinStrenght(int minStrenght) {
        this.minStrenght = minStrenght;
    }

    public int getMaxStrenght() {
        return maxStrenght;
    }

    public void setMaxStrenght(int maxStrenght) {
        this.maxStrenght = maxStrenght;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getCurrentStrenght() {
        return currentStrenght;
    }

    public void setCurrentStrenght(int currentStrenght) {
        this.currentStrenght = currentStrenght;
    }
}
