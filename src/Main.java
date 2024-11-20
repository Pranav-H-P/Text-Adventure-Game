import GameObjects.Entity.Enemy;
import GameObjects.Entity.Player;
import GameObjects.Items.Map;
import GameObjects.Items.Potion;
import GameObjects.Items.Shoe;
import GameObjects.Items.Weapon;
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

        Room passageway = new Room("Passageway", "There are skeletons chained to the walls");

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
                70, 25, 60, "Vicious Beast", "ROOOOAR"
        ));

        wizardCave.north = forest;

        wizardCave.NPCList.add(new NPC(
                new Potion(50, "Super Potion"),
                "Old Wizard", "Ohh well seems like you ran into my pet...Take a sip of this and you'll be back in action",
                "Don't worry about it, it'll regenerate in a night",
                "It keeps most nosy visitors away but you seem to be different",
                "You seem to be full of determination. What are you up to?"
        ));



        bridge.south = forest;
        bridge.north = palace;

        bridge.enemyList.add(
                new Enemy(
                        30, 20, 90,
                        "Bridge troll", "Answer my riddle!!! What happens if I stab you??? You Die!!!!"
                )
        );
        bridge.itemList.add(new Potion(30));

        palace.north = treasure;
        palace.south = bridge;

        palace.enemyList.add(
                new Enemy(120, 30, 60, "Palace Guard", "Halt! You shall not pass!")
        );

        treasure.south = bridge;

        abandonedRoad.west = crossRoads;
        abandonedRoad.east = passageway;
        abandonedRoad.north = strangeHouse;

        abandonedRoad.enemyList.add(new Enemy(
                10, 10, 20, "Spiky Rat", "*Rat noises*"
        ));
        abandonedRoad.enemyList.add(new Enemy(
                20, 5, 20, "Fat Rat", "*More rat noises*"
        ));
        abandonedRoad.enemyList.add(new Enemy(
               10 , 5, 20, "Normal Rat", "*Silent, he knows he is not special*"
        ));

        townSquare.south = crossRoads;

        townSquare.itemList.add(new Potion(30));
        townSquare.itemList.add(new Potion(30));

        townSquare.NPCList.add(new NPC(
                new Shoe(55, "Sandals of Swiftness"),
                "Shoemaker John",
                "Congratulations, you're my 1000th visitor!!! You'll get a prize!",
                "I make the best shoes in the world",
                "This is a great pair, but my magnum opus was stolen by a thief!!!",
                "I wonder where they are now"
        ));
        townSquare.NPCList.add(new NPC(
                new Map(),
                "Cartographer Carol",
                "You seem like you're not from here. Here, this might help",
                "I hope you know how to read",
                "My last customer didn't, and he went missing for a month"
        ));



        strangeHouse.south = abandonedRoad;

        townSquare.itemList.add(new Potion(30));
        strangeHouse.itemList.add(new Shoe(80, "Air Johns"));
        strangeHouse.enemyList.add(new Enemy(
                70, 5, 30, "Mutated blob", "I love shoes, but they dont fit me : ("
        ));

        passageway.west = abandonedRoad;
        passageway.south = dungeon;

        passageway.itemList.add(new Weapon(20, "Battered Spear"));
        passageway.itemList.add(new Potion(30));
        passageway.itemList.add(new Potion(30));

        dungeon.south = armory;
        dungeon.north = passageway;

        dungeon.enemyList.add(new Enemy(
                20, 10, 50, "The Kidney Stealer", "I'm gonna sell your organs!"
        ));
        dungeon.enemyList.add(new Enemy(
                10, 1, 60, "Tax Evader", "I shouldn't be here with these crazies!"
        ));
        dungeon.enemyList.add(new Enemy(
               30 , 10, 60, "Drunken Driver", "*Free Bird solo plays in the background*"
        ));

        dungeon.NPCList.add(new NPC(
                new Potion(50, "Super Potion"), "Hostage", "Here, this might help you","I was kidnapped by that psycho",
                "I used to be the janitor here", "Thank you for saving me",
                "Here's some advice, there a lot of weapons in the armory, but its guarded by a maniac",
                "He makes these guys look soft, Don't go in there unless you absolutely have to"
        ));

        armory.north = dungeon;

        armory.itemList.add(new Potion(50, "Super Potion"));
        armory.itemList.add(new Weapon(40, "General's Axe"));
        armory.enemyList.add(new Enemy(
                100, 40, 80, "Shoplifter", "I can lift shops, are you a shop?"
        ));

        boolean inBattle;

        Room currRoom = entrance;
        Player player = new Player(100, 10, 40);


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

                        if (inBattle){
                            if (player.getSpeed() < currRoom.enemyList.getFirst().getSpeed()){ // check if enemy is faster than player
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
                                System.out.println("======================================================================================");
                                continue;

                            }

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

                        }
                    }
                    default -> System.out.println("Unknown command!");
                }

            }
            System.out.println();
            if (inBattle){
                // enemies hit you
                for (Enemy e: currRoom.enemyList){
                    if (e.alive){
                        e.attack(player);
                    }
                }

            }
            System.out.println("======================================================================================");
        }

        if (!player.alive){ // loop broke because player died
            if (player.innocentKills >= 2){
                System.out.println("You have been defeated!!!");
            }else{
                System.out.println("Game over! You have died!");
                System.out.println("Maybe try exploring the world and finding better items?");
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
