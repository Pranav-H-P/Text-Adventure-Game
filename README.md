# Guvi Assignment week 11 
A simple single player text based game using Java

### you are an adventurer from a far away land who stumbled onto a kingdom that needs to be freed from tyranny. Will you use the royal coffers for your own gain? Or give it to the people?

- Completely text based
- Has many different locations that you must travel through, each with different names, descriptions, NPCs, Enemies, Items 
- Has 3 endings, depending on your play-style.
- Has a world with many NPCs, who you can talk to ( they have multiple lines usually ), and some of them give you items.
- Even has very loose story going on in the background
- NPCs change how they interact with you based on your play-style
- Has many different items with different properties that might aid you in your journey.
- Has an inventory where you can view and equip/use various items you've collected throughout your journey
- Turn based combat system
- Has enemies that have a little bit of personality
- You can either fight or run if you're fast enough



## Game Controls

- go <direction>          # to travel between rooms, direction can be north, south, east, west
- inv                     # check your inventory
- use <itemNumber>        # use itemNumber from your inventory
- talk <Entity name>      # talk to entity if present
- attack <Entity name>    # attack a certain entity
- run <direction>         # flee from battle towards a direction


### Note

If the game is too challenging, or you want to make it easier,
go to line 242 in Main.java
```Java
Player player = new Player(100, 10, 40); // player initializing player and stats
```
modify the values to suit your needs (health, power, speed)

Note: it is possible to win the game with the default settings if you explore the map fully and collect all the items.