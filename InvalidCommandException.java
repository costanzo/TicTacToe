/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: this class is an exception when the command 
*              input is not among the command list
*/
class InvalidCommandException extends Exception{

    //set the default message
    public InvalidCommandException(){
        super("Invalid Command Exception");
    }

	//set a message if needed
    public InvalidCommandException(String command){
		//customize the message as required
        super("'" + command + "' " + "is not a valid command.");
    }

}
