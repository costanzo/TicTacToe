/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
*/
import java.util.Arrays;
import java.util.Scanner;

class PlayerManager{
    public static final int WIN = 1;
    public static final int LOSE = 2;
    public static final int DRAW = 3;
    public static final int NO_SUCH_PLAYER = -1;
    public static final int DEFAULT_PLAYER_CAPACITY = 100;
    public static final int RESET_PLAYER_NUMBER = 0;
    public static final String
            CONFIRM_REMOVE_ALL = "Are you sure you want to remove all players? (y/n)";
    public static final String
            CONFIRM_RESET_ALL = "Are you sure you want to reset all player statistics? (y/n)";
    public static final String
            USERNAME_EXISTS_ERROR = "The username has been used already.";
    public static final String
            PLAYER_NOT_EXISTS_ERROR = "The player does not exist.";
    public static final String GAME_ERROR = "Player does not exist.";
    public static final String CONFIRM_ANSWER = "y";

    private Player[] playerArray;
    private int playerTotalNum;
    private GameManager gameManager;

    public PlayerManager(){
        this(DEFAULT_PLAYER_CAPACITY);
    }

	public PlayerManager(int totalPlayers){
        playerArray = new Player[totalPlayers];
        resetPlayerManager();
    }

    private void resetPlayerManager(){
        playerTotalNum = RESET_PLAYER_NUMBER;
    }

    private int findPlayerNum(String userName){
        String playerUserName;
        if(userName == null)
            return NO_SUCH_PLAYER;
        for(int i = 0; i < playerTotalNum; i++){
            playerUserName = playerArray[i].getUserName();
            if(playerUserName.equals(userName))
                return i;
        }

        return NO_SUCH_PLAYER;
    }

    public void addPlayer(Player player){
        if(player == null)
            return;
        String playerUserName = player.getUserName();
        if(findPlayerNum(playerUserName) != NO_SUCH_PLAYER)
            System.out.println(USERNAME_EXISTS_ERROR);
        else{
            playerArray[playerTotalNum] = player;
            playerTotalNum++;
        }
    }

    public void removePlayer(String userName, Scanner scanner){
        if(userName == null){
            System.out.println(CONFIRM_REMOVE_ALL);
            String answer = scanner.nextLine();
            if(answer.equals(CONFIRM_ANSWER))
                removeAllPlayers();
        }
        else{
            int playerNum = findPlayerNum(userName);
            if(playerNum == NO_SUCH_PLAYER)
                System.out.println(PLAYER_NOT_EXISTS_ERROR);
            else
                removeOnePlayer(playerNum);
        }
    }

    private void removeOnePlayer(int playerNum){
        for(int i = playerNum; i < playerTotalNum - 1; i++)
            playerArray[i] = playerArray[i + 1];
        playerTotalNum --;
    }

    private void removeAllPlayers(){
        resetPlayerManager();
    }

    public void editPlayer(String userName, String familyName, String givenName){
        int playerNo = findPlayerNum(userName);
        if(playerNo == NO_SUCH_PLAYER)
            System.out.println(PLAYER_NOT_EXISTS_ERROR);
        else{
            playerArray[playerNo].setFamilyName(familyName);
            playerArray[playerNo].setGivenName(givenName);
        }
    }

    public void resetStats(String userName, Scanner scanner){
        if(userName == null){
            System.out.println(CONFIRM_RESET_ALL);
            String answer = scanner.nextLine();
            if(answer.equals(CONFIRM_ANSWER))
                resetAllStats();
        }
        else{
            int playerNo = findPlayerNum(userName);
            if( playerNo == NO_SUCH_PLAYER)
                System.out.println(PLAYER_NOT_EXISTS_ERROR);
            else
                resetOneStats(playerNo);
        }
    }

    private void resetAllStats(){
        for( int i = 0; i < playerTotalNum; i++ )
            playerArray[i].ResetStats();
    }

    private void resetOneStats(int playerNum){
        playerArray[playerNum].ResetStats();
    }

    public void displayPlayer(String userName){
        if(userName == null){
            displayAllPlayers();
        }
        else{
            int playerNo = findPlayerNum(userName);
            if(playerNo == NO_SUCH_PLAYER)
                System.out.println(PLAYER_NOT_EXISTS_ERROR);
            else
                displayOnePlayer(playerNo);
        }
    }

    private void displayAllPlayers(){
        String[] userName = new String[playerTotalNum];
        for(int i = 0; i < playerTotalNum; i++)
            userName[i] = playerArray[i].getUserName();
        Arrays.sort(userName);
        for(int i = 0; i< playerTotalNum; i++)
            System.out.println(playerArray[findPlayerNum(userName[i])].toString());
    }

    private void displayOnePlayer(int playerNum){
        System.out.println(playerArray[playerNum].toString());
    }

    public void displayRankings(){
        System.out.println("WIN  | DRAW | GAME | USERNAME");
        Arrays.sort(playerArray, 0, playerTotalNum);
        for(int i = 0; i < playerTotalNum ; i++)
            formatResult(playerArray[i]);
    }

    private void formatResult(Player player){
        System.out.printf("%3d", (int)(player.GetWinningRatio()*100));
        System.out.print("% | ");
        System.out.printf("%3d", (int)(player.GetDrawnRatio()*100));
        System.out.print("% | ");
        System.out.printf("%2d", player.getNumberOfGamePlayed());
        System.out.print("   | ");
        System.out.print(player.getUserName());
        System.out.println();
    }

    private void storeGameResult(String player1UserName, String player2UserName, int resultStats){
        int player1Num = findPlayerNum(player1UserName);
        int player2Num = findPlayerNum(player2UserName);
        switch (resultStats){
            case WIN:
                playerArray[player1Num].addWin();
                playerArray[player2Num].addLose();
                break;
            case LOSE:
                playerArray[player1Num].addLose();
                playerArray[player2Num].addWin();
                break;
            case DRAW:
                playerArray[player1Num].addDraw();
                playerArray[player2Num].addDraw();
                break;
            default:
        }
    }

    public void makePlayersPlay(String player1UserName, String player2UserName, Scanner scanner){
        int player1Num = findPlayerNum(player1UserName);
        int player2Num = findPlayerNum(player2UserName);
        if( (player1Num == NO_SUCH_PLAYER)
                || (player2Num == NO_SUCH_PLAYER) ){
            System.out.println(GAME_ERROR);
            return ;
        }
        int result ;
        String player1GivenName = playerArray[player1Num].getGivenName();
        String player2GivenName = playerArray[player2Num].getGivenName();
        this.gameManager = new GameManager(player1GivenName, player2GivenName);
        result = this.gameManager.run(scanner);
        storeGameResult(player1UserName, player2UserName, result);
    }

}