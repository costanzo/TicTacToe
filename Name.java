/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: This class is a helper class, contains the
*              player family name and given name
*/
class Name {
    //when no user info is input, use these constant as default value
    public static final String DEFAULT_FAMILYNAME = "familyname";
    public static final String DEFAULT_GIVENNAME = "givenname";

    //the family name and given name variable, the basic information
    private String familyName;
    private String givenName;

    public Name(){
        this(DEFAULT_FAMILYNAME,DEFAULT_GIVENNAME);
    }

    //construct a Name instance with provided family name and given name
    public Name(String familyName, String givenName){
        this.familyName = familyName;
        this.givenName = givenName;
    }

    //get the user's given name
    public String getGivenName() {
        return givenName;
    }

    //get the user's family name
    public String getFamilyName() {
        return familyName;
    }

    //set the user's family name
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    //set the user's given name
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

}
