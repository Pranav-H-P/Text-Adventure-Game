package GameObjects.NPCs;

import GameObjects.Entity.Player;
import GameObjects.Items.Item;

import java.util.ArrayList;

public class NPC {

    boolean talked = false; // to check if user has interacted atleast once
    Item specialItem = null;
    String name;
    int linePick = 0; // picks what line the NPC says
    int maxLines = 0;
    ArrayList<String> lines;

    NPC(String name, Item item){
        this.specialItem = item;
        this.name = name;
    }

    NPC(String name){
        this.name = name;
    }

    public void setLines(String ... lines){
        for (String s: lines){
            this.lines.add(s);
        }
        maxLines = lines.length;
    }

    public String talk(Player player){
        String response = "";
        if (!talked){
            talked = true;
            if (specialItem != null){
                player.addToInv(specialItem);
                response += String.format("You have received %s from %s!!\n",
                        specialItem.getName(), this.name);
            }

        }
        response += this.lines.get(linePick);
        linePick = (linePick + 1) % maxLines;
        return response;
    }

}
