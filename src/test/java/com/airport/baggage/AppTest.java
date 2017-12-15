package com.airport.baggage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	public void testMainBag1() throws GraphMapException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Main.main(new String [] {"src/main/resources/test.txt"});
        String output=baos.toString();
        System.out.println(output);        
        assertTrue("0001 Concourse_A_Ticketing A5 A1 : 11".trim().equals(output.trim()));
        
    }
	
	public void testMainBag2() throws GraphMapException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Main.main(new String [] {"src/main/resources/test2.txt"});
        String output=baos.toString();
        System.out.println(output);        
        assertTrue("0002 A5 A1 A2 A3 A4 : 9".trim().equals(output.trim()));
        
    }
	
	public void testMainBag3() throws GraphMapException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Main.main(new String [] {"src/main/resources/test3.txt"});
        assertTrue("0003 A2 A1 : 1".trim().equals(baos.toString().trim()));
        
    }
	
	public void testMainBag4() throws GraphMapException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Main.main(new String [] {"src/main/resources/test4.txt"});
        assertTrue("0004 A8 A9 A10 A5 : 6".trim().equals(baos.toString().trim()));
        
    }
	
	public void testMainBag5() throws GraphMapException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Main.main(new String [] {"src/main/resources/test5.txt"});
        assertTrue("0005 A7 A8 A9 A10 A5 BaggageClaim : 12".trim().equals(baos.toString().trim()));
        
    }
}
