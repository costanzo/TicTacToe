/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: this class inherits the abstract class Player
*              and implement the makeMove method, it is a smart
*              and can never lose
*/
class AdvancedAIPlayer extends Player {
	//set the scores for win, drawn and lose
    public static final int WIN_SCORE = 3;
    public static final int DRAWN_SCORE = 1;
    public static final int LOSE_SCORE = 0;

	//set the first round number in maxMin algorithm
    public static final int FIRST_ROUND = 0;

	//set the default mark of a cell
    public static final char DEFAULT_MARK = '\u0000';

	//the mark of advanceAI and the opponent
    private char aiMark;
    private char opponentMark;

	//the best move of this round
    private Move bestMove ;

	//default constructor for AdvancedAIPlayer
    public AdvancedAIPlayer(){
    }

    @Override
	//get the best move according to the gameBoard
    public Move makeMove(char[][] gameBoard){
		
		//try find the advanceAI mark from the gameBoard
		findPlayerMark(gameBoard);
		
		//using maxMin algorithm to find the best move
        maxMin(gameBoard, this.aiMark, FIRST_ROUND);
		
        return this.bestMove;
    }

	//this method using maxMin algorithm to find the best move from gameBoard
    private int maxMin(char[][] gameBoard, char playerMark, int roundNum){
		//if it is AI's turn, get the maximum score
        if(playerMark == aiMark){			
            int maxScore = LOSE_SCORE;
			
			//find the best move of this round
            for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
                for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++)
                    if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY){
						//try to test every single empty cell
                        Move move = new Move(i, j);
						
						//get the best score of this move
                        int score = getMoveResult(gameBoard, move, this.aiMark, roundNum);
                        if(score > maxScore){
                            maxScore = score;
                            
							//if it is the first round of MaxMin, 
			                //set this move as the temporary best move
                            if(roundNum == FIRST_ROUND){
                                this.bestMove = move;
                            }
                        }
                    }
			
            return maxScore;
        }
		//if it is opponent's turn, get the minimum score
        else{
            int minScore = WIN_SCORE;
			
			//try to get the worst move for AI of this round
            for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
                for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++)
				    if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY){
						//try to test every single empty cell
                        Move move = new Move(i, j);
						
						//get the worst score of this move
                        int score = getMoveResult(gameBoard, move, this.opponentMark, roundNum);
                        if(score < minScore){
                            minScore = score;
                        }
                    }
				
            return minScore;
        }
    }

	//try to get game result after a move has been made
    private int getMoveResult(char[][] gameBoard, Move move, char playMark, int roundNum){
		//create a new gameBoard first in case of original gameBoard misuse
        char[][] newGameBoard = copyGameBoard(gameBoard);
		
		//make the move in the gameBoard for this round
        newGameBoard[move.getRow()][move.getColumn()] = playMark;
		
		//if AI wins, return win score
        if(win(newGameBoard, aiMark)){
            return WIN_SCORE;
        }
		//if opponent wins, return lose score
        else if(win(newGameBoard, opponentMark)){
            return LOSE_SCORE;
        }
		//if it is a drawn, return drawn score
        else if(drawn(newGameBoard)){
            return DRAWN_SCORE;
        }
		//if game continues, use maxMin algorithm to get 
		//the optimal score from the following rounds
        else{
            if(playMark == this.aiMark){
				//this round is AI, the opponent play the next round
                return maxMin(newGameBoard, this.opponentMark, roundNum + 1);
            }
            else{
				//this round is opponent, AI player play the next round
                return maxMin(newGameBoard, this.aiMark, roundNum + 1);
            }
        }

    }

	//this method find the player's mark from gameBoard
    private void findPlayerMark(char[][] gameBoard){
		//the number of empty cells in gameBoard
		int countEmptyCells = 0;
		
		//traverse the gameBoard to find the nubmer of empty cells
        for(int i = GameManager.ROW_LOWERBOUND; i < GameManager.ROW_UPPERBOUND; i++)
            for (int j = GameManager.COLUMN_LOWERBOUND ;j < GameManager.COLUMN_UPPERBOUND; j++)
                if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY){
					//this cell is empty, count this one
					countEmptyCells ++;
				}
		
		//get the total number of cells
		int totalEmptyCells = GameManager.ROW_UPPERBOUND * GameManager.COLUMN_UPPERBOUND;
		
		//if AI play first, AI is O and opponent is X
		if(countEmptyCells == totalEmptyCells){
			this.aiMark = GameManager.MARK_OF_O;
			this.opponentMark = GameManager.MARK_OF_X;
		}
		//if opponent play first, he is O and AI is X
		else if(countEmptyCells == (totalEmptyCells - 1)){
			this.aiMark = GameManager.MARK_OF_X;
			this.opponentMark = GameManager.MARK_OF_O;
		}
    }
	
	//find out if certain mark is the winner
	private boolean win(char[][] gameBoard, char mark){
		//check every column, row, diagonal and anti-diagonal
		if((gameBoard[0][0] == mark && gameBoard[0][1] == mark && gameBoard[0][2] == mark)||
		    (gameBoard[1][0] == mark && gameBoard[1][1] == mark && gameBoard[1][2] == mark) ||
		    (gameBoard[2][0] == mark && gameBoard[2][1] == mark && gameBoard[2][2] == mark) ||
		    (gameBoard[0][0] == mark && gameBoard[1][0] == mark && gameBoard[2][0] == mark) ||
		    (gameBoard[0][1] == mark && gameBoard[1][1] == mark && gameBoard[2][1] == mark) ||
		    (gameBoard[0][2] == mark && gameBoard[1][2] == mark && gameBoard[2][2] == mark) ||
		    (gameBoard[0][0] == mark && gameBoard[1][1] == mark && gameBoard[2][2] == mark) ||   
		    (gameBoard[0][2] == mark && gameBoard[1][1] == mark && gameBoard[2][0] == mark))
		    return true;
		else{
			return false;
		}
	}

	//try to find out if the game is a drawn
    private boolean drawn(char[][] gameBoard){
		//if any cell of the gameBoard is empty, it is not a drawn
        for(int i = GameManager.ROW_LOWERBOUND; i < GameManager.ROW_UPPERBOUND; i++)
            for (int j = GameManager.COLUMN_LOWERBOUND ;j < GameManager.COLUMN_UPPERBOUND; j++)
                if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY)
                    return false;
				
        return true;
    }

	//copy the gameBoard to a new gameBoard for different assumptions
    private char[][] copyGameBoard(char[][] gameBoard){
        char[][] newGameBoard = new char[GameManager.ROW_UPPERBOUND][GameManager.ROW_UPPERBOUND];
		
		//copy every single cell
        for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
            for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++){
                newGameBoard[i][j] = gameBoard[i][j];
            }
			
        return newGameBoard;
    }

}
