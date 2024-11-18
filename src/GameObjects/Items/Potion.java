package GameObjects.Items;

public class Potion extends Item{

    public Potion(int f){
        super(f);
        this.name = "Potion";
        this.removable = true;
    }
    public void use(){
        if (this.player != null){
            this.player.addHealth(this.factor);
        }
    }

}
