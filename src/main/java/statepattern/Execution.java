//package model.state;
//
//import controller.GameEngineController;
//import model.gameelements.Player;
//
//public class Execution extends MainPlayPhase {
//    /**
//     * initialize the GameData object
//     *
//     * @param p_gameData The current context of game engine object
//     */
//    Execution(GameEngineController p_gameData, MainLoop p_mlObj) {
//        super(p_gameData,p_mlObj);
//    }
//
//    /**
//     * invoke method to assign  reinforcement armies to the players
//     */
//    //GameEngineController.java
//    public  void setReinforcementArmies(){
//        printInvalidCommand(this);
//    }
//
//    /**
//     * invoke method to issue deploy order
//     *
//     * @param p_player         The player who issued the order
//     * @param p_countryID      ID of the country to which to deploy the armies
//     * @param p_numberOfArmies Number of armies to deploy
//     */
//    //DeployOrder.java
//    public void deployOrder(Player p_player, String p_countryID, int p_numberOfArmies){
//        printInvalidCommand(this);
//    }
//
//    /**
//     * invoke method to issue advance order
//     *
//     * @param p_player         The player who issued the order
//     * @param p_countryIDFrom  ID of the country from which to deploy the armies
//     * @param p_countryIDTo    ID of the country to which to deploy the armies
//     * @param p_numberOfArmies Number of armies to advance
//     */
//    public  void advanceOrder(Player p_player, String p_countryIDFrom, String p_countryIDTo, int p_numberOfArmies){
//        printInvalidCommand(this);
//    }
//
//    /**
//     * invoke method to issue bomb order
//     *
//     * @param p_player    The player who issued the order
//     * @param p_countryID ID of the country to bomb
//     */
//    public  void bombOrder(Player p_player, String p_countryID){
//        printInvalidCommand(this);
//    }
//
//    /**
//     * invoke method to issue blockade order
//     *
//     * @param p_player    The player who issued the order
//     * @param p_countryID ID of the country to block
//     */
//    public  void blockadeOrder(Player p_player, String p_countryID){
//        printInvalidCommand(this);
//    }
//
//    /**
//     * This function is used to issue airlift order
//     *
//     * @param p_player         The player who issued the order
//     * @param p_countryIDFrom  ID of the country from which to deploy the armies
//     * @param p_countryIDTo    ID of the country to which to deploy the armies
//     * @param p_numberOfArmies Number of armies to advance
//     */
//    public  void airliftOrder(Player p_player, String p_countryIDFrom, String p_countryIDTo, int p_numberOfArmies){
//        printInvalidCommand(this);
//    }
//
//
//    /**
//     * This function is used to issue negotiate order
//     *
//     * @param p_player   The player who issued the order
//     * @param p_playerID ID of the player to negotiate with
//     */
//    public  void dilpomacyOrder(Player p_player, String p_playerID){
//        printInvalidCommand(this);
//    }
//
//    public void next(){
//        d_mainLoopObject.setPhase(new End(d_gameDataObject,d_mainLoopObject));
//    }
//
//}
