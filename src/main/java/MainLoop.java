import java.util.Scanner;

public class MainLoop {
    public static void main(String[] args){

        String phase1 = "Assign Reinforcement phase";
        String phase2 = "Issue Order Phase";
        String phase3 = "Execute Order Phase";

        String message1 = "Assign the correct number of reinforcement armies to your selected continents according to the Warzone rules. ";
        String message2 = "Issue your aray to targrt continents to attack enemy area or support your own  area, the order will list on the right side";
        String message3 = "To confirm your order on right and execute the order of this round.";

        System.out.println("      WELCOME TO WARZONE !     ");
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want load the game from previously saved game (yes/no)?");
        String ans = scan.next();
        if (ans.equals("y") ){
            System.out.println("loading previous game");
        }
        else if(ans.equals("n")){
            System.out.println("starting a new game ");
        }
        else
            System.out.println("invalid enter");

        int player_flag = 1;
        String winner = null;
        int i;
    }
}

