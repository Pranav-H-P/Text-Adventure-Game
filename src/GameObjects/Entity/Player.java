package GameObjects;

import java.util.ArrayList;
public class Player {

    boolean alive;
    int health;
    int power;
    int speed;
    ArrayList<String> specialInv; // for special items like weapons, map, etc
    int potions; // count of potions

    Player(){
        this.health = 100;
        this.power = 10;
        this.speed = 10;
        this.potions = 0;
    }

    public void setPower(int p){
        this.power = p;
    }

    public void addHealth(int h){
        this.health = (this.health + h) % 100;
    }

    public void setSpeed(int s){
        this.speed = s;
    }
    public void hit(int f){
        this.health -= f;
    }

    public void attack(Enemy e){
        if (e != null){
            e.hit(this.power);
        }


    }
}
