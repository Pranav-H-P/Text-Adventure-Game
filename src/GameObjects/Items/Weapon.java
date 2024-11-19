package GameObjects.Items;

public class Weapon extends Item{

    public Weapon(int f, String weaponName){
        super(f);
        this.name = weaponName;
    }
    public void use(){
        if (this.player != null){
            System.out.println("Equipped " + this.name);
            System.out.printf("Your power is now %d", this.factor);
            this.player.setPower(this.factor);
        }
    }

}
