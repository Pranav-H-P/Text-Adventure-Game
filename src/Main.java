import GameObjects.Entity.Player;
import GameObjects.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Scanner inp = new Scanner(System.in);

    static int getInt(){ // gets integers with proper input checking
        String s = inp.nextLine();
        if (s.matches("-?\\d+")){

            return Integer.parseInt(s);

        }else{
            return -1;
        }
    }
    static int getInt(int min, int max){ // gets integers with proper input checking, and with range
        String s = inp.nextLine();
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

        Room walkway = new Room("Walkway", "The pretty yellow flowers on the side fill you with DETERMINATION"); // I hope you get the reference lol

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

        // linking rooms
        entrance.north = walkway;

        walkway.north = crossRoads;
        walkway.south = entrance;

        crossRoads.north = townSquare;
        crossRoads.south = walkway;
        crossRoads.east = abandonedRoad;
        crossRoads.west = forest;

        forest.south = wizardCave;
        forest.north = bridge;
        forest.east = crossRoads;

        wizardCave.north = forest;

        bridge.south = forest;
        bridge.north = palace;

        palace.north = treasure;
        palace.south = bridge;

        treasure.south = bridge;

        abandonedRoad.west = crossRoads;
        abandonedRoad.east = passageway;
        abandonedRoad.north = strangeHouse;

        passageway.west = abandonedRoad;
        passageway.south = dungeon;

        dungeon.south = armory;
        dungeon.north = passageway;

        armory.north = dungeon;

        int innocentKills = 0; // tracks the number of innocent people killed : )

        Room currRoom = entrance;
        Player player = new Player(100, 10, 50);


        System.out.println("""
                Controls
                =============
                
                go <direction>          # to travel between rooms, direction can be north, south, east, west
                inv                     # check your inventory
                use <itemNumber>        # use itemNumber from your inventory
                talk <Entity number>    # talk to entity if present
                attack <Entity number>  # attack a certain entity
                run <direction>         # flee from battle towards a direction
                
                """);

        System.out.println("Enter any input to start the adventure:");
        inp.nextLine();


        while (player.alive && currRoom != treasure){

            currRoom.entryMessage();
            currRoom.showNearby();



        }

        if (!player.alive){ // loop broke because player died
            System.out.println("Game over! You have died!");
        }else{ // loop broke because player reached treasure

            currRoom.entryMessage();
            System.out.println("You win!");

        }


    }
}
