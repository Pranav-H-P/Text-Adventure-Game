package GameObjects.Items;

public class Map extends Item{

    String worldMap = """
            ========================================================================================================
                           [Treasure]
                           [Palace]
                           [Bridge]        [Town Square]       [Strange house]
                           [Forest]--------[Crossroads]-------[AbandonedRoad]--------[Passageway]
                     [Wizard's Cave]        [Walkway]                                 [Dungeon]
                                           [Entrance]                                 [Armory]
            ========================================================================================================
            """;


    public Map(){

        super(0);
        this.name = "Map";
    }
    public void use(){
        if (this.player != null){
            System.out.println(worldMap);
        }
    }

}
