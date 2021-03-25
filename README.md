# Soen 6441 Team_05 Project WarZone
## Build 2:

**Group 5**
Group Member:
- Yongtang Lu
- Jiaming Han
- Dejian Wang
- Zitao Wang
- Haochen Zou

**======================Build 2 ================================**

**Refactoring**

**Refactoring of Map package**
1. Combining classes that are related to get the file information(the country’s storage location), and get the map information(functions that return a list of countries or continents).  

**Refactoring about State Pattern**
In gernal, we refactored the game progress in the first version according to the statepattern design mode. As the result, the following refactoring was carried out:
1. We removed the enum class _GamePhase.java_.  Now, each game phase will base on the user input turning to the specified game phase in the state pattern，
and remind the user what the current game phase they are in. Also, We changed the if-else statement in the previous version of Demo class _mainloop.java_ to a switch case statement that is more consistent with the state pattern


**Refactoring about MVC pattern**
(只有MC 没V )
1. In order to introduce the observer mode in the current version, we move all relevant game elements and game data from the previous version to a package named model as the program data in the model that responds to requests for data about its state. 
Also, Some classes like (_LogEntryBuffer.java_) in the package model extend Observable.java class that used to allow observers to watch the data change of them. 

2. We refactored the GameEngine.java class in the previous version and move it from gameplay package to the package named controller, now the behavior of this class become the role of controller in Observer pattern and named _GameEngineController.java_


**Refactoring about Command Pattern**
1. After the refactor, we don't need to pass in an object of Type CommandValidator when we create a Player object. Since the Player class should not include a class member of Type CommandValidator. We should decouple the two classes. 



**To run the Warzone version 2.0**

User will first get into option phase as the previous 

 Welcome to Warzone.
user can type the following command in the console.
command: **edit / play / exit  (ignore-case)**


Type **edit** if user  want to get into Map Editor model.

 **if you are in Map Editor model:**
 
 
 

 Type **play** if user  want to get into playing game model.
 
 **if you are in playing game model:**
 1. you will be asked to enter the following **command** to load the game
    enter a LoadMap phase command:
    command:**loadmap**
    Please enter command to load the map for the game:
    command: **Loadmap filename   (example: Loadmap 01.model.map)**
 
  then the represent Graph of the model.map file information will showing up.
 
 2. you will be asked to add or remove player for this game (y/n)
    enter a AddPlayer phase command: 
    command: addplayer
    Please enter "gameplayer -add playername -remove playername" command to create players for the game: 
    command: enter **y** for yes, and **n** for no.
 
 3. if yes, you will be asked to enter the following **command** to add/remove player to this game (player limit: 2 to 5)
    command: **gameplayer -add PlayerName   (example: gameplayer -add Alice)**
 
 4. if no (and the player is not out of bound),  the game will be start. you will be asked to enter the following command to randomly assign countries to all players:
 
    enter a AssignCountry phase command: 
    command: **assign**
    Please enter command to randomly assign countries to all players: 
    command: **assigncountries**
 
 5. then the reinforcement armies and countries ownership will be show up for each player. 
    **Reinforcement phase complete.**
 
 
 6. After that, you will get into Issue order phase, and you will be asked to enter following command to issue order for each player.
    enter a IssueOrder phase command:
    command: **issue**
    
    Player XXX(player name ), do you want to create an order? (y/n)
    command:**y/n**  (if y, continues the following command. if no, skip this round.)
    
    if y, you have the following command to enter: 
    command: **deploy countryID num   (example: deploy china 5)**
             **advance country(from)  country(to) num   (example: advance china korea 5)**
             **bomb countryID   (example: bomb korea)**
             **blockade countryID   (example: blockade china)**
             **airlift countryID(source) countryID(target) num (example: airlift china japan 5)**
             **negotiate playerID (example: negotiate china)**
    (1. Tips:the number of deployed armies cannot over reinforcement armies)
    (2. Tips:the deployed country must have ownership of current player)
    (3. Tips:the Bomb card cannot be use to player’s own territory)
    (4. Tips:the player will randomly get a Function card from (bomb, blockade, airlift, negotiate), and can be use at next round)
    
    once all player have entered no for the current round, the game will continue to next phase, Order execution phase.
     **Issue Order Phase Complete**
 
 7. After each player have already issued their orders, the game will get into  order execution phase. and you will be ask for the following command to execute orders.
     enter a OrderExecution phase command: 
     command: **execute**
     
     once there exit a player who have already conquered all of the countries in the game, he/she will be the winner for the game. Therefor, the game will be end, and back to start menu. if user does not want to play again, just enter command exit to end the program.
    **Execute Order Phase Complete**
 
 

 Type **exit** if user  want to exit the game.
 command: **exit**

## Build 2 Architecture UML
 ![Build2](classesuml/B2_UML.png)