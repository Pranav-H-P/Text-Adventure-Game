import GameObjects.Entity.Enemy;
import GameObjects.Entity.Player;
import GameObjects.Items.Potion;
import GameObjects.NPCs.NPC;
import GameObjects.Room;

import java.util.Scanner;

public class Main {

    static Scanner inp = new Scanner(System.in);

    static int getInt(String s){ // gets integers with proper input checking

        if (s.matches("-?\\d+")){

            return Integer.parseInt(s);

        }else{
            return -1;
        }
    }
    static int getInt(String s, int min, int max){ // gets integers with proper input checking, and with range

        if (s.matches("-?\\d+")){

            int i = Integer.parseInt(s);

            if (i >= min && i <= max){
                return i;
            }else{
                return -1;
            }

        }else{
            return -1;
        }
    }

    public static void main(String[] args) {



        /*
        * World Map
        *
           ========================================================================================================
                           [Treasure]
                           [Palace]
                           [Bridge]        [Town Square]       [Strange house]
                           [Forest]--------[Crossroads]-------[AbandonedRoad]--------[Passageway]
                     [Wizard's Cave]        [Walkway]                                 [Dungeon]
                                           [Entrance]                                 [Armory]
            ========================================================================================================

        *
        * */

        // map generation
        Room entrance = new Room("Entrance", "Your journey begins");

        Room walkway = new Room("Walkway", "The flowers on the sidewalk fill you with DETERMINATION"); // I hope you get the reference lol

        Room crossRoads = new Room("Crossroads", "Choose your path carefully");

        Room townSquare = new Room("Town Square", "The people seem to be very poor");

        Room strangeHouse = new Room("Strange House", "Something seems wrong here");

        Room abandonedRoad = new Room("Abandoned Road", "The path ahead is filled with potholes");

        Room passageway = new Room("Passageway", "You hear distant cries of pain");

        Room dungeon = new Room("Dungeon", "Seems like the prisoners have escaped");

        Room armory = new Room("Armory", "The wall is lined with weapons");

        Room forest = new Room("Forest", "This doesn't seem like a normal forest");

        Room wizardCave = new Room("Wizard's Cave", "The wall is covered with scrolls");

        Room bridge = new Room("Bridge", "Maybe you should burn it");

        Room palace = new Room("Palace", "Is this where all the money is going?");

        Room treasure = new Room("Treasure room", "So much gold...");

        // linking and adding entities to rooms
        entrance.north = walkway;


        walkway.north = crossRoads;
        walkway.south = entrance;

        walkway.itemList.add(new Potion(30));

        walkway.NPCList.add(new NPC(
           "Gardener",
                "Oh wow, a wanderer",
                "You look like you could take some rest!"
        ));

        crossRoads.north = townSquare;
        crossRoads.south = walkway;
        crossRoads.east = abandonedRoad;
        crossRoads.west = forest;

        crossRoads.NPCList.add(new NPC(
                new Potion(30),
                "Old Man",
                "You have an air of destiny around you",
                "Will you be our saviour? Or our destroyer?",
                "The choice is yours"
        ));
        crossRoads.NPCList.add(new NPC(
                "Kid", "That old bat keeps mumbling something about destiny", "Is it just me or is he crazy",
                "You look kinda scary"
        ));


        forest.south = wizardCave;
        forest.north = bridge;
        forest.east = crossRoads;

        forest.enemyList.add(new Enemy(
                60, 20, 60, "Monstrous Beast", "ROOOOAR"
        ));

        wizardCave.north = forest;

        bridge.south = forest;
        bridge.north = palace;

        palace.north = treasure;
        palace.south = bridge;

        treasure.south = bridge;

        abandonedRoad.west = crossRoads;
        abandonedRoad.east = passageway;
        abandonedRoad.north = strangeHouse;

        townSquare.south = crossRoads;

        strangeHouse.south = abandonedRoad;

        passageway.west = abandonedRoad;
        passageway.south = dungeon;

        dungeon.south = armory;
        dungeon.north = passageway;

        armory.north = dungeon;

        boolean inBattle;

        Room currRoom = entrance;
        Player player = new Player(100, 10, 50);


        System.out.println("""
                Controls
                =============
                
                go <direction>          # to travel between rooms, direction can be north, south, east, west
                inv                     # check your inventory
                use <itemNumber>        # use itemNumber from your inventory
                talk <Entity name>      # talk to entity if present
                attack <Entity name>    # attack a certain entity
                run <direction>         # flee from battle towards a direction
                
                """);

        System.out.print("Enter any input to start the adventure: ");
        inp.nextLine();

        System.out.print("\n\n");
        currRoom.enter();

        while (player.alive && currRoom != treasure){ // gameloop


            for (int i = currRoom.enemyList.size() - 1; i >= 0; i--){
                if (!currRoom.enemyList.get(i).alive){
                    currRoom.enemyList.remove(i);
                }
            }
            for (int i = currRoom.NPCList.size() - 1; i >= 0; i--){
                if (!currRoom.NPCList.get(i).alive){
                    currRoom.NPCList.remove(i);
                }
            }
            for (int i = currRoom.itemList.size() - 1; i >= 0; i--){ // put items in room in inventory
                player.addToInv(currRoom.itemList.get(i));
                System.out.println("You have received: " + currRoom.itemList.get(i).getName());
                currRoom.itemList.remove(i);
            }

            inBattle = !currRoom.enemyList.isEmpty(); // if enemies are present in room, battle time


            if (inBattle){
                System.out.println("You've run into enemies!!!");
                // enemies hit you
                for (Enemy e: currRoom.enemyList){
                    System.out.printf("%s: %s\n", e.enemyName, e.getStats());
                }
                System.out.printf("Your stats: %s\n",player.getStats());

            }

            System.out.print("Enter a command: ");
            String command = inp.nextLine();
            command = command.strip().toLowerCase();

            if (command.equals("inv")){ // inv has no arguments
                player.viewInventory();
            }else{

                String[] commArr = command.split(" ", 2);

                switch (commArr[0]) {
                    case "go" -> {

                        if (inBattle) {
                            System.out.println("Your path is blocked by your enemies, try running away or fighting");
                            continue;
                        }

                        if (currRoom.north != null && commArr[1].equals("north")) {
                            currRoom = currRoom.north;
                            currRoom.enter();
                        } else if (currRoom.south != null && commArr[1].equals("south")) {
                            currRoom = currRoom.south;
                            currRoom.enter();
                        } else if (currRoom.east != null && commArr[1].equals("east")) {
                            currRoom = currRoom.east;
                            currRoom.enter();
                        } else if (currRoom.west != null && commArr[1].equals("west")) {
                            currRoom = currRoom.west;
                            currRoom.enter();
                        } else {
                            System.out.println("Invalid direction!");
                        }
                    }
                    case "use" -> {

                        int choice = getInt(commArr[1]);

                        if (!player.useItem(choice)) {
                            System.out.println("Invalid choice!!");
                        }
                    }
                    case "talk" -> {

                        boolean found = false;

                        for (NPC n: currRoom.NPCList){
                            if (n.name.toLowerCase().equals(commArr[1])){
                                player.talk(n);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            for (Enemy e : currRoom.enemyList) {
                                if (e.enemyName.toLowerCase().equals(commArr[1])) {
                                    player.talk(e);
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (!found){
                            System.out.println("No such entity exists!");
                        }
                    }
                    case "attack" -> {

                        boolean found = false;

                        for (NPC n: currRoom.NPCList){
                            if (n.name.toLowerCase().equals(commArr[1])){
                                player.attack(n);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            for (Enemy e : currRoom.enemyList) {
                                if (e.enemyName.toLowerCase().equals(commArr[1])) {
                                    player.attack(e);
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (!found){
                            System.out.println("No such entity exists!");
                        }

                    }
                    case "run" -> {

                        if (inBattle && (player.getSpeed() < currRoom.enemyList.getFirst().getSpeed())) { // check if enemy is faster than player
                            System.out.println("You're too slow! The enemy isn't letting you escape!");

                        }else{
                            if (currRoom.north != null && commArr[1].equals("north")) {
                                currRoom = currRoom.north;
                                currRoom.enter();
                            } else if (currRoom.south != null && commArr[1].equals("south")) {
                                currRoom = currRoom.south;
                                currRoom.enter();
                            } else if (currRoom.east != null && commArr[1].equals("east")) {
                                currRoom = currRoom.east;
                                currRoom.enter();
                            } else if (currRoom.west != null && commArr[1].equals("west")) {
                                currRoom = currRoom.west;
                                currRoom.enter();
                            } else {
                                System.out.println("Invalid direction!");
                            }
                            continue;
                        }
                    }
                    default -> System.out.println("Unknown command!");
                }

            }
            if (inBattle){
                System.out.println("You've run into enemies!!!");
                // enemies hit you
                for (Enemy e: currRoom.enemyList){
                    e.attack(player);
                    System.out.printf("%s: %s\n", e.enemyName, e.getStats());
                }

                System.out.printf("Your stats: %s\n",player.getStats());

            }
            System.out.println("======================================================================================");
        }

        if (!player.alive){ // loop broke because player died
            if (player.innocentKills >= 2){
                System.out.println("You have been defeated!!!");
            }else{
                System.out.println("Game over! You have died!");
            }
        }
        if (currRoom == treasure){ // loop broke because player reached treasure

            currRoom.entryMessage();
            System.out.println("You found the treasure!");

            if (player.innocentKills >= 2){
                System.out.println("The gold seems to be stained red.");
                System.out.println("Game over, do you feel like you won?");
            }else{
                System.out.println("Game over, You Win!");
            }

        }


    }
}
