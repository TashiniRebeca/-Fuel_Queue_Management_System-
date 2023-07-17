package com.example.coursework_task_04;

import javafx.application.Application;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FuelQueue extends Application
{
    static FuelQueue[][] fuelQueue = new FuelQueue[5][6]; // Creating the 2D Array of FuelQueue Objects.
    static Scanner input = new Scanner(System.in);
    static int fuelStock = 6600; // Initialize the fuel stock.
    static int priceOfFuelLitre = 430; // Initialize the price of 1 litre of fuel.
    Passenger passenger = new Passenger(); // Creating an object of the Passenger Class.
    static WaitingQueue waitingQueues = new WaitingQueue(); // Creating an object of the WaitingQueue Class to call the 2 methods in it.

    public static void viewAllQueues()
    {
        /*
            Display All the Fuel Queues
         */

        for (int i=0;i<5;i++) // Looping through 5 queues.
        {
            System.out.println("\nFuel Queue " + (i+1) + "=>");
            System.out.printf("%n %2s  %-13s  %-13s  %-13s  %-11s%n"," ","First-Name","Last-Name","Vehicle-No","Fuel-Amount\n"); // Formatted String.
            for (int j=0;j<6;j++)
            {
                if (fuelQueue[i][j] == null) {System.out.println(" - ");} // If the object is null, then printing a dash instead of printing null.
                else
                    System.out.printf("%2d.  %-13s  %-13s  %-13s  %-11d%n",j+1,fuelQueue[i][j].passenger.getFirstName(),fuelQueue[i][j].passenger.getLastName(),fuelQueue[i][j].passenger.getVehicleNumber(),fuelQueue[i][j].passenger.getNoOfLitres());
            }
            System.out.println();
        }
    }

    public static void addCustomer()
    {
        /*
            Adding a New Customer to a particular Queue
         */

        try
        {
            if (fuelStock == 500)
            {
                System.out.println("Alert Message: " + fuelStock +"L of fuel are remaining in the Current Fuel Stock!!!");
            }

            int queueNumber = returningMinimumQueue(); // Getting the queue number as the minimum queue number.
            System.out.print("\nEnter the Customer's First Name: ");
            String firstName = input.next();
            firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase(); // Getting the first letter as Capital and all other letters as Simple.
            System.out.print("Enter the Customer's Last Name: ");
            String lastName = input.next();
            lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
            System.out.print("Enter the Number of the Vehicle: ");
            String vehicleNumber = input.next();
            System.out.print("Enter the No.of Litres: ");
            int noOfLitres = input.nextInt();
            if (queueNumber != 0) // Checking whether queueNumber is invalid or not.
            {
                for (int j =0;j<6;j++)
                {
                    if (fuelQueue[queueNumber - 1][j] == null)
                    {
                        fuelQueue[queueNumber - 1][j] = new FuelQueue();
                        fuelQueue[queueNumber - 1][j].passenger.setFirstName(firstName);
                        fuelQueue[queueNumber - 1][j].passenger.setLastName(lastName);
                        fuelQueue[queueNumber - 1][j].passenger.setVehicleNumber(vehicleNumber);
                        fuelQueue[queueNumber - 1][j].passenger.setNoOfLitres(noOfLitres);
                        fuelStock -= noOfLitres;
                        System.out.println("\n" + firstName + " " + lastName + " added to the queue " + (queueNumber) + " successfully");
                        break;
                    }
                }
            }
            else
            {
                FuelQueue obj = new FuelQueue(); // Creating an object of FuelQueue Class.
                obj.passenger.setFirstName(firstName);
                obj.passenger.setLastName(lastName);
                obj.passenger.setVehicleNumber(vehicleNumber);
                obj.passenger.setNoOfLitres(noOfLitres);
                waitingQueues.addingToWaitingQueue(obj); // Adding the customers to the Waiting Queue.
            }
        }
        catch (InputMismatchException inputMismatchException) // If the user didn't enter an integer, then this will handle that InputMismatchException.
        {
            input.nextLine();
            System.out.println("Invalid Input.Try Again");
        }
    }

    public static void viewEmptyQueues()
    {
        /*
            Display All the Completely Empty, Partially Empty & Completely Full Queues
         */

        for (int i=0;i<5;i++) // Looping through 5 queues.
        {
            for (int j=0;j<6;j++) // Looping through the customers in each queue.
            {
                if (fuelQueue[i][0] == null) // Checking whether the first element is null or not
                {
                    System.out.println("\nQueue "+ (i+1) +" is Completely Empty");
                    break;
                }
                else if (fuelQueue[i][5] != null) // Checking whether the last element is null or not
                {
                    System.out.println("\nQueue "+ (i+1) +" is Completely Full");
                    break;
                }
                else
                {
                    System.out.println("\nQueue "+ (i+1) +" is Partially Empty"); // If the first element is not null and the last element is null then the queue is partially empty.
                    break;
                }
            }
        }
    }

    public static void removeCustomer()
    {
        /*
            Removing a Customer from a specific location in a Queue
         */

        try
        {
            System.out.print("\nEnter the Queue Number of the Customer to be removed: ");
            int queueNo = input.nextInt();
            if (fuelQueue[queueNo - 1][0] != null) // Checking whether there's an element to remove or not.
            {
                System.out.print("Enter the index of the Customer you want to remove: ");
                int index = input.nextInt();
                if (fuelQueue[queueNo - 1][index] != null)
                {
                    fuelStock += fuelQueue[queueNo - 1][index].passenger.getNoOfLitres();
                    fuelQueue[queueNo - 1][index] = null;

                    for (int k=index + 1;k<6;k++)
                    {
                        if (fuelQueue[queueNo - 1][k - 1] == null)
                        {
                            fuelQueue[queueNo - 1][k - 1] = fuelQueue[queueNo - 1][k]; // Swapping the elements to the front one by one.
                            fuelQueue[queueNo - 1][k] = null;
                        }
                    }
                    for (int i=0;i<6;i++) // Looping through the customers in each queue.
                    {
                        if (fuelQueue[queueNo - 1][i] == null) { fuelQueue[queueNo - 1][i] = waitingQueues.removingFromWaitingQueue(); }
                    }
                    System.out.println("The Customer in index " + index + " is removed from Queue " + queueNo + " successfully.");
                }
                else { System.out.println("There's no customer in that index! Try again..."); }
            }
            else { System.out.println("The Queue " + queueNo + " is empty"); }
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
        {
            input.nextLine();
            System.out.println("Array Index is out of range! Try again...");
        }
    }

    public static void removeServedCustomer()
    {
        /*
            Removing a Served Customer from a Queue
         */

        try
        {
            System.out.print("Enter the Queue Number to be served: ");
            int queueNumber = input.nextInt();
            if (fuelQueue[queueNumber - 1][0] != null) // If the first customer is not null then he/she is served.
            {
                System.out.println("The customer in the queue " + queueNumber + " is served successfully");
                fuelQueue[queueNumber - 1][0] = null; // When the first is served, its position is set to be null.
                for (int i = 1; i < 6; i++) // Looping through the customers in each queue except for the first customer who is served.
                {
                    if (fuelQueue[queueNumber - 1][i - 1] == null)
                    {
                        fuelQueue[queueNumber - 1][i - 1] = fuelQueue[queueNumber - 1][i]; // Swapping the other customers to the front.
                        fuelQueue[queueNumber - 1][i] = null;
                    }
                }
            }
            else { System.out.println("The queue is empty"); }
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
        {
            input.nextLine();
            System.out.println("Queue Number is out of range! Try again...");
        }
    }

    public static void sortInAlphabeticalOrder()
    {
        /*
            Sorting The Names of Customers in Alphabetical Order
         */

        FuelQueue[][] sortingArray = new FuelQueue[5][6]; // Creating an array to store the data of sorted customers in each queue.

        for (int i=0;i<5;i++) // Looping through 5 queues.
        {
            for (int j=0;j<6;j++) // Looping through the customers in each queue.
            {
                sortingArray[i][j] = fuelQueue[i][j]; // Copying the elements of fuelQueue to the sortingArray.
                if (sortingArray[i][j] == null) {
                    sortingArray[i][j] = new FuelQueue();
                    sortingArray[i][j].passenger.setFirstName("{}"); } // '{}' has a higher ASCII Value.
            }

            for (int j=0;j<6;j++) // Looping through the customers in each queue in sortingArray.
            {
                for (int k=j+1;k<6;k++)
                {
                    if (sortingArray[i][j].passenger.getFirstName().compareTo(sortingArray[i][k].passenger.getFirstName()) > 0) // Comparing the ASCII Values of two nearby elements in the array by First Name.
                    {
                        FuelQueue tempVar; // Swapping the 2 elements
                        tempVar = sortingArray[i][j];
                        sortingArray[i][j] = sortingArray[i][k];
                        sortingArray[i][k] = tempVar;
                    }
                }
            }
        }

        System.out.println("\nSorted Fuel Queues\n ");
        for (int i=0;i<5;i++) // Looping through 5 queues in sortingArray.
        {
            System.out.println("\nFuel Queue " + (i+1) + "=>");
            System.out.printf("%n %2s  %-13s  %-13s  %-13s  %-11s%n"," ","First-Name","Last-Name","Vehicle-No","Fuel-Amount");
            for (int j=0;j<6;j++) // Looping through the customers in each queue in sortingArray.
            {
                if (sortingArray[i][j].passenger.getFirstName().equals("{}")) {System.out.println(" - ");}
                else // Printing the customer details with  the sorted queues.
                    System.out.printf("%2d.  %-13s  %-13s  %-13s  %-11d%n",j+1,sortingArray[i][j].passenger.getFirstName(),sortingArray[i][j].passenger.getLastName(),sortingArray[i][j].passenger.getVehicleNumber(),sortingArray[i][j].passenger.getNoOfLitres());
            }
            System.out.println();
        }
    }

    public static void storeDataToFile()
    {
        /*
           Storing the data to a file
         */

        try
        {
            FileWriter fuelFile = new FileWriter("Fuel Management System - Galle.txt"); // Creating a text file to store data of the Fuel Station.
            PrintWriter printWriter = new PrintWriter(fuelFile); // Printing the objects to the text file created.

            for (int i=0;i<5;i++) // Looping through 5 queues.
            {
                for (int j=0;j<6;j++) // Looping through the customers in each queue.
                {
                    if (fuelQueue[i][j] == null) { printWriter.println("null");}
                    else { printWriter.println(fuelQueue[i][j].passenger.getFirstName() + " " + fuelQueue[i][j].passenger.getLastName() + " " + fuelQueue[i][j].passenger.getVehicleNumber() + " " + fuelQueue[i][j].passenger.getNoOfLitres());}
                }
            }
            printWriter.println(fuelStock); // Also printing the fuel stock to the text file.
            fuelFile.close(); // Closing the text file.
            System.out.println("\nSuccessfully store the data to the file");
        }
        catch (IOException ioException) { System.out.println("This file already exists!"); } // Handling the IOException.
    }

    public static void loadDataFromFile()
    {
        /*
            Loading data from the file
         */

        try {
            File fuelFile = new File("Fuel Management System - Galle.txt");
            Scanner scanner = new Scanner(fuelFile); // Reading the data inside the text file created.
            for (int i=0;i<6;i++) // Looping through 5 queues & the current fuel stock.
            {
                for (int j=0;j<6;j++) // Looping through the customers in each queue.
                {
                    if (scanner.hasNextLine()) // Checking whether there's a line to read or not.
                    {
                        String line = scanner.nextLine();
                        if (line.equals("null")) { fuelQueue[i][j] = null; }
                        else if (i==5) { fuelStock = Integer.parseInt(line);} // Converting the string value to integer.
                        else
                        {
                            String[] lineArray = line.split(" "); // Creating an array.
                            fuelQueue[i][j] = new FuelQueue();
                            fuelQueue[i][j].passenger.setFirstName(lineArray[0]);
                            fuelQueue[i][j].passenger.setLastName(lineArray[1]);
                            fuelQueue[i][j].passenger.setVehicleNumber(lineArray[2]);
                            fuelQueue[i][j].passenger.setNoOfLitres(Integer.parseInt(lineArray[3]));
                        }
                    }
                }
            }
            scanner.close(); // Closing the text file.
            System.out.println("\nSuccessfully load the data from the file");
        }
        catch (FileNotFoundException fileNotFoundException) { System.out.println("This file is not found.Try again!"); } // Checking whether there's a file or not by handling the FileNotFoundException.
    }

    public static void viewRemainingStock()
    {
        /*
            Displaying the remaining fuel stock
         */

        System.out.println("Remaining Fuel Stock: " + fuelStock + "L");
    }

    public static void addFuelStock()
    {
        /*
            Adding Fuel to the Stock
         */

        try {
            System.out.print("Enter the amount of fuel you need to add to the stock: ");
            int addFuelAmount = input.nextInt();
            fuelStock += addFuelAmount;
            System.out.print("Successfully added " + addFuelAmount + "L of fuel to the stock.\n\nDo you want to view the current stock?(Y/N) ");
            String viewStock = input.next().toUpperCase(); // Converting the given input to uppercase.
            if (viewStock.equals("Y")) { viewRemainingStock(); }
        }
        catch (InputMismatchException inputMismatchException) // If the user didn't enter an integer, then this will handle that InputMismatchException.
        {
            input.nextLine();
            System.out.println("Invalid Input.Try Again"); // Printing a statement showing the error instead of crashing the program flow.
        }
    }

    public static void calculateIncome()
    {
        /*
            Calculating the Income of each Queue respectively
         */
        double income = 0;
        for (int i=0;i<5;i++) // Looping through 5 queues.
        {
            System.out.print("\nIncome of Fuel Queue " + (i+1) + " => ");
            for (int j=0;j<6;j++) // Looping through the customers in each queue.
            {
                if (fuelQueue[i][j] != null){ income += fuelQueue[i][j].passenger.getNoOfLitres() * priceOfFuelLitre;} // Calculating the income as income = No: of litres * price of 1 litre.
            }
            System.out.format("Rs. %.2f",income); // Formatting the income as (.00).
            income = 0;
        }
        System.out.println();
    }

    public static int returningMinimumQueue()
    {
        /*
            Returning the Queue Number with minimum no: of passengers in the queue
         */

        int minimumCount = 6; // Initializing minimum no:of customers in a queue as 6.
        int minimumQueueNo = 0; // initializing queueNumber with minimum no:of customers as 0.
        for (int i=0;i<5;i++) // Looping through 5 queues.
        {
            int count = 0; // Initializing the no:of customers as 0.
            for (int j=0;j<6;j++) // Looping through the customers in each queue.
            {
                if (fuelQueue[i][j] != null)
                {
                    count ++; // If the element isn't null, then increment the count by adding 1.
                }
            }
            if (count<minimumCount) // After calculating the no:of customers in the queue as count, then checking whether that count is less than the minimumCount.
            {
                minimumCount = count; // Equalizing the count to minimumCount.
                minimumQueueNo = i + 1; // Getting the queue number with minimum customers.
            }
        }
        return minimumQueueNo; // Returning the queueNumber with minimum no:of customers.
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelQueue.class.getResource("fuel-queue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fuel Queue Management System");
        stage.setScene(scene);
        stage.show();
    }

}

