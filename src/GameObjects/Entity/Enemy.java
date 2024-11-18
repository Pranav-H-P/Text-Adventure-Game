package GameObjects.Entity;

public class Enemy extends Entity{

    public Enemy(int h, int p, int s){
        super(h, p, s);
    }

    public void attack(Player e){
        if (e != null){
            e.hit(this.power);
        }

    }
}
