package GameObjects;

import GameObjects.Entity.Enemy;
import GameObjects.Items.Item;
import GameObjects.NPCs.NPC;

import java.util.ArrayList;

public class Room {

    public String roomName;
    public ArrayList<Enemy> enemyList = new ArrayList<>();
    public ArrayList<NPC> NPCList = new ArrayList<>();
    public ArrayList<Item> itemList = new ArrayList<>();
    public String roomMessage;
    public Room north = null;
    public Room east = null;
    public Room west = null;
    public Room south = null;

    public Room(String n, String m){
        roomName = n;
        roomMessage = m;
    }
    public void entryMessage(){
        System.out.println("\nYou are at: " + roomName);
        System.out.println(roomMessage + "\n");
    }
    public void showNearby(){
        System.out.println("Connected areas=>");
        if (north != null){
            System.out.print("North: ");
            System.out.println(north.roomName);
        }
        if (south != null){
            System.out.print("South: ");
            System.out.println(south.roomName);
        }
        if (east != null){
            System.out.print("East: ");
            System.out.println(east.roomName);
        }
        if (west != null){
            System.out.print("west: ");
            System.out.println(west.roomName);
        }
    }
    public void showEntities(){

        if (!NPCList.isEmpty()){ // can be interacted by name
            System.out.print("\nPeaceful Entities: ");

            for (NPC n: NPCList){
                System.out.printf("%s, ", n.name);
            }
        }
        System.out.println();
        if (!enemyList.isEmpty()){
            System.out.print("Enemies: ");
            for (Enemy e: enemyList){
                System.out.printf("%s, ", e.enemyName);
            }

        }
        System.out.println();

    }
    public void enter(){
        entryMessage();
        showNearby();
    }
}
