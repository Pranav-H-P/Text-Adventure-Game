package GameObjects.Items;

public class SpeedPotion extends Item{ // increases current speed by 20 until player equips different shoe

    public SpeedPotion(int f){
        super(f);
        this.name = "Speed Potion";
        this.removable = true;
    }

    public void use(){
        if (this.player != null){
            System.out.println("Applied Speed Potion on Current Boots!");
            this.player.setSpeed(this.player.getSpeed() + this.factor);
            System.out.printf("Current Speed is: %s\n", this.player.getSpeed());
        }
    }

}
