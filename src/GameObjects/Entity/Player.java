package GameObjects.Entity;

import GameObjects.Items.Item;
import GameObjects.NPCs.NPC;

import java.util.ArrayList;
public class Player extends Entity{

    ArrayList<Item> inventory = new ArrayList<>(); // for special items like weapons, map, etc

    public int innocentKills = 0; // tracks the number of innocent people killed : )
    public int enemykills = 0; // tracks the number of enemies killed

    public Player(int h, int p, int s) {

        super(h, p, s);
    }
    public void addToInv(Item item){
        inventory.add(item);
        item.setTarget(this);
    }
    public void viewInventory(){

        StringBuilder st = new StringBuilder();

        st.append("Player's Inventory\n" + this.getStats() + "\n\n");

        int count = 1;
        for (Item i: inventory){
            st.append(String.format("%s\t%s\n", count, i.getName()));
            ++count;
        }
        st.append("\nUse an item using 'use [item number]'\n");

        System.out.println(st);
    }

    public boolean useItem(int itemNo){

        if (itemNo <= inventory.size() && itemNo > 0){

            Item choice = inventory.get(itemNo-1);
            choice.use();

            if (choice.removable){ // delete if one time use
                inventory.remove(itemNo - 1);
            }
            return true;
        }

        return false;
    }

    public void attack(Enemy e){ // returns 1 if innocent
        if (e != null){
            e.hit(this.power);
        }
        System.out.printf("Dealt %s damage to %s", this.power, e.enemyName);
        ++enemykills;

    }
    public void attack(NPC n){ // NPCs are innocent so returns 1
        if (n != null){
            n.hit();
        }

        System.out.printf("Dealt %s damage to %s\n", this.power, n.name);
        ++innocentKills;
    }

    public void talk(Enemy e){ // returns 1 if innocent
        if (e != null){
            e.talk();
        }

    }
    public void talk(NPC n){ // NPCs are innocent so returns 1
        if (n != null){
            n.talk(this, innocentKills);
        }
    }

}
