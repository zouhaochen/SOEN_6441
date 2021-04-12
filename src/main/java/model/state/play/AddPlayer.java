package model.state.play;

import controller.MainPlayController;

import java.util.Scanner;

/**
 * The type Add player.
 */
public class AddPlayer extends Startup {
    /**
     * Instantiates a new Add player.
     *
     * @param p_ml the p ml
     */
    public AddPlayer(MainPlayController p_ml) {
        super(p_ml);
    }

    /**
     * Add players to the game
     */
    @Override
    public void setPlayers() {
        Scanner l_scanner = new Scanner(System.in);

        boolean l_isTrue = true;
        while (l_isTrue) {
            System.out.println("do you want to add or remove player (Number of player limit is 2 to 5)? (y/n) ");
            String l_askUser = l_scanner.nextLine().trim();

            if (l_askUser.equalsIgnoreCase("y")) {
                if (d_Ml.d_GameData.getPlayerList().size() < 5) {
                    d_Ml.d_GameEngineController.gamePlayerCommand();
                }
                // since the number of player range is 2 to 5. no more player can be add in.
                else if (d_Ml.d_GameData.getPlayerList().size() >= 5) {
                    System.out.println("number of player out of limit ");
                }
            } else if (l_askUser.equalsIgnoreCase("n")) {
                // since the number of player range is 2 to 5. no more player can be remove in.
                if (d_Ml.d_GameData.getPlayerList().size() < 2) {
                    System.out.println("number of player is not enough, please add more ");
                } else {
                    System.out.println("All player have already set ");
                    l_isTrue = false;
                }
            } else {
                System.out.println("Invalid command, please try again (y/n): ");
            }
        }
        d_Ml.getDLogEntryBuffer().updateFile();

        next();
    }

    /**
     * Goes to the next phase
     */
    @Override
    public void next() {
        d_Ml.setPhase(new AssignCountry(d_Ml));
    }

}
