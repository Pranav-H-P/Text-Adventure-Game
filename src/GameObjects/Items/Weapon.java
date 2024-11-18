package GameObjects.Items;

public class Weapon extends Item{

    public Weapon(int f, String weaponName){
        super(f);
        this.name = weaponName;
    }
    public void use(){
        if (this.player != null){
            this.player.setPower(this.factor);
        }
    }

}
