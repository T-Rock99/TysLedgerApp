package com.tc;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

// Application intent: Track all financial transactions for general variety of purposes.
// Note: All transactions should read from and save to the "transactions.csv" file, in the following format:
// date|time|description|vendor|amount

// Home Screen:
//  - D) Add Deposit - prompt user for information and save it to csv file
//  - P) Make Payment (Debit) - prompt user for debit information, save to csv
//  - L) Ledger - display ledger screen
//      A) All - display all entries
//      D) Deposits - display only deposits
//      P) Payments - display only negative entries (payments)
//      R) Reports - a new screen that allows the user to run pre-defined reports, or run a custom search
//          1) Month To Date
//          2) Previous Month
//          3) Year To Date
//          4) Previous Year
//          5) Search by Vendor - prompt user for vendor name and display all entries for that vendor
//          6) Custom Search - prompt user for the following search values:
//              . Start Date
//              . End Date
//              . Description
//              . Vendor
//              . Amount
//                  = If user enters a value for a field, you should filter on that field
//                  = If user does not enter a value, you should not filter on that field.
//          0) Back - go back to the report page
//      H) Home - go back to the home page
//  - X) Exit - exit the application

public class Main {
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner userInput = new Scanner (System.in);
    public static void main(String[] args) {
        try{
            FileReader transactions = new FileReader("./src/main/java/com/tc/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(transactions);

            FileWriter transactionsUpdate = new FileWriter("./src/main/java/com/tc/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(transactionsUpdate);

            LocalDateTime now = LocalDateTime.now();

            System.out.println("Welcome to Ty's Ledger Application!\n Taking you to the home screen...");

            String input;
            do{
                System.out.println("Please select an option:\n D) Add Deposit\n P) Make Payment\n L) Display Ledger Screen\n X) Exit Ty's Ledger App");

                input = userInput.nextLine();

                switch(input){
                    case "D":
//                        transactionsUpdate.write(addDeposit());
                        addDeposit();
                        break;
                    case "P":
                        break;
                    case "L":
                        break;
                    case "X":
                        break;
                    default:
                        System.out.println("That's not a valid input -- Make sure your input is capitalized (Ex: \"L\" to display Ledger Screen");


                }
            } while (!input.equals("X"));
            transactionsUpdate.close();


        } catch (IOException e){
            e.printStackTrace();
            System.out.println("There is a \"catch\" error! This error message is for developer use.");
        }


    }
    public static String addDeposit(){
        System.out.println("How much would you like to deposit?");
        String deposit = userInput.nextLine();
        return deposit;
    }

}
