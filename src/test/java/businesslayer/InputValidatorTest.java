package businesslayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    private InputValidator inputValidator;
    @BeforeEach
    public void setUp() {
        inputValidator = new InputValidator();
    }


    @Test
    void checksIfTimeFormatIsCorrect() {
        String timeFormat1 = "00:00";
        String timeFormat2 = "00:00:00";
        assertTrue(inputValidator.checksIfTimeFormatIsCorrect(timeFormat1));
        assertTrue(inputValidator.checksIfTimeFormatIsCorrect(timeFormat2));
    }
    @Test
    public void testIfTimeFormatValidatorHasWrongCharacters() {
        String timeFormat1 = "00/00";
        String timeFormat2 = "0a:00:00";
        assertFalse(inputValidator.checksIfTimeFormatIsCorrect(timeFormat1));
        assertFalse(inputValidator.checksIfTimeFormatIsCorrect(timeFormat2));
    }
    @Test
    public void testIfTimeFormatValidatorHasMissingInput() {
        String timeFormat1 = "00:0";
        String timeFormat2 = "00:00:0";
        assertFalse(inputValidator.checksIfTimeFormatIsCorrect(timeFormat1));
        assertFalse(inputValidator.checksIfTimeFormatIsCorrect(timeFormat2));
    }

    @Test
    void checksIfDateFormatIsCorrect() {
        String timeFormat1 = "00/00";
        String timeFormat2 = "00/00/00";
        assertTrue(inputValidator.checksIfDateFormatIsCorrect(timeFormat1));
        assertTrue(inputValidator.checksIfDateFormatIsCorrect(timeFormat2));
    }
    @Test
    public void testIfDateFormatValidatorHasWrongCharacter() {
        String timeFormat1 = "00:00";
        String timeFormat2 = "0a/00/00";
        assertFalse(inputValidator.checksIfDateFormatIsCorrect(timeFormat1));
        assertFalse(inputValidator.checksIfDateFormatIsCorrect(timeFormat2));
    }
    @Test
    public void testIfDateFormatValidatorHasMissingInput() {
        String timeFormat1 = "00/0";
        String timeFormat2 = "00/00/0";
        assertFalse(inputValidator.checksIfDateFormatIsCorrect(timeFormat1));
        assertFalse(inputValidator.checksIfDateFormatIsCorrect(timeFormat2));
    }


    @Test
    public void testIfValidationContainsOnlyLettersOrIsEmpty() {
        String letter = "";
        assertTrue(inputValidator.containsOnlyLettersOrIsEmpty(letter));
        letter = "onlyletters";
        assertTrue(inputValidator.containsOnlyLettersOrIsEmpty(letter));
    }
    @Test
    public void testIfInputAddressAllowsWrongInput() {
        String start = "";
        String end = "";
        assertFalse(inputValidator.containsLettersThatAreOptionallyFollowedByNumbers(start, end));
        start = "ö";
        end = "eins";
        assertFalse(inputValidator.containsLettersThatAreOptionallyFollowedByNumbers(start, end));

    }

    @Test
    public void testIfUserInputAddressAllowsCorrectInput() {
        String start = "letters1";
        String end = "letters1";
        assertTrue(inputValidator.containsLettersThatAreOptionallyFollowedByNumbers(start, end));
    }


    @Test
    public void testIfValidationOnlyLettersOrIsEmptyContainsWrongInput() {
        String letter = " ";
        assertFalse(inputValidator.containsOnlyLettersOrIsEmpty(letter));
        letter = "19340andletters";
        assertFalse(inputValidator.containsOnlyLettersOrIsEmpty(letter));
    }
    @Test
    public void testIfValidationContainsNumbersWithDecimalPlacesOrIsEmptyContainsSpace() {
        String number = " ";
        assertFalse(inputValidator.containsOnlyNumbersforRating(number));
    }

    @Test
    public void testIfValidationContainsNumbersWithDecimalPlacesOrIsEmptyContainsLetters() {
        String number = "abcd";
        assertFalse(inputValidator.containsOnlyNumbersforRating(number));
    }

    @Test
    public void testIfValidationContainsNumbersWithDecimalPlacesOrIsEmptyContainsNumberBeforeDecimalPoint() {
        String number = ",032";
        assertFalse(inputValidator.containsOnlyNumbersforRating(number));
    }
    @Test
    public void testIfValidationContainsOnlyNumbersOrIsEmpty() {
        String number = "4";

        assertTrue(inputValidator.containsOnlyNumbersforRating(number));
    }
    @Test
    public void testIfValidationContainsNumbersWithDecimalPlacesOrIsEmpty() {
        String number = "";
        assertTrue(inputValidator.containsNumbersWithDecimalPlacesOrIsEmpty(number));
        number = "0123213";
        assertTrue(inputValidator.containsNumbersWithDecimalPlacesOrIsEmpty(number));
        number = "423432,0234";
        assertTrue(inputValidator.containsNumbersWithDecimalPlacesOrIsEmpty(number));
    }

    @Test
    public void testIfValidationContainsOnlyNumbersOrIsEmptyContainsWrongInput() {
        String number = "notnumbers";
        assertFalse(inputValidator.containsOnlyNumbersforRating(number));
        number = "1andnotnumbers";
        assertFalse(inputValidator.containsOnlyNumbersforRating(number));
    }
}