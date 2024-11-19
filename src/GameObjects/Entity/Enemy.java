package GameObjects.Entity;

public class Enemy extends Entity{

    String enemyLine;
    public String enemyName;

    public Enemy(int h, int p, int s, String name, String line){
        super(h, p, s);
        enemyLine = line;
        enemyName = name;
    }

    public void talk(){
        System.out.println(enemyName + ": " + enemyLine);
    }

    public void attack(Player e){
        if (e != null){
            e.hit(this.power);
        }
        System.out.printf("%s dealt %s damage to you\n", this.enemyName, this.power);
    }
}
