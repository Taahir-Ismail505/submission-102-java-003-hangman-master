package za.co.wethinkcode.examples.hangman;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// tag::header[]
class HangmanTest {
    // end::header[]
    // tag::simulate[]
    private void simulateGame(String simulatedUserInput, String expectedLastLine) {                     //<1>
        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());     //<2>
        System.setIn(simulatedInputStream);                                                             //<3>

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();                         //<4>
        System.setOut(new PrintStream(outputStreamCaptor));                                             //<5>

        try {                                                                                           //<6>
            Hangman.main(new String[]{});                                                               //<7>
        } catch (IOException e) {                                                                       //<8>
            fail("Not expecting an exception.");                                                        //<9>
        }

        String[] linesOutput = outputStreamCaptor.toString().split("\n");                               //<10>
        String lastLine = linesOutput[linesOutput.length - 1];                                          //<11>
        assertEquals(expectedLastLine, lastLine);                                                       //<12>
    }

    @Test
    public void shouldWinTheGame() {
        String simulatedUserInput = "oneword.txt\nt\ne\ns\n";
        String expectedOutput = "That is correct! You escaped the noose .. this time.";
        simulateGame(simulatedUserInput, expectedOutput);
    }

    // end::simulate[]
    // tag::test-win[]
    @Test
    public void shouldLoseTheGame() {
        String simulatedUserInput = "oneword.txt\na\nb\nc\nd\nx\n";
        String expectedOutput = "Sorry, you are out of guesses. The word was: test";
        simulateGame(simulatedUserInput, expectedOutput);}

        // end::test-win[]
    // tag::test-lose[]

    // end::test-lose[]
// tag::footer[]
}
// end::footer[]
