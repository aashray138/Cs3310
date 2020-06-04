/**
 * Hash holder for item data
 */
public class Bag {
    public static final int sizeHashtable = 199;
    public static final int addingSize = 125;
    public Item[] itemsArray;
    public Item[][] itemsList2;
    public int[] randomShuffle;
    public HashInterface hash;
    private int numberBag;
    private int probingType;

    /**
     * Complicated constructor
     *
     * @param items       for filling bag
     * @param probingType which type of probing will be used
     * @param numberBag   for output
     */
    public Bag(Item[] items, int probingType, int numberBag, InitInterface init, HashInterface hash) {
        this.numberBag = numberBag;
        this.probingType = probingType;
        this.hash = hash;
        init.init(this, items);
    }

    /**
     * return string representation of bag
     *
     * @param fullType required parameter
     * @return string view
     */
    public String print(boolean fullType) {
        StringBuilder res = new StringBuilder("Bag " + numberBag + " using ");
        switch (probingType) {
            case -1:
                res.append("Open ");
                break;
            case 0:
                res.append("Linear-Probing Closed ");
                break;
            case 1:
                res.append("Pseudo-Random-Probing Closed ");
                break;
            default:
                res.append("Double-Hashing-Probing Closed ");
                break;
        }
        res.append("Hashing with my Hashing Function").append(hash.getType()).append(": \n");
        if (fullType) {
            if (probingType == -1) {
                for (Item[] items : itemsList2) {
                    if (items != null) {
                        if (items.length == 1) {
                            res.append("\t").append(items[0]).append("\n");
                        } else {
                            res.append("\t(").append(items[0]).append(");");
                            for (int u = 1; u < items.length; u++) {
                                if (items[u] != null) {
                                    res.append(" (").append(items[u]).append(");");
                                }
                            }
                            res.append("\n");
                        }
                    }

                }
            } else {
                for (Item item : itemsArray) {
                    if (item != null) {
                        res.append("\t").append(item).append("\n");
                    }
                }
            }
        } else {
            int rows = 0;
            if (probingType == -1) {
                for (int i = 0, itemsListLength = itemsList2.length; i < itemsListLength; i++) {
                    Item[] items = itemsList2[i];
                    if (items != null) {
                        rows++;
                        if (items.length == 1) {
                            res.append("\t").append(items[0]).append("\n");
                        } else {
                            res.append("\t(").append(items[0]).append(");");
                            for (int u = 1; u < items.length; u++) {
                                res.append(" (").append(items[u]).append(");");
                            }
                            res.append("\n");
                        }
                    }
                    if (rows > 4) {
                        i = itemsListLength + 1;
                    }
                }
            } else {
                for (int i = 0; i < itemsArray.length; i++) {
                    Item item = itemsArray[i];
                    if (item != null) {
                        rows++;
                        res.append("\t").append(item).append("\n");
                    }
                    if (rows > 4) {
                        i = itemsArray.length + 1;
                    }
                }
            }
        }
        return res.toString();
    }

    /**
     * @return number of bug
     */
    public int getNumber() {
        return numberBag;
    }

    /**
     * Search item in bag, using hash
     *
     * @param item searched object
     * @return list with indexes
     */
    public int[] find(Item item) {
        int[] res = new int[100];
        int counter = 0;
        int number = hash.getHash(item);

        switch (probingType) {
            case -1:
                number = hash.getHash(item) % sizeHashtable;
                if (itemsList2[number] != null) {
                    for (int i = 0; i < itemsList2[number].length; i++) {
                        if (itemsList2[number][i] != null) {
                            if (itemsList2[number][i].equalsName(item)) {
                                res[counter] = number;
                                counter++;
                                if (counter == res.length) {
                                    int[] strNew = new int[(int) (res.length * 1.5)];
                                    System.arraycopy(res, 0, strNew, 0, res.length);
                                    res = strNew;
                                }

                            }
                        }
                    }
                }
                break;
            case 0://linear probing
                number = number % sizeHashtable;
                 int n2=number;
                boolean run = true;
                boolean k = true;
                while (run) {
                    if (itemsArray[number] != null) {
                        if (itemsArray[number].equalsName(item)) {
                            res[counter] = number;
                            counter++;
                            if (counter == res.length) {
                                int[] strNew = new int[(int) (res.length * 1.5)];
                                System.arraycopy(res, 0, strNew, 0, res.length);
                                res = strNew;
                                run = false;
                            }
                        } else {
                            number++;
                            if (number == sizeHashtable) {
                                number = 0;
                                k = false;
                            }
                            if (n2==number&&!k){
                                run=false;
                            }
                        }
                    } else {
                        number++;
                        if (number == sizeHashtable) {
                            number = 0;
                            k = false;
                        }
                        if (n2==number&&!k){
                            run=false;
                        }
                    }
                }
                break;
            case 1://double hashing
                int hash1 = number;
                int hash2 = 1 + ((hash1 / 19) % 109);
                number = number % sizeHashtable;
                while (itemsArray[number] != null) {
                
                    if (itemsArray[number].equalsName(item)) {
                        res[counter] = number;
                        counter++;
                        try {
                            if (counter == res.length) {
                                int[] strNew = new int[(int) (counter * 1.5)];
                                System.arraycopy(res, 0, strNew, 0, res.length);
                                res = strNew;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    number = number + hash2;
                    number = number % sizeHashtable;
                }
                break;
            default://pseudo random probing
                number = number % sizeHashtable;
                int cc = 0;
                while (itemsArray[number] != null) {
                    if (itemsArray[number].equalsName(item)) {
                        res[counter] = number;
                        counter++;
                        if (counter == res.length) {
                            int[] strNew = new int[(int) (res.length * 1.5)];
                            System.arraycopy(res, 0, strNew, 0, res.length);
                            res = strNew;
                        }
                    }
                    number = number + randomShuffle[cc];
                    cc++;
                    number = number % sizeHashtable;
                }
                break;
        }

        res[counter] = number;
        int[] strNew = new int[counter];
        System.arraycopy(res, 0, strNew, 0, counter);
        return strNew;

    }

    /**
     * Return strenghts of selected items
     *
     * @param res  list with indexes
     * @param item for comparison
     * @return list of strenghts
     */
    public int[] getStrengths(int[] res, Item item) {
        int[] str = new int[100];
        int counter = 0;
        if (probingType == -1) {
            try {
                for (int ii : res) {
                    for (Item ls : itemsList2[ii]) {
                        if (item != null && ls != null && item.equalsName(ls)) {
                            str[counter] = (ls.getCurrentStrenght());
                            counter++;
                            if (counter == str.length) {
                                int[] strNew = new int[(int) (str.length * 1.5)];
                                System.arraycopy(str, 0, strNew, 0, str.length);
                                str = strNew;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            for (Integer ii : res) {
                if (item.equalsName(itemsArray[ii])) {
                    str[counter] = itemsArray[ii].getCurrentStrenght();
                    counter++;
                    if (counter == str.length) {
                        int[] strNew = new int[(int) (str.length * 1.5)];
                        System.arraycopy(str, 0, strNew, 0, str.length);
                        str = strNew;
                    }
                }
            }
        }
        int[] strNew = new int[counter];
        System.arraycopy(str, 0, strNew, 0, counter);
        return strNew;
    }
}
