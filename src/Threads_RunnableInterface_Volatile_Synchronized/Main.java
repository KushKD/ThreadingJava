package Threads_RunnableInterface_Volatile_Synchronized;

import java.util.Scanner;

/**
 * Created by kush on 03/09/16.
 */
public class Main {

    private int count = 0;

    public synchronized void increment(){
        count ++;
    }

    public static void main(String args[]){

       // Threads_RunnableInterface_Volatile_Synchronized.Runnable_Interface_Class T = new Threads_RunnableInterface_Volatile_Synchronized.Runnable_Interface_Class();
       // T.run();

        //Runner 1 running in own thread
        Main_Thread_Class runner1 = new Main_Thread_Class();
        runner1.start();

        //Runner Two Running in its Own Thread
        Main_Thread_Class runner2 = new Main_Thread_Class();
        runner2.start();

        //Runner Three Running in its Own Thread
        Main_Thread_Class runner3 = new Main_Thread_Class();
        runner3.start();

        //All the Loops are Running at the same Time.
        Thread t1 = new Thread(new Runnable_Interface_Class());
        Thread t2 = new Thread(new Runnable_Interface_Class());

        t1.start(); //Threads_RunnableInterface_Volatile_Synchronized.Runnable_Interface_Class One Running in its Own Thread
        t2.start(); //Threads_RunnableInterface_Volatile_Synchronized.Runnable_Interface_Class Two Running in its Own Thread

        //Third way to create a thread is
        Thread x = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<20;i++){
                    System.out.println("Hello This is the third Type of thread" + i);
                    //Slow a Loop a Bit ,
                    //We'll use static methord of the Tread class

                    try {
                        //Sleep pauses the program for the specific number of millisecond
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        //Third way to create a thread is
        Thread y = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<20;i++){
                    System.out.println("Hello This is the third Type of thread" + i);
                    //Slow a Loop a Bit ,
                    //We'll use static methord of the Tread class

                    try {
                        //Sleep pauses the program for the specific number of millisecond
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


        x.start();
        y.run();

        //Basic Thread Synchronization
        //We have a problem if data is shared in more than one thread
        //Data being cashed   Use of the VOLATILE keyword  We have a class called processor

      //  Threads_RunnableInterface_Volatile_Synchronized.Processor p1 = new Threads_RunnableInterface_Volatile_Synchronized.Processor();   working code with infinite loop
      //  p1.start();       working code with infinite loop



        Shared_Data_Processor SDP = new Shared_Data_Processor();
        SDP.start();
        //Problem comes when we have to stop the tread in a proper way. So we use Intrupttions.
        // We will be sharing the data among two Thread
        //Threads_RunnableInterface_Volatile_Synchronized.Shared_Data_Processor is the class
        System.out.println("Press return key to stop the shared data processor thread");
        Scanner scanner = new Scanner(System.in);  //Taking the input to inturrept the thread
        scanner.nextLine();

        //Stoping the Thread
        SDP.shutdown();

        //Certain Woman is the new movie
        //Now we'll be looking at the syncronized keyword
        //Two threads trying to increment the variable count at the same time using two different threads .
        //In other tearms both the threads are accssing the variable count at the same time.
        // When we run the program the count is 0 bacause the threads are running in their own processes and count is printed
        //before the threads are started.
        //we need to use use the .join methord of the threads so that count is not printed untill the threads are finished
        //When we run the code the the count is 20000. We we run the code the other time the count is 12345 .
        //Sometimes it works and sometimes it doesn't
        //What happens is when we increment the varable count in the two threads its like the atomic operation ,
        // in count++ there are actually three steps count = count + 1
        // First we get the value of count
        //Second we Incremet the value of count
        //Third we store the value of count
        //From Computer point of view :
        // First the computer is getting the value of count , and somethings happen in the background
        //Second the computer is incrementing the value by 1 , and some more things happen in the background
        //Thirs the computer stores the value of count ,  and some other processes are running in the background
        //Now think what happens when two threads are trying to theses steps at the same time.
        //The problem is both the threads read the value of count
        //What we want to do is , when one thread is reading the value of count , no other thread can read and write to the value
        //of count . We can do this by making the integer , the atomic integer because atomic integer is a specialized class
        //which allows us to increment the value of count in just one step.
        //Volatile keyword might fix the problem to an extent, but it will not fix the underlying problem because the basic problem is not
        //CACHING it is INTERLEAVING
        //To fix this we need the SYNCHRONIZED keyword
        //What we'll do is create a method here public void increment and in the increment method we will say count++.
        //We will change the threads to use the increment methods. We will remove the count++ from both the threads.
        //Now this won't fix anything, what we can use is making the increment method synchronized.
        //Now when we run the code the count is 20000 always.
        //What synchronize actually does is :
        //Every object is JAVA has an Intrinsic Lock or Monitor Lock , we can also call it MUTEX , and if we call the synchronized method
        //of the object, in this we are calling the Threads_RunnableInterface_Volatile_Synchronized.Main object , we have to acquire the Intrinsic lock before we can call it.
        //And the thing is only one thread can acquire the Intrinsic lock at a Time and if onr Thread acquires the Intrinsic Lock and runs
        //the method i.e. increment and if other threead at the same time tries to call this methord then second thread just have to wait
        //untill the first thread releases the Intrinsic Lock by the methord finishing and executing.
        //Whenever we got multiple threads accesing shared data

        Main main = new Main();
        main.dowork();


    }

    private void dowork()  {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0 ;i<150000;i++){
                   // count++;
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0 ;i<150000;i++){
                   // count++;
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Count is:- " + count);
    }


}
