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

}
