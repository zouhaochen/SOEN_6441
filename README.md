# Soen 6441 Team_05 Project WarZone
## Build 2:

**Group 5**
Group Member:
- Yongtang Lu
- Jiaming Han
- Dejian Wang
- Zitao Wang
- Haochen Zou

**======================Build 3 ================================**

## Refactoring

**Refactoring of Map package**
1. Combining classes that are related to get the file information(the country’s storage location), and get the map information(functions that return a list of countries or continents).  

**Refactoring about State Pattern**
In gernal, we refactored the game progress in the first version according to the statepattern design mode. As the result, the following refactoring was carried out:
1. We removed the enum class _GamePhase.java_.  Now, each game phase will base on the user input turning to the specified game phase in the state pattern，
and remind the user what the current game phase they are in. Also, We changed the if-else statement in the previous version of Demo class _mainloop.java_ to a switch case statement that is more consistent with the state pattern


**Refactoring about MVC pattern**
(we are not fully implement MVC, View part is not complete)
1. In order to introduce the observer mode in the current version, we move all relevant game elements and game data from the previous version to a package named model as the program data in the model that responds to requests for data about its state. 
Also, Some classes like (_LogEntryBuffer.java_) in the package model extend Observable.java class that used to allow observers to watch the data change of them. 

2. We refactored the GameEngine.java class in the previous version and move it from gameplay package to the package named controller, now the behavior of this class become the role of controller in Observer pattern and named _GameEngineController.java_


**Refactoring about Command Pattern**
1. After the refactor, we don't need to pass in an object of Type CommandValidator when we create a Player object. Since the Player class should not include a class member of Type CommandValidator. We should decouple the two classes. 



## To run the Warzone version 3.0

User will first get into option phase as the previous 

 Welcome to WAR-ZONE game.
user can type the following command in the console.
you will be ask to select single mode or tournament mode.
command: single/tournament.
then you will be ask Do you want to edit map or play game? (Edit/Play/Back)
command: **edit / play / back  (ignore-case)**


**If you are in the single Mode:**
Type **edit** if user  want to get into Map Editor model.

 **if you are in Map Editor model:**
 enter a Edit phase command: 
 command: **editmap**
 
  then you will be asked to enter the following command to edit model.map. 
  command: **1. editmap filename     (example: editmap 01.model.map)**
           **2. savepmap filename    (example: savepmap 01.model.map)**
           
   1. if enter command **editmap filename**: you will be continue to asked the following command to edit the model.map you selected.
      command: **1. editcontinent -add continentID continentValue (example: editcontinent -add Asia 1 -add Europe 2)**
            **2. editcountry -add countryID continentID (example: editcountry -add 1 China 1 -add 2 Korea 1)**
            **3. editneighbor -add countryID neighborcountryID (example: editneighbor -add 1 2)**
             
       **4. editcontinent -remove continentID  (example: editcontinent -remove Asia -remove Egypt)**
       **5. editcountry -remove countryID  (example: editcountry -remove 3 Russia 2 -remove 4 Finland 2)**
       **6. editneighbor -remove countryID neighborcountryID (example: editneighbor -remove 4 3 -remove 3 1)**
 
   2. if enter command **savemap**: your current edit model.map will be saved.


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
    command: **Start**
    then each of player will automatically issue and execute order on each phase, and finally return the winner for the game
 
 **If you are in the tournament Mode:**
 you will be ask to enter the following command, in which:
 M represents map files that will be used on game.
 P represents player`s strategy. (limit 2 to 4).
 G represents for how many game on each map are going to be played.
 D represents the max number of turns for one game.
 
 command:**tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns**
 for example: **tournament -m 02.map 03.map -p aggressive benevolent -g 2 -d 10**
 
 after that the player for each game will be post at the end. 
 
 Type **back** if user want to back to the main menu, and enter twice to end of the game.
 command: **exit**

## Build 3 Architecture UML
 ![Build3](classesuml/Build3.png)