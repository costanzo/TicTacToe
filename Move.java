/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: this class records the Player's single
*              move
*/
class Move {
	//the default row and column of the move
    public static final int DEFAULT_ROW = 0;
    public static final int DEFAULT_COLUMN = 0;

	//the row and column of the move
    private int row;
    private int column;

    public Move(){
        this(DEFAULT_ROW, DEFAULT_COLUMN);
    }

	//construct an instance initializing the row and column
    public Move(int row, int column){
        this.row = row;
        this.column = column;
    }

	//getter of the column
    public int getColumn() {
        return column;
    }

	//getter of the row
    public int getRow() {
        return row;
    }

}
