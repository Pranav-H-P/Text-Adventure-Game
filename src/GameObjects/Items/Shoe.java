package GameObjects.Items;

public class Shoe extends Item{

    public Shoe(int f, String shoeName){

        super(f);
        this.name = shoeName;
    }
    public void use(){
        if (this.player != null){
            this.player.setSpeed(this.factor);
        }
    }

}
