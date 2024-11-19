package GameObjects.NPCs;

import GameObjects.Entity.Player;
import GameObjects.Items.Item;

import java.util.ArrayList;

public class NPC {

    public boolean alive = true;
    boolean talked = false; // to check if user has interacted atleast once
    Item specialItem = null;

    public String name;

    int linePick = 0; // picks what line the NPC says
    int maxLines = 0;
    ArrayList<String> lines = new ArrayList<>();
    String violentLine = "Please spare me! Take whatever you want!";

    public NPC(Item item, String name, String... lines){
        this.specialItem = item;
        this.name = name;
        for (String s: lines){
            this.lines.add(s);
        }
        maxLines = lines.length;
    }

    public NPC(String name, String... lines){

        this.name = name;
        for (String s: lines){
            this.lines.add(s);
        }
        maxLines = lines.length;
    }

    public void hit(){
        System.out.println("You monster! Aaaaah!");
        alive = false;
    }

    public void talk(Player player, int kills){ // change responses based on if the player is violent
        String response = "";


        if (kills >= 2){
            response += violentLine;
        }else{
            response += this.lines.get(linePick);
            linePick = (linePick + 1) % maxLines;
        }
        System.out.println(name + ": " + response);

        if (!talked){
            talked = true;
            if (specialItem != null){
                player.addToInv(specialItem);
                System.out.printf("You have received %s from %s!!\n",
                        specialItem.getName(), this.name);
            }

        }
    }

}
