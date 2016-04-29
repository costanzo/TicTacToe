/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
*/
import java.util.Scanner;
import java.util.StringTokenizer;

class TicTacToe{
    public static final String COMMAND_DELIMITER = " ";
    public static final String CONTENT_DELIMITER = ",";

    private Scanner scanner;
    private String commandName ;
    private String commandContent ;
    private PlayerManager playerManger;

    public enum Command{
        EXIT,
        ADDPLAYER,
        REMOVEPLAYER,
        EDITPLAYER,
        RESETSTATS,
        DISPLAYPLAYER,
        RANKINGS,
        PLAYGAME
    }

    public static void main(String[] args){
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();
	}
	
	public TicTacToe(){
		this.scanner = new Scanner(System.in);
        this.playerManger = new PlayerManager();
	}
	
	public void run(){
		SystemInitialisation();

        do {
            ProcessInput();
            chooseAction();
            ShowCommandPrompt();
        }while(true);
    }

    private void SystemInitialisation(){
        System.out.println("Welcome to TIc Tac Toe!");
        ShowCommandPrompt();
    }

    private void ProcessInput(){
        String input = this.scanner.nextLine();

        StringTokenizer stOfInput = new StringTokenizer(input,COMMAND_DELIMITER);

        this.commandName = stOfInput.nextToken();
        if(stOfInput.hasMoreTokens())
            this.commandContent = stOfInput.nextToken();
        else
            this.commandContent = null;
    }

    private void chooseAction(){
        Command command = Command.valueOf(commandName.toUpperCase());
        switch(command){
            case EXIT :
                Exit();
                break;
            case ADDPLAYER :
                addPlayer();
                break;
            case REMOVEPLAYER:
                removePlayer();
                break;
            case EDITPLAYER:
                editPlayer();
                break;
            case RESETSTATS:
                resetStats();
                break;
            case DISPLAYPLAYER:
                displayPlayer();
                break;
            case RANKINGS:
                displayRanking();
                break;
            case PLAYGAME:
                playGame();
                break;
            default:
        }
    }

    private void ShowCommandPrompt(){
        System.out.println();
        System.out.print(">");
    }

    private void Exit(){
        this.scanner.close();
        System.out.println();
        System.exit(0);
    }

    private void addPlayer(){
        String userName, familyName, givenName;
        StringTokenizer st = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
        userName = st.nextToken();
        familyName = st.nextToken();
        givenName = st.nextToken();
        Player newPlayer = new Player(userName, familyName, givenName);
        playerManger.addPlayer(newPlayer);
    }

    private void removePlayer(){
        playerManger.removePlayer(this.commandContent, this.scanner);
    }

    private void editPlayer(){
        String userName, familyName, givenName;
        StringTokenizer st = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
        userName = st.nextToken();
        familyName = st.nextToken();
        givenName = st.nextToken();
        playerManger.editPlayer(userName, familyName, givenName);
    }

    private void resetStats(){
        playerManger.resetStats(this.commandContent, this.scanner);
    }

    private void displayPlayer(){
        playerManger.displayPlayer(this.commandContent);
    }

    private void displayRanking(){
        playerManger.displayRankings();
    }

    private void playGame(){
        StringTokenizer stOfPlayer = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
        String player1UserName = stOfPlayer.nextToken();
        String player2UserName = stOfPlayer.nextToken();
        playerManger.makePlayersPlay(player1UserName, player2UserName, this.scanner);
    }

}