/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: this class inherits the abstract class Player
*              and implement the makeMove method, only the 
*              human decide where to move
*/
class HumanPlayer extends Player{
    public HumanPlayer() {
    }

	//inherit the super class constructor to create a new humanplayer
    public HumanPlayer(String userName, String familyName, String givenName) {
        super(userName, familyName, givenName);
    }

	//inherit the super class constructor to restore a player from file
    public HumanPlayer(String userName, String familyName, String givenName, 
	                    int numberOfGamePlayed, int numberOfGameWon, int numberOfGameDrawn) {
        super(userName, familyName, givenName, 
		            numberOfGamePlayed, numberOfGameWon, numberOfGameDrawn);
    }

    @Override
	//get the input from the interface and make a move to grid
    public Move makeMove(char[][] gameBoard){
        int row = TicTacToe.scanner.nextInt();
        int column = TicTacToe.scanner.nextInt();

        return new Move(row, column);
    }
}
