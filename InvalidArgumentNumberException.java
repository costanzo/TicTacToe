/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: this class is an exception when the command 
*              argument number is less than expected
*/
class InvalidArgumentNumberException extends Exception{

    //set the default message
    public InvalidArgumentNumberException(){
        super("Incorrect number of arguments supplied to command.");
    }

	//set the message if needed
    public InvalidArgumentNumberException(String message){
		//customize the message as indicated
        super(message);
    }
}
