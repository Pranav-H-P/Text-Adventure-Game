package GameObjects.Entity;

import java.util.ArrayList;

public class Entity {

    public boolean alive;
    int health;
    int power;
    int speed;


    Entity(int h, int p, int s){
        this.health = h;
        this.power = p;
        this.speed = s;
        this.alive = true;
    }
    public void setPower(int p){
        this.power = p;
    }

    public int getSpeed(){ // raw integer value of stats stats
        return speed;
    }

    public String getStats(){ // human-readable stats
        return String.format("Health: %s, Speed: %s, Power: %s", health, speed, power);
    }
    public void addHealth(int h){
        this.health = (this.health + h);
        if (this.health > 100){
            this.health = 100;
        }
    }

    public void setSpeed(int s){
        this.speed = s;
    }
    public void hit(int f){
        this.health -= f;
        if (this.health <= 0){
            this.alive = false;
        }
    }

}
