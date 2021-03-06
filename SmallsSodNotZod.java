//AUTHOR: Khalid Smalls
//COURSE: CPT167
//PURPOSE: This is an example sales program.
//STARTDATE: 07/25/2019


package edu.cpt167.smalls.participation;

import java.util.Scanner;

public class SmallsSodNotZod 
{
	//declare and initialize class variables
	public static final double DISCOUNT_RATE_MEMBER = .15;
	public static final String DISCOUNT_LABEL_MEMBER = "Member Discount"; 
	public static final String MEMBER_COUNT_LABEL = "Member Count:"; 
	
	public static final double DISCOUNT_RATE_SENIOR = .25; 
	public static final String DISCOUNT_LABEL_SENIOR = "Senior Discount"; 
	public static final String SENIOR_COUNT_LABEL = "Senior Count:"; 
	
	public static final double DISCOUNT_RATE_NONE = 0.0; 
	public static final String DISCOUNT_LABEL_NONE = "No Discount";
	public static final String NO_DISCOUNT_COUNT_LABEL = "No Discount Count:";
	
	public static final double TAX_RATE = .075; 
	
	public static final int RESET_VALUE = 0; 
	
	public static final String A_BUTTON = " [A] "; 
	public static final String B_BUTTON = " [B] "; 
	public static final String C_BUTTON = " [C] "; 
	public static final String Q_BUTTON = " [Q] "; 
	
	public static final String $ = "$"; 
	public static final String PERCENT = "%"; 
	public static final String PER_SQ_FT = "/sq. ft.";
	
	public static final String ITEM_A_LABEL = "RADICAL SOD";
	public static final double ITEM_A_PRICE = 5.95; //per square foot
	public static final String ITEM_A_COUNT_LABEL = "Radical Sod Orders:"; 
	
	public static final String ITEM_B_LABEL = "OK SOD"; 
	public static final double ITEM_B_PRICE = 3.67; //per square foot
	public static final String ITEM_B_COUNT_LABEL = "OK Sod Orders:"; 
	
	public static final String ITEM_C_LABEL = "DECENT SOD";
	public static final double ITEM_C_PRICE = .99; //per square foot
	public static final String ITEM_C_COUNT_LABEL = "Decent Sod Orders:"; 
	
	public static final String QUIT_LABEL = "Quit";
	public static final String USER_NAME_LABEL = "User Name:"; 
	public static final String ITEM_NAME_LABEL = "Item Name:"; 
	public static final String ORIG_PRICE_LABEL = "Original Price:"; 
	public static final String DISCOUNT_PRICE_LABEL = "Discount Price:"; 
	public static final String HOW_MANY_LABEL = "Number of sq ft:"; 
	public static final String SUB_TOTAL_LABEL = "Sub Total:"; 
	public static final String TAX_LABEL = "Tax:"; 
	public static final String TOTAL_COST_LABEL = "Total Cost:"; 
	
	public static final String HOW_MANY_ITEMS_LABEL = "Number of Items:"; 
	public static final String CUSTOMER_TOTAL_LABEL = "Customer Total:"; 
	public static final String GRAND_TOTAL_LABEL = "Grand Total:"; 
	
	
	 
	
	//main method
	public static void main(String[] args) 
	{
		//declare and initialize variables
		Scanner input = new Scanner(System.in); 
		String userName = ""; 
		String itemName = ""; 
		
		double origPrice = 0.0; 
		double discountAmt = 0.0; 
		double discountPrice = 0.0; 
		double discountRate = 0.0; 
		double subTotal = 0.0; 
		double tax = 0.0; 
		double totalCost = 0.0; 
		double customerTotal = 0.0; 
		double grandTotal = 0.0; 
		
		int howManyItems = 0; 
		int howManyEach = 0; 
		int memberCount = 0; 
		int seniorCount = 0; 
		int noDiscountCount = 0; 
		int itemACount = 0; 
		int itemBCount = 0; 
		int itemCCount = 0; 
		int itemCounter = 0; 
		
		char menuSelection = ' '; 
		char itemSelection = ' '; 
		
		displayWelcomeBanner(); 
		
		userName = getUserName(input); 
		
		menuSelection = validateMainMenu(input); 
		while (menuSelection != 'Q') 
		{
			howManyItems = validateHowManyItems(input); 
			
			while (itemCounter < howManyItems) 
			{
				itemSelection = validateItemMenu(input);
				howManyEach = validateHowManyEach(input); 
				
				//processes
				//menu selection decision structure
				if (menuSelection == 'A') 
				{
					discountRate = DISCOUNT_RATE_MEMBER; 
					memberCount++; 
				}
				else if (menuSelection == 'B') 
				{
					discountRate = DISCOUNT_RATE_SENIOR; 
					seniorCount++; 
				}
				else 
				{
					discountRate = DISCOUNT_RATE_NONE; 
					noDiscountCount++; 
				}
				
				//item selection decision structure
				if (itemSelection == 'A') 
				{
					itemName = ITEM_A_LABEL; 
					origPrice = ITEM_A_PRICE; 
					itemACount++; 
				}
				else if (itemSelection == 'B') 
				{
					itemName = ITEM_B_LABEL;
					origPrice = ITEM_B_PRICE; 
					itemBCount++; 
				}
				else 
				{
					itemName = ITEM_C_LABEL;
					origPrice = ITEM_C_PRICE; 
					itemCCount++; 
				}
				
				discountAmt = origPrice * discountRate; 
				discountPrice = origPrice - discountAmt; 
				subTotal = howManyEach * discountPrice; 
				tax = subTotal * TAX_RATE; 
				totalCost = subTotal + tax; 
				customerTotal = customerTotal + totalCost; 
				grandTotal = grandTotal + totalCost; 
				
				//output
				displayItemReceipt(userName, itemName, origPrice, discountPrice, howManyEach, subTotal, tax, totalCost);
				itemCounter++; 
				
			}//end of inner run-while (item selection/ how many each?)
			
			
			if (howManyItems > 0) 
			{
				displayCustomerReport(howManyItems, customerTotal); 
			}
			
			itemCounter = RESET_VALUE; 
			customerTotal = RESET_VALUE; 
			
			//update LCV
			menuSelection = validateMainMenu(input); 
			
		}//end of outer run-while (how many items) 
		
		if (grandTotal > 0.0) 
		{
			System.out.println("\nThank you, " + userName + ". Here is your sales summary.\n");
			displayFinalReport(memberCount, seniorCount, noDiscountCount, itemACount, itemBCount, itemCCount, grandTotal);
		}
		
		displayFarewell(); 

		input.close(); 
	}//end of main
	
	
	
	//methods
	
	//display welcome banner
	public static void displayWelcomeBanner() 
	{
		System.out.printf("\n%s\n", "|*****************************************************|");
		System.out.println("|\t\tWELCOME TO SodNotZod                  |");
		System.out.printf("%s\n", "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
		System.out.println("| This example sales program allows the employee to   |");
		System.out.println("| fullfill multiple orders of sod while keeping track |");
		System.out.println("| of sales and types of sod sold.                     |"); 
		System.out.printf("%s\n", "|*****************************************************|"); 
	}//end of display welcome banner
	
	//get user name
	public static String getUserName(Scanner borrowedInput) 
	{
		String localUserName = ""; 
		System.out.print("Enter your user name: "); 
		localUserName = borrowedInput.next(); 
		return localUserName; 
	}//end of get user name
	
	public static void displayMainMenu() 
	{
		System.out.println("\n|********************************************|");
		System.out.println("|\t\tMAIN MENU                    |");
		System.out.printf("%s\n", "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|"); 
		System.out.printf("%s%s%-30s%.0f%s%3s\n", "|", A_BUTTON, DISCOUNT_LABEL_MEMBER, DISCOUNT_RATE_MEMBER * 100, "% off", "|");
		System.out.printf("%s%s%-30s%.0f%s%3s\n", "|", B_BUTTON, DISCOUNT_LABEL_SENIOR, DISCOUNT_RATE_SENIOR * 100, "% off", "|");
		System.out.printf("%s%s%-30s%10s\n", "|", C_BUTTON, DISCOUNT_LABEL_NONE, "|"); 
		System.out.printf("%s%s%-30s%10s\n", "|", Q_BUTTON, QUIT_LABEL, "|");  
		System.out.printf("%s\n", "|********************************************|"); 
		System.out.print("Select a menu option (or Q to quit): ");
	}//end of display main menu
	
	//validate main menu
	public static char validateMainMenu(Scanner borrowedInput) 
	{
		char localMenuSelection = ' '; 
		displayMainMenu(); 
		localMenuSelection = borrowedInput.next().toUpperCase().charAt(0); 
		while (localMenuSelection != 'A' && localMenuSelection != 'B' && localMenuSelection != 'C' && localMenuSelection != 'Q') 
		{
			System.out.println("\n\t***Invalid selection***");
			displayMainMenu(); 
			localMenuSelection = borrowedInput.next().toUpperCase().charAt(0); 
		}
		return localMenuSelection; 
	}//end of validate main menu
	
	//display farewell
	public static void displayFarewell() 
	{
		System.out.println("\n\n\t\tThank you for using this program.");
		System.out.println("\n\t\tHave a nice day!"); 
	}//end of display farewell
	
	//validate how many items
	public static int validateHowManyItems(Scanner borrowedInput) 
	{
		int localHowMany = 0; 
		System.out.print("\nEnter the number of items to be purchased: ");
		localHowMany = borrowedInput.nextInt(); 
		
		while (localHowMany <= 0) 
		{
			System.out.println("\n\t\t***Invalid entry***");
			System.out.print("Enter the number of items to be purchased: ");
			localHowMany = borrowedInput.nextInt(); 
		}
		return localHowMany; 
	}//end of validate how many items
	
	//display item menu
	public static void displayItemMenu() 
	{
		System.out.printf("\n%s\n", "|****************************************************|");
		System.out.println("|\t\tSOD MENU                             |"); 
		System.out.printf("%s\n", "|****************************************************|"); 
		System.out.printf("%s%s%-24s%s%8.2f%s%7s\n", "|", A_BUTTON, ITEM_A_LABEL, $, ITEM_A_PRICE, PER_SQ_FT, "|");
		System.out.printf("%s%s%-24s%s%8.2f%s%7s\n", "|", B_BUTTON, ITEM_B_LABEL, $, ITEM_B_PRICE, PER_SQ_FT, "|");
		System.out.printf("%s%s%-24s%s%8.2f%s%7s\n", "|", C_BUTTON, ITEM_C_LABEL, $, ITEM_C_PRICE, PER_SQ_FT, "|"); 
		System.out.printf("%s\n", "|****************************************************|"); 
		System.out.print("Select a menu option: ");
	}//end of display item menu
	
	public static char validateItemMenu(Scanner borrowedInput) 
	{
		char localItemSelection = ' '; 
		displayItemMenu();
		localItemSelection = borrowedInput.next().toUpperCase().charAt(0); 
		
		while (localItemSelection != 'A' && localItemSelection != 'B' && localItemSelection != 'C') 
		{
			System.out.print("\n\t\t***Invalid Selection***\n");
			displayItemMenu(); 
			localItemSelection = borrowedInput.next().toUpperCase().charAt(0); 
		}
		return localItemSelection; 
	}//end of validate item menu
	
	// validate how many each
	public static int validateHowManyEach(Scanner borrowedInput) 
	{
		int localHowManyEach = 0; 
		System.out.print("\nEnter number of square feet to be purchased: "); 
		localHowManyEach = borrowedInput.nextInt(); 
		
		while (localHowManyEach <= 0) 
		{
			System.out.println("\nPlease enter a value of 1 or more."); 
			localHowManyEach = borrowedInput.nextInt(); 
		}
		return localHowManyEach; 
	}//end of validate how many each
	
	//display item receipt
	public static void displayItemReceipt(String borrowedUserName, String borrowedItemName, double borrowedOrigPrice,
			double borrowedDiscountPrice,int howManyEach, double borrowedSubTotal, double borrowedTax, double borrowedTotalCost) 
	{
		System.out.printf("\n%s", "|****************************************************|");
		System.out.println("\n|\t\tITEM RECIEPT                         |");
		System.out.printf("%s\n", "|****************************************************|"); 
		System.out.printf("%-2s%-20s%20s%10s\n", "|", USER_NAME_LABEL, borrowedUserName, "|"); 
		System.out.printf("%-2s%-20s%20s%10s\n", "|", ITEM_NAME_LABEL, borrowedItemName, "|"); 
		System.out.println("|----------------------------------------------------|");
		System.out.printf("%-2s%-20s%9s%8.2f%s%7s\n", "|",  ORIG_PRICE_LABEL, $, borrowedOrigPrice, PER_SQ_FT, "|");
		System.out.printf("%-2s%-20s%9s%8.2f%s%7s\n", "|", DISCOUNT_PRICE_LABEL, $, borrowedDiscountPrice, PER_SQ_FT, "|");
		System.out.printf("%-2s%-20s%14d%18s\n", "|", HOW_MANY_LABEL, howManyEach, "|");
		System.out.println("|----------------------------------------------------|");
		System.out.printf("%-2s%-20s%9s%8.2f%15s\n", "|", SUB_TOTAL_LABEL, $, borrowedSubTotal, "|"); 
		System.out.printf("%-2s%-20s%9s%8.2f%15s\n", "|", TAX_LABEL, $, borrowedTax, "|");
		System.out.printf("%-2s%-20s%9s%8.2f%15s\n", "|", TOTAL_COST_LABEL, $, borrowedTotalCost, "|");
		System.out.printf("%s\n", "|****************************************************|");    
		
	}//end of display item receipt
	
	//display customer report
	public static void displayCustomerReport(int borrowedHowManyItems, double borrowedCustomerTotal) 
	{
		System.out.printf("\n%s\n", "|****************************************************|");
		System.out.println("|\t\tCUSTOMER REPORT                      |"); 
		System.out.printf("%s\n", "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|"); 
		System.out.printf("%-2s%-35s%6d%11s\n", "|", HOW_MANY_ITEMS_LABEL, borrowedHowManyItems, "|"); 
		System.out.printf("%-2s%-35s%s%8.2f%8s\n", "|", CUSTOMER_TOTAL_LABEL, $, borrowedCustomerTotal, "|"); 
		System.out.printf("%s%53s", "|", "|");
		System.out.println("\n|\t\tThank you for your purchase.         |");
		System.out.printf("%s%53s", "|", "|"); 
		System.out.println("\n|\t\tHave a nice day!                     |");
		System.out.printf("%s%53s\n", "|", "|"); 
		System.out.printf("%s\n\n", "|****************************************************|"); 
	}//end of display customer report
	
	//display final report
	public static void displayFinalReport(int borrowedMemberCount, int borrowedSeniorCount, int borrowedNoDiscountCount, int borrowedItemACount,
			int borrowedItemBCount, int borrowedItemCCount, double borrowedGrandTotal) 
	{
		System.out.printf("%s\n", "|****************************************************|");
		System.out.println("|\t\tFINAL REPORT                         |");
		System.out.printf("%s\n", "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|"); 
		System.out.printf("%-2s%-35s%6d%11s\n", "|", MEMBER_COUNT_LABEL, borrowedMemberCount, "|"); 
		System.out.printf("%-2s%-35s%6d%11s\n", "|", SENIOR_COUNT_LABEL, borrowedSeniorCount, "|"); 
		System.out.printf("%-2s%-35s%6d%11s\n", "|", NO_DISCOUNT_COUNT_LABEL, borrowedNoDiscountCount, "|"); 
		System.out.printf("%s\n", "|----------------------------------------------------|");
		System.out.printf("%-2s%-35s%6d%11s\n", "|", ITEM_A_COUNT_LABEL, borrowedItemACount, "|");
		System.out.printf("%-2s%-35s%6d%11s\n", "|", ITEM_B_COUNT_LABEL, borrowedItemBCount, "|"); 
		System.out.printf("%-2s%-35s%6d%11s\n", "|", ITEM_C_COUNT_LABEL, borrowedItemCCount, "|"); 
		System.out.printf("%s\n", "|----------------------------------------------------|");
		System.out.printf("%-2s%-35s%s%8.2f%8s\n", "|", GRAND_TOTAL_LABEL, $, borrowedGrandTotal, "|");
		System.out.printf("%s%53s\n",  "|", "|"); 
		System.out.printf("%s\n", "|****************************************************|"); 
	}//end of display final report
	
	
}//end of class
