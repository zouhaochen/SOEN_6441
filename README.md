# Soen 6441 Team_05 Project WarZone
## Build 3:

**Group 5**
Group Member:
- Yongtang Lu
- Jiaming Han
- Dejian Wang
- Zitao Wang
- Haochen Zou

**======================Build 3 ================================**

## Refactoring

**Refactor about strategy pattern**
To make users able to choose various strategies for AI players, we change the createOrder() method in player object to createOrder() method in playerStrategy object. Now the Ai player will based on the strategy that selected by user y to deploy the order.

**Refactor about Adapter pattern**
In the previous version, users can only load or edit the maps with the “domination” type. Now we have changed the previous structure of the map class to apply the adapter pattern. So That the adapter can convert the map in conquest type into the domination type for loading and editing.

**common refactor**
1. We super the parent class Order  and transfer  the value of each methods to the defined variables in AdvanceOder class, instead of  using this() to call another constructor in the same class 

2. We made some rectifications to the main menu of the previous version of mainController class. In single game mode, the user needs to enter the start command to start the game after the play setup phase, and the user will only need to enter command for his/her own turns durning the issue and execute phase.

3. We made some rectifications to the main menu of the previous version of mainController class. In single game mode, the user needs to enter the start command to start the game after the play setup phase, and the user will only need to enter command for his/her own turns durning the issue and execute phase.

4. We adopted a try-catch method to handle the exception, if  the target map files are not found, then we track the map file from the top of stack.


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
 command: **back**

## Build 3 Architecture UML
 ![Build3](classesuml/Build3.png)