package com.tc;
import java.util.*;
import java.util.regex.*;
import java.io.*;

// Application intent: Track all financial transactions for general variety of purposes.
// Note: All transactions should read from and save to the "inventory.csv" file, in the following format:
// date|time|description|vendor|amount (more examples in "invetory.csv" by default.

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
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}