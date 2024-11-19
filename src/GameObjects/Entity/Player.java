package GameObjects.Entity;

import GameObjects.Items.Item;
import GameObjects.NPCs.NPC;

import java.util.ArrayList;
public class Player extends Entity{

    ArrayList<Item> inventory = new ArrayList<>(); // for special items like weapons, map, etc

    Enemy currEnemy = null;


    public Player(int h, int p, int s) {

        super(h, p, s);
    }
    public void addToInv(Item item){
        inventory.add(item);
        item.setTarget(this);
    }
    public void viewInventory(){

        StringBuilder st = new StringBuilder();

        st.append("Player's Inventory\n");

        int count = 1;
        st.append("Item number\tItem Name\n\n");
        for (Item i: inventory){
            st.append(String.format("%s\t%s\n", count, i.getName()));
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

    public int attack(Enemy e){ // returns 1 if innocent
        if (e != null){
            e.hit(this.power);
        }

        return 0;

    }
    public int attack(NPC n){ // NPCs are innocent so returns 1
        if (n != null){
            n.hit();
        }

        return 1;
    }
    public boolean runCheck(){ // to run away from the enemy (possible if player is faster than enemy)
        return currEnemy != null && currEnemy.getSpeed() < this.speed;
    }
}
