/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: this class inherits the abstract class Player
*              and implement the makeMove method, it is a stupid
*              player
*/
class AIPlayer extends Player{
    public AIPlayer() {
    }

	//inherit the super class constructor to create a new aiplayer
    public AIPlayer(String userName, String familyName, String givenName) {
        super(userName, familyName, givenName);
    }

	//inherit the super class constructor to restore a player from file
    public AIPlayer(String userName, String familyName, String givenName, 
	                    int numberOfGamePlayed, int numberOfGameWon, int numberOfGameDrawn) {
        super(userName, familyName, givenName, 
		            numberOfGamePlayed, numberOfGameWon, numberOfGameDrawn);
    }

    @Override
	//get move from the aiplayer 
    public Move makeMove(char[][] gameBoard){
        for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
            for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++){
				//move to the first cell that is empty from scaning gameboard
                if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY){
                    return new Move(i, j);
				}
            }

        return null;
    }
}
