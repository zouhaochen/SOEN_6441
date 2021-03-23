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

**Refactoring Part**

**Refactoring about State Pattern**
In gernal, we refactored the game progress in the first version according to the statepattern design mode. As the result, the following refactoring was carried out:

1. We separate the game Phase of the previous version to the following part to , and impletement the phases of the application.
  A. MapEdit `preload, postload`  the class is used to impletement all the functions about map edit state. 
  B. GamePlay `PlaySetup, MainPlay`  the class is used to impletement all the functions about game play state. 
  B_1: PlaySetup (extends Gameplay) `Phase: set Players, assign Countries` the class is used to impletement all the functions before start to playing the game.
  B_2: MainPlay (extends Gameplay) `Phase: Reinforcement, IssueOrder, Execute` the class is used to impletement all the functions during the playing time.
  
2. a. Add a new abstract class _Phase.java_, which includes the abstract methods that need to be implemented at each phase, hence it being inherited by `Edit.java` class and `Play.java` class.
   b. Add GameDriver class _GameDriver.java_, which use to demo the program. so  this is a public class in package _model_.
   
   c. Remove the old main Demo class_Mainloop.java_ in previous version, since the functions of previous version it contains have been assigned to different states to be called
   d. Remove the _GamePhase.java_ class in previous version, since the game phase in our current version is being called from _GameData.java_ class
 
3. Now our new main Demo class _Mainloop.java_ , we add a "Phase/command" menu for user, it will introduce the user what commands that need to be entered in each state, and automatically turn to next state according to user`s command.

**Refactoring about Command Pattern**
4. 

5.


**New functions in Order Creation Phase**




## Build 2 Architecture UML
 ![Build2](classesuml/CommandType.png)