package expression;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import expresso.Main;

public class ConsoleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());

    @Before
    public void setUpStreams() throws IOException {
        String[] args = new String[0];
        Main.main(args);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    
    @Test
    public void testParseAddinMultiply(){
        System.out.print("Some message from the system");
        String data = "3*x";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine() + "ok");
        //assertEquals("Some message from the system", outContent.toString());
    }
}
