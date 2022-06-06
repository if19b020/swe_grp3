package businesslayer;

public class InputValidator {
    public boolean containsLettersThatAreOptionallyFollowedByNumbers(String value1, String value2){
        return value1.matches("[a-zA-Z]+[0-9]*") && value2.matches("[a-zA-Z]+[0-9]*");
    }

    /*public boolean containsOnlyNumbersOrIsEmpty(String value){
        return value.matches("[0-9]*");
    }

    public boolean containsNumbersWithDecimalPlacesOrIsEmpty(String value){
        return value.matches("([0-9]+([.,][0-9]+)?)?");
    }*/
    public boolean containsOnlyNumbersOrIsEmpty(String value)
    {
        return value.matches("[1-5]");
    }
    public boolean containsOnlyLettersOrIsEmpty(String value){
        return value.matches("[a-zA-Z]*");
    }

    public boolean checksIfTimeFormatIsCorrect(String value){
        return value.matches("([0-9]{2}[:][0-9]{2})?([:][0-9]{2})?");
    }

    public boolean checksIfDateFormatIsCorrect(String value){
        return value.matches("([0-9]{2}[/][0-9]{2}([/][0-9]{2})?)?");
    }
}
