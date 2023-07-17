package com.example.coursework_task_04;

import java.util.Scanner;

public class MainMenu extends FuelQueue
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\t...Welcome to the Fuel Queue Management System - Galle...\n");
        System.out.println("""
                ***************************************************************************************************
                100 or VFQ:    View all Fuel Queues.
                101 or VEQ:    View all Empty Queues.
                102 or ACQ:    Add customer to a Queue.
                103 or RCQ:    Remove a customer from a Queue. (From a specific location)
                104 or PCQ:    Remove a served customer.
                105 or VCS:    View Customers Sorted in alphabetical order (Do not use library sort routine)
                106 or SPD:    Store Program Data into file.
                107 or LPD:    Load Program Data from file.
                108 or STK:    View Remaining Fuel Stock.
                109 or AFS:    Add Fuel Stock.
                110 or IFQ:    View Income of all Fuel Queues.
                111 or VGI:    View the GUI of Fuel Station.
                999 or EXT:    Exit the Program.
                ***************************************************************************************************
                """);

        while (true)
        {
            System.out.print("\nSelect the correct operator from the console menu: ");
            String choice = input.next();

            if (choice.equals("100") || choice.equalsIgnoreCase("VFQ"))
            {
                viewAllQueues();
            }
            else if (choice.equals("101") || choice.equalsIgnoreCase("VEQ"))
            {
                viewEmptyQueues();
            }
            else if (choice.equals("102") || choice.equalsIgnoreCase("ACQ"))
            {
                addCustomer();
            }
            else if (choice.equals("103") || choice.equalsIgnoreCase("RCQ"))
            {
                removeCustomer();
            }
            else if (choice.equals("104") || choice.equalsIgnoreCase("PCQ"))
            {
                removeServedCustomer();
            }
            else if (choice.equals("105") || choice.equalsIgnoreCase("VCS"))
            {
                sortInAlphabeticalOrder();
            }
            else if (choice.equals("106") || choice.equalsIgnoreCase("SPD"))
            {
                storeDataToFile();
            }
            else if (choice.equals("107") || choice.equalsIgnoreCase("LPD"))
            {
                loadDataFromFile();
            }
            else if (choice.equals("108") || choice.equalsIgnoreCase("STK"))
            {
                viewRemainingStock();
            }
            else if (choice.equals("109") || choice.equalsIgnoreCase("AFS"))
            {
                addFuelStock();
            }
            else if (choice.equals("110") || choice.equalsIgnoreCase("IFQ"))
            {
                calculateIncome();
            }
            else if (choice.equals("111") || choice.equalsIgnoreCase("VGI"))
            {
                try { launch(); }
                catch (IllegalStateException illegalStateException){ System.out.println("Can run only one time"); }
            }
            else if (choice.equals("999") || choice.equalsIgnoreCase("EXT"))
            {
                System.out.println("\nThank you!!!\nHave a Nice Day:)");
                break;
            }
            else
            {
                System.out.println("\nInvalid operator...Try again!!!");
            }
        }
    }
}
