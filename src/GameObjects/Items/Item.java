package GameObjects.Items;

import GameObjects.Entity.Player;

public abstract class Item {

    String name; // to display in inventory
    int factor; // special effect amount
    Player player; // player that owns the item
    public boolean removable = false; // to check if item is single use

    public String getName(){
        return this.name;
    }

    Item(int f){
        this.factor = f;
    }

    public void setTarget(Player player){
        this.player = player;
    }

    public abstract void use(); // apply effect
}
