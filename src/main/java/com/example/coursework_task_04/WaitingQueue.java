package com.example.coursework_task_04;

public class WaitingQueue
{
    public int front, rear, size = 6;
    public FuelQueue[] waitingQueue = new FuelQueue[size]; // Creating a Waiting Queue Array with 6 passengers.

    public WaitingQueue(){ front = rear = -1; }

    public boolean isFull(){ return (rear + 1) % size == front; }

    public boolean isEmpty(){ return front == -1; }

    public void addingToWaitingQueue(FuelQueue fuelqueue) // If the Fuel Queue is full, adding the next customer to the Waiting Queue
    {
        if (isFull()){ System.out.println("The waiting list is full!!"); }
        else
        {
            if (front == -1){ front = 0; }
            rear = (rear + 1) % size;
            waitingQueue[rear] = fuelqueue;
            System.out.println("The customer is successfully added to the waiting list");
        }
    }

    public FuelQueue removingFromWaitingQueue() // Removing a customer from the Waiting Queue and adding to the Fuel Queue.
    {
        FuelQueue fuelQ;
        if (isEmpty()) { return null;}
        else
        {
            fuelQ = waitingQueue[front];
            if (front == rear) {front = rear = -1;} // Resetting the Waiting Queue.
            else { front = (front + 1) % size;}
        }
        return fuelQ;
    }
}
