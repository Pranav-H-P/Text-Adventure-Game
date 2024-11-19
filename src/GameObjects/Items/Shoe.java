package GameObjects.Items;

public class Shoe extends Item{

    public Shoe(int f, String shoeName){

        super(f);
        this.name = shoeName;
    }
    public void use(){
        if (this.player != null){
            System.out.println("Equipped " + this.name);
            System.out.printf("Your speed is now %d\n", this.factor);
            this.player.setSpeed(this.factor);
        }
    }

}
