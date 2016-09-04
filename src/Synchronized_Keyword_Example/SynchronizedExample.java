package Synchronized_Keyword_Example;

 import java.util.Scanner;

//Program Not Working


class Data {

    private int id;

    private String name;

    public /*synchronized*/ void accept(int id, String name) {

        this.id = id;

        this.name = name;

    }

    public /*synchronized*/ void display() {

        System.out.println("ID : " + this.id);

        System.out.println("Name : " + this.name);

    }

}

//Accepting the elements for Data class objects

class AcceptThread implements Runnable{

    private String name;

    private int id;

    private Scanner kbd = new Scanner(System.in);

    private Data d;

    public void recieveData(Data d){

        this.d = d;

    }

    public void run() {

        try {

            System.out.println("Enter ID :");

            id = kbd.nextInt();

            System.out.println("Enter Name:");

            name = kbd.nextLine();

//calling "synchronized" object

            this.d.accept(id, name);

        } catch (Exception e) {

            System.out.println("Something went wrong here :"+e);

        }

    }

}

//Displaying the contents of Data class object

class DisplayThread implements Runnable{

    private Data d;

    public void receiveData(Data d){

        this.d = d;

    }

    public void run(){

        this.d.display();

    }

}

class SynchronizedExample {

    public static void main(String[] args) {

//Initializing Thread and other objects

        Data d = new Data();

        AcceptThread acceptObj = new AcceptThread();

        DisplayThread displayObj = new DisplayThread();

//calling appropriate methods to get hold of Data object 'd'

        acceptObj.recieveData(d);

        displayObj.receiveData(d);

        Thread acceptThread = new Thread(acceptObj,"Accept Thread");

        Thread displayThread = new Thread(displayObj,"Display Thread");

//Starting Threads

        acceptThread.start();


        while(acceptThread.isAlive());//I tried this to avoid the acceptThread from loosing control

        displayThread.start();

    }

}
