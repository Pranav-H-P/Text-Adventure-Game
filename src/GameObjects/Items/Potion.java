package GameObjects.Items;

public class Potion extends Item{

    public Potion(int f){
        super(f);
        this.name = "Potion";
        this.removable = true;
    }
    public void use(){
        if (this.player != null){
            System.out.println("Used a Potion!");
            System.out.printf("Healed %d healthPoints", this.factor);
            this.player.addHealth(this.factor);
        }
    }

}
