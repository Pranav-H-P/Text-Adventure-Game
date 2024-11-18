package GameObjects.Items;

public class Weapon extends Item{

    Weapon(int f){
        super(f);
    }
    void use(){
        if (this.player != null){
            this.player.setPower(this.factor);
        }
    }

}
