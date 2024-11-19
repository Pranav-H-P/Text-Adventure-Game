package GameObjects.Entity;

import GameObjects.Items.Item;

import java.util.ArrayList;
public class Player extends Entity{

    ArrayList<Item> inventory; // for special items like weapons, map, etc

    Enemy currEnemy = null;


    public Player(int h, int p, int s) {

        super(h, p, s);
    }
    public void addToInv(Item item){
        inventory.add(item);
        item.setTarget(this);
    }
    public String viewInventory(){

        StringBuilder st = new StringBuilder();

        st.append("Player's Inventory\n");

        int count = 1;
        st.append("Item number\tItem Name");
        for (Item i: inventory){
            st.append(String.format("%s\t%s\n", count, i.getName()));
        }
        st.append("Use an item using 'use [item number]'\n");

        return st.toString();
    }

    public String useItem(int itemNo){

        if (itemNo <= inventory.size() && itemNo > 0){

            Item choice = inventory.get(itemNo-1);
            choice.use();
            String ret = "used " + choice.getName();

            if (choice.removable){ // delete if one time use
                inventory.remove(itemNo - 1);
            }

            return ret;
        }

        return "No such item!!!";
    }

    public void attack(Enemy e){
        if (e != null){
            e.hit(this.power);
        }

    }
    public boolean runCheck(){ // to run away from the enemy (possible if player is faster than enemy)
        return currEnemy != null && currEnemy.getSpeed() < this.speed;
    }
}
