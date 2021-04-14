package view;

import controller.TournamentController;

/**
 * To show the Tournament result.
 */
public class TournamentResultsView {

    /**
     * To show the Tournament result.
     * @param p_TournamentController get data from TournamentController
     */
    public static void printTournamentResults(TournamentController p_TournamentController) {

        System.out.println("**************************************************************************");
        System.out.println("**                          Tournament Results                          **");
        System.out.println("**************************************************************************\n");
        //print Tournament information
        System.out.println("M: " + p_TournamentController.getMapFiles().toString());
        System.out.println("P: " + p_TournamentController.getPlayerStrategies().toString());
        System.out.println("G: " + p_TournamentController.getNumberOfGames());
        System.out.println("D: " + p_TournamentController.getMaxTurns() + "\n");
        System.out.format("%15s", "");

        for(int i = 1; i <= p_TournamentController.getNumberOfGames(); i++) {
            System.out.format("%15s", "Game " + i);
        }
        //print Tournament results
        System.out.println();
        //tournament -M 01.map 02.map -P aggressive random cheater -G 4 -D 30
        for(int i = 0; i < (p_TournamentController.getResults().size()/p_TournamentController.getNumberOfGames()); i++) {

            String l_Mapname = p_TournamentController.getMapFiles().get(i).toString();

            System.out.format("%15s", l_Mapname);

            for(int j = 0; j < p_TournamentController.getResults().get(i).length; j++) {
                System.out.format("%15s", p_TournamentController.getResults().get(i)[j]);
            }

            System.out.println();
        }

    }

}
