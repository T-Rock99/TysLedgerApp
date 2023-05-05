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
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
    public static void main(String[] args) {
        try{
            FileWriter transactionsUpdate = new FileWriter("./src/main/java/com/tc/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(transactionsUpdate);

            try{
            System.out.println("Welcome to Ty's Ledger Application!\n"); // intro
            Thread.sleep(1500);} catch (InterruptedException e){} // Gives user time to read intro screen

            try{
            System.out.println("Initializing...\n");
            Thread.sleep(1500);} catch (InterruptedException e){}

            String input;
            do{
                System.out.println("Please select an option:\n D) Add Deposit\n P) Make Payment\n L) Display Ledger Screen\n X) Exit Ty's Ledger App");
                input = userInput.nextLine();

                switch(input){
                    case "D":
                        transactionsUpdate.write("\n" + addDeposit());
                        System.out.println("\nDeposit has been successfully made!\n");
                        break;
                    case "P":
                        transactionsUpdate.write("\n" + makePayment());
                        System.out.println("\nPayment submitted successfully!\n");
                        break;
                    case "L":
                        LedgerSubMenu();
                        break;
                    case "X":
                        System.out.println("\nThank you for using Ty's Ledger Application! We look forward to servicing you again!"); // Exit message
                        break;
                    default:
                        System.out.println("\nThat's not a valid input -- Make sure your input is capitalized (Ex: \"L\" to display the Ledger Screen.\n");
                }
            } while (!input.equals("X"));
            transactionsUpdate.close();


        } catch (IOException e){
            e.printStackTrace();
            System.out.println("There is a \"catch\" error! This error message is for developer use.");
        }


    }
    public static String addDeposit(){
        System.out.println("\nWhat bank, company, or organization are you making a deposit with?");
        String vendor = userInput.nextLine();
        System.out.println("\nHow much would you like to deposit?");
        float amount = userInput.nextFloat();
        String formattedAmount = String.format("$%,.2f", amount);
        String date = now.format(dateFormat);
        String time = now.format(timeFormat);
        String deposit = date + "|" + time + "|Deposit|" + vendor + "|" + formattedAmount;
        return deposit;
    }
    public static String makePayment(){
        System.out.println("\nWhat bank, company, or organization are you making a payment to?");
        String vendor = userInput.nextLine();
        System.out.println("\nPlease enter the amount of this payment:");
        float amount = userInput.nextFloat();
        String formattedAmount = String.format("-$%,.2f", amount);
        String date = now.format(dateFormat);
        String time = now.format(timeFormat);
        String payment = date + "|" + time + "|Payment|" + vendor + "|" + formattedAmount;
        return payment;
    }
    public static void LedgerSubMenu() throws IOException {
        String input;
        do{
            input = userInput.nextLine();
            System.out.println("\nWelcome to the Ledger Screen. Please select one of the following options:\n A) Display all entries\n D) Display all deposits\n P) Display all payments\n R) Run a pre-defined or custom report\n B) Go back to the home screen");
            switch(input){
                case "A":
                    FileReader transactions1 = new FileReader("./src/main/java/com/tc/transactions.csv");
                    BufferedReader bufferedReader1 = new BufferedReader(transactions1);
                    String allEntries;
                    while((allEntries = bufferedReader1.readLine()) != null){
                    System.out.println("\n" + allEntries);}
                    break;
                case "D":
                    FileReader transactions2 = new FileReader("./src/main/java/com/tc/transactions.csv");
                    BufferedReader bufferedReader2 = new BufferedReader(transactions2);
                    String allDeposits;
                    while((allDeposits = bufferedReader2.readLine()) != null){
                        if(allDeposits.contains("Deposit")){
                            System.out.println("\n" + allDeposits);
                        }
                    }
                    break;
                case "P":
                    FileReader transactions3 = new FileReader("./src/main/java/com/tc/transactions.csv");
                    BufferedReader bufferedReader3 = new BufferedReader(transactions3);
                    String allPayments;
                    while((allPayments = bufferedReader3.readLine()) != null){
                        if(allPayments.contains("Payment")){
                            System.out.println("\n" + allPayments);
                        }
                    }
                    break;
                case "R":
                    reportsSubMenu();
                    break;
                case "B":
                    System.out.println("\nSending you back to the home screen...\n"); // Submenu exit message
                    try{Thread.sleep(750);}catch(InterruptedException e){}
                    break;
                default:
                    System.out.println("\nThat's not a valid input -- Make sure your input is capitalized (Ex: \"P\" to display all payments.\n");
            }
        }while(!input.equals("B"));
    }
    public static void reportsSubMenu() {
        String input;
        do {
            input = userInput.nextLine();
            System.out.println("\nWhat kind of report would you like to run?\n 1) Month to Date\n 2) Previous Month\n 3) Year to Date\n 4) Previous Year\n 5) Search by Vendor\n 0) Back to home page");
            switch (input) {
                case "1":
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/main/java/com/tc/transactions.csv"));
                    int currentYear = now.getYear();
                    String formattedYear = String.format("$04d", currentYear);
                    int currentMonth = now.getMonthValue();
                    String formattedMonth = String.format("$02d", now.getMonthValue());
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "0":
                    System.out.println("\nSending you back to the home screen...\n"); // Submenu exit message
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException e) {
                    }
                    break;
            }
        } while (!input.equals("0"));
    }

}
