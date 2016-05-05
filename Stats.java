/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
* Description: This class is a helper class, containing the
*              game statistics of the player
*/
class Stats {
    //this constant is for the situation that the player does not play any game
    public static final float ZERO_RATIO = 0;

    //when reset the player result, use this as default number
    public static final int DEFAULT_GAME_NUMBER = 0;

    //the user game statistics variables
    private int numberOfGamePlayed;
    private int numberOfGameWon;
    private int numberOfGameDrawn;

    public Stats() {
        this(DEFAULT_GAME_NUMBER,DEFAULT_GAME_NUMBER,DEFAULT_GAME_NUMBER);
    }

    public Stats(int numberOfGameDrawn, int numberOfGameWon, int numberOfGamePlayed) {
        this.numberOfGameDrawn = numberOfGameDrawn;
        this.numberOfGameWon = numberOfGameWon;
        this.numberOfGamePlayed = numberOfGamePlayed;
    }

    //get the number of game drawn
    public int getNumberOfGameDrawn() {
        return numberOfGameDrawn;
    }

    //get the total number of game played
    public int getNumberOfGamePlayed() {
        return numberOfGamePlayed;
    }

    //get the number of game won
    public int getNumberOfGameWon() {
        return numberOfGameWon;
    }

    //get the winning ratio of the player, if no game played, return zero
    public float getWinningRatio(){
        if(numberOfGamePlayed != DEFAULT_GAME_NUMBER){
            //if at least one game played, return a float number as winning ratio
            return ((float)numberOfGameWon) / numberOfGamePlayed ;
        }
        else{
            //if no game played, return a float zero
            return ZERO_RATIO;
        }
    }

    //get the drawn ratio of the player, if no game played, return zero
    public float getDrawnRatio(){
        if(numberOfGamePlayed != DEFAULT_GAME_NUMBER){
            //if at least one game played, return a float number as drawn ratio
            return ((float)numberOfGameDrawn) / numberOfGamePlayed ;
        }
        else{
            //if no game played, return a float zero
            return ZERO_RATIO;
        }
    }

    //reset this player's game statistics
    public void resetStats(){
        //all the game statistics will be set to the default value
        this.numberOfGamePlayed = DEFAULT_GAME_NUMBER;
        this.numberOfGameDrawn = DEFAULT_GAME_NUMBER;
        this.numberOfGameWon = DEFAULT_GAME_NUMBER;
    }

    //when a player win a game, add a winning record to statistics
    public void addWin(){
        //total game number and winning game number plus one
        this.numberOfGamePlayed++;
        this.numberOfGameWon++;
    }

    //when a player gets a drawn, add a drawn record to statistics
    public void addDraw(){
        //total game number and drawn game number plus one
        this.numberOfGamePlayed++;
        this.numberOfGameDrawn++;
    }

    //when a player loses a game, add a lose record to statistics
    public void addLose(){
        //only the total game number should plus one
        this.numberOfGamePlayed++;
    }
}
