package model.util;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputValidation {
	
	private static  Scanner input =  new Scanner(System.in);
	
	public static int  validationInt (int opcao) {
		try 
		{
			
			opcao = input.nextInt();
			
		}
		
		catch (InputMismatchException exception) 
		{
			System.err.println("Tipo invalido.");
		}
		
		input.nextLine();
		return opcao;
	}
	public static String  validationString (String text) {
		try 
		{
			
			text = input.nextLine();
		
		}
		
		catch ( NoSuchElementException e) 
		{
			System.err.println("Tipo invalido.");
		}
		
		return text;
	}
	
	public static Scanner getInput() {
		return input;
	}
	public static void closeScanner () {
		input.close();
	}
}
