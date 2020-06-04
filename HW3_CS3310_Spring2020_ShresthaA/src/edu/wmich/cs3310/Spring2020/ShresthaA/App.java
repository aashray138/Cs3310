package edu.wmich.cs3310.Spring2020.ShresthaA;

import java.io.*;
import java.util.Random;

public class App {

    Bag[] bags;
    int n;
    boolean fullOutput;
    String outputS;

    /**
     * Constructor
     *
     * e@param itemsArray for filling intуrnal stores
     * @param n          number of bugs
     */
    public App(Item[] itemsArray, int n) {
        this.n = n;
        //    String   output = "";
        StringBuilder out = new StringBuilder();
        bags = new Bag[n * 12];
        int count = 0;
        InitInterface[] initTypes = new InitInterface[]{
                (bag, items) -> {
                    //open hashing
                    int k;
                    Random rd = new Random();
                    bag.itemsList2 = new Item[Bag.sizeHashtable][];
                    for (int i = 0; i < Bag.addingSize; i++) {
                        Item currentItem = items[rd.nextInt(items.length)];
                        k = currentItem.getMaxStrenght() - currentItem.getMinStrenght();
                        int y = rd.nextInt(k);
                        currentItem.setCurrentStrenght(1 + currentItem.getMinStrenght() + y);
                        int number = bag.hash.getHash(currentItem) % Bag.sizeHashtable;
                        if (bag.itemsList2[number] == null) {
                            bag.itemsList2[number] = new Item[50];
                        }
                        for (int f = 0; f < bag.itemsList2[number].length; f++) {
                            if (bag.itemsList2[number][f] == null) {
                                bag.itemsList2[number][f] = currentItem;
                                f = bag.itemsList2[number].length + 1;
                            }
                        }

                    }
                },
                (bag, items) -> {
                    //linear probing
                    int k;
                    Random rd = new Random();
                    bag.itemsArray = new Item[Bag.sizeHashtable];
                    for (int i = 0; i < Bag.addingSize; i++) {
                        Item currentItem = items[rd.nextInt(items.length)];
                        k = currentItem.getMaxStrenght() - currentItem.getMinStrenght();
                        currentItem.setCurrentStrenght(1 + currentItem.getMinStrenght() + rd.nextInt(k));
                        int number = bag.hash.getHash(currentItem);
                        number = number % Bag.sizeHashtable;
                        while (bag.itemsArray[number] != null) {
                            number++;
                            if (number == Bag.sizeHashtable) {
                                number = 0;
                            }
                        }
                        bag.itemsArray[number] = currentItem;
                    }

                },
                (bag, items) -> {
                    //double hashing
                    int k;
                    Random rd = new Random();
                    bag.itemsArray = new Item[Bag.sizeHashtable];
                    for (int i = 0; i < Bag.addingSize; i++) {
                        Item currentItem = items[rd.nextInt(items.length)];
                        k = currentItem.getMaxStrenght() - currentItem.getMinStrenght();
                        currentItem.setCurrentStrenght(1 + currentItem.getMinStrenght() + rd.nextInt(k));
                        int number = bag.hash.getHash(currentItem);
                        int hash1 = number;
                        int hash2 = 1 + ((hash1 / 19) % 198);
                        number = number % Bag.sizeHashtable;
                        while (bag.itemsArray[number] != null) {
                            number = number + hash2;
                            number = number % Bag.sizeHashtable;
                        }
                        bag.itemsArray[number] = currentItem;
                    }
                },
                (bag, items) -> {
                    //pseudo random probing
                    int k;
                    Random rd = new Random();
                    bag.itemsArray = new Item[Bag.sizeHashtable];
                    bag.randomShuffle = new int[Bag.sizeHashtable];
                    for (int i = 0; i < Bag.sizeHashtable; i++) {
                        bag.randomShuffle[i] = i;
                    }
                    int temp;
                    int randomIndexToSwap;
                    for (int i = 0; i < bag.randomShuffle.length; i++) {
                        randomIndexToSwap = rd.nextInt(bag.randomShuffle.length);
                        temp = bag.randomShuffle[randomIndexToSwap];
                        bag.randomShuffle[randomIndexToSwap] = bag.randomShuffle[i];
                        bag.randomShuffle[i] = temp;
                    }
                    for (int i = 0; i < Bag.addingSize; i++) {
                        Item currentItem = items[rd.nextInt(items.length)];
                        k = currentItem.getMaxStrenght() - currentItem.getMinStrenght();
                        currentItem.setCurrentStrenght(1 + currentItem.getMinStrenght() + rd.nextInt(k));
                        int number = bag.hash.getHash(currentItem);
                        number = number % Bag.sizeHashtable;
                        int cc = 0;
                        while (bag.itemsArray[number] != null) {
                            number = number + bag.randomShuffle[cc];
                            cc++;
                            number = number % Bag.sizeHashtable;
                        }
                        bag.itemsArray[number] = currentItem;
                    }
                }//
        };
        HashInterface[] hashTypes = new HashInterface[]{
                new HashInterface() {
                    @Override
                    public int getHash(Item item) {
                        int hash = 0;
                        String uniqueID = item.getName() + item.getRarity();
                        for (byte v : uniqueID.getBytes()) {
                            hash = 67 * hash + (v & 0xff);
                        }
                        if (hash >= 0) {
                            return hash;
                        } else {
                            return Integer.MAX_VALUE - 1 + hash;
                        }
                    }

                    @Override
                    public int getType() {
                        return 1;
                    }
                },
                new HashInterface() {
                    @Override
                    public int getHash(Item item) {
                        int hash = 0;
                        String uniqueID = item.getName() + item.getRarity();
                        char[] chars = uniqueID.toCharArray();
                        for (int i = 0; i < chars.length; i++) {
                            hash = hash + ((i + 1) * chars[i]);
                        }
                        if (hash >= 0) {
                            return hash;
                        } else {
                            return Integer.MAX_VALUE - 1 + hash;
                        }
                    }

                    @Override
                    public int getType() {
                        return 2;
                    }
                },
                new HashInterface() {
                    @Override
                    public int getHash(Item item) {
                        int hash = 1601;
                        String uniqueID = item.getName() + item.getRarity();
                        for (int i = 0; i < uniqueID.length(); i++) {
                            hash = hash * 67 + uniqueID.charAt(i);
                        }
                        if (hash >= 0) {
                            return hash;
                        } else {
                            return Integer.MAX_VALUE - 1 + hash;
                        }
                    }

                    @Override
                    public int getType() {
                        return 3;
                    }
                },
        };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                bags[count] = new Bag(itemsArray, -1, i + 1, initTypes[0], hashTypes[j]);
                bags[count + 1] = new Bag(itemsArray, 0, i + 1, initTypes[1], hashTypes[j]);
                bags[count + 2] = new Bag(itemsArray, 1, i + 1, initTypes[2], hashTypes[j]);
                bags[count + 3] = new Bag(itemsArray, 2, i + 1, initTypes[3], hashTypes[j]);
                count = count + 4;
            }
        }
        out.append("\nn=").append(n).append("\n");
        if (n == 1) {
            for (Bag bag : bags) {
                out.append(bag.print(true)).append("\n");
            }
        } else {
            if (n <= 10) {
                for (Bag bag : bags) {
                    out.append(bag.print(false)).append("\n").append("...").append("\n");
                }
            }
        }
        fullOutput = n < 11;
        out.append("\n");
        outputS = out.toString();

    }

    /**
     * Entry point
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        try {
            int[] array = new int[]{1, 10, 100, 1000, 10000};
            Item[] itemsArray = readFile();
            for (int value : array) {
                App app = new App(itemsArray, value);
                app.search(itemsArray);

                BufferedWriter writer = new BufferedWriter(new FileWriter("n=" + value + ".txt"));
                writer.write(app.outputS);
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create basic array with items from file
     *
     * @return constructed array
     */
    private static Item[] readFile() {
        Item[] array = new Item[750];
        try (BufferedReader br = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i < array.length) {
                    array[i] = new Item(line);
                    i++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    /**
     * Search random item from array
     *
     * @param itemsArray array with items
     */
    private void search(Item[] itemsArray) {
        StringBuilder output = new StringBuilder();
        Random rd = new Random();
        long[] timeArray = new long[bags.length];
        Item item2 = itemsArray[rd.nextInt(itemsArray.length)];
        for (Bag bag : bags) {
          int[] res = bag.find(item2);
            if (res.length != 0) {
                bag.getStrengths(res, item2);
            }
        }

        for (int i = 0; i < 5; i++) {
            Item item = itemsArray[rd.nextInt(itemsArray.length)];
            if (fullOutput) {
                output.append("Searching for ").append(item.getRarity()).append(" ").append(item.getName()).append("... \nFound in");
            }
            for (int i1 = 0; i1 < bags.length; i1++) {
               Bag bag = bags[i1];
                long time = System.nanoTime();
                int[] res = bag.find(item);

                timeArray[i1] = timeArray[i1] + (System.nanoTime() - time);
                if (fullOutput) {
                    StringBuilder outp = new StringBuilder();
                    if (res.length!= 0) {
                        outp.append(" (bag ").append(bag.getNumber()).append(" slots");
                        for (int g = 0; g < res.length; g++) {
                            if (g == (res.length - 1)) {
                                outp.append(" ").append(res[g]).append(",");
                            } else {
                                outp.append(" ").append(res[g]).append(".");
                            }
                        }
                        outp.append("  Strengths:");
                     int[] resStrength = bag.getStrengths(res, item);
                        for (int g = 0; g < resStrength.length; g++) {
                            if (g == (resStrength.length - 1)) {
                                outp.append(" ").append(resStrength[g]).append(")");
                            } else {
                                outp.append(" ").append(resStrength[g]).append(",");
                            }
                        }
                    }
                    output.append(outp.toString());
                    if (i1 == (bags.length - 1)) {
                        output.append("\n");
                    }
                }
            }
            if (fullOutput) {
                output.append("\n");
            }
        }
        output.append("\nTotal average\n");
        long[] timeArrayHelper = new long[12];
        for (int i = 0; i < timeArray.length; i++) {
            timeArrayHelper[i % 12] = timeArrayHelper[i % 12] + timeArray[i];
        }
        for (int i = 0; i < 12; i++) {
            output.append((timeArrayHelper[i] * 12) / (timeArray.length * 5)).append(" ");
        }
        output.append("\n");
        outputS = outputS + output.toString();
    }
}
