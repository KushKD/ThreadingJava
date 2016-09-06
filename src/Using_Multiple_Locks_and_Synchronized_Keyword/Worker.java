package Using_Multiple_Locks_and_Synchronized_Keyword;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kuush on 9/5/2016.
 */
public class Worker {


    // MULTIPLE LOCKS USING SYNCRONIZED CODE BLOCKS
    //Declare a list
    // Working with list using different threads
    //we want to give this worker object some methors , We will imagine that worker does some processing and the processing can be broken down into stages.
    //We will declare two methords, STAGE 1 and STAGE2
    //Stage One will do something with List One and we will generate a  random number using the Random Variable.
    //Lets Imagine that Stage One Does a Little bit of calculation that takes time or it gets some information from others, To stimulate that we will be having a Thread.sleep()
    //for one millisecond and then we'll be adding integer to list One.
    //Now Stage two will add integer to list two.
    //Now we have a process methord that will do all the processing.
    //Process methord will call stage one and stage 2 in a loop and let there be 1000 ittrations
    //We'll be analying the how much time it takes to finish the process, so in the main we calculate and print the start time and the end time.
    //We'll also be printing the list sizes to see what are the size of the two lists.
    //Now between the start and end we will call the process method , so the process methord will ittrate a 1000 times and every time it ittrates it will call stage one and then stage2
    //Stage One is writting to first list and Stage2 is writting to second list.
    //Now when we run the program , the procss takes 2636 milliseconds which is  a bit longer as we are calling the Thread.sleep of 1 miilisecond inside each stage.
    //We can speed up the process by using multiple Threads (Multiple Threading).
    //We'll create a new Thread and we will pass it a new instance of Runnable and we will call teh process methord int the run method.
    //We can start this thread just by calling the .start method. This is the Shortest way to run a thread
    //Now when we run the program, time taken is 2 second but the size of list is zero as the thread is running in the background and we have printed the value of list.
    //We have to use the .join() methord and name the thread t1
    //When we run this program it takes 2781 seconds and the size of list is 100
    //Now we will have another thread t2 which runs the same pocess at the same time . We are going to run these two threads simaltaniously.
    //Now we run the code , the program takes 2 second and the size of lists is different i.e. List1: 1994; List2: 1999
    //Some times we will be getting the exception as we are trying to access the shared data in multiple threads
    //We are getting the same problem of threads Interleving , so we know that we have to make these methords Synchronized to avoid the Interleving problem.
    //So, we will make both the methords synchronized.
    //Now when we run the program we expect the program to be finished in 2 seconds with the same number of items in the list but it takes more than 4 seconds for execution but the
    //size of list is same. Time Taken: 4634 List1: 2000; List2: 2000
    //Now the problem is , the two treads are takeing almost double the time for execution. The Problem is there is only one Intrensic Lock of the worker object.
    //When the first stage starts , the second stage has to wait to aquire the Intrinsic Lock of the worker object.
    //No two threads can run stage1 at the same time and not two threads can run stage2 at the same time. But one thread can use stage1 and the other thread can use stage2 at the same time.
    //We can do that by creating locks and synchronising two locks separately.
    //We will create two new  instance of object class lock1 and lock2
    //So, Instead of having Synchronized methords we are going to have synchronized code blocks
    //First we will synchronized lock1 in stage one and remove the synchronized keyword from stage one method
    //Then we will  synchronized lock2 in stage two and remove the synchronized keyword from stage two method
    //Now Synchronized code blocks , Two threads can run the stage1 methord at the same time and two threads can run the stage two methords at the same time. But if one thread
    // has entered the syncronized code block the other thread has to wait untill the first thread has released the lock by exiting syncronized the code block.
    //Now there are two intrensic locks one of lock1 and the other of lock2
    //Now when we run the code time taken to execute the process has dropped to 2133 seconds.


    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    public  void stage1(){

        synchronized(lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }


    }

    public  void stage2(){
        synchronized(lock2){

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
        }

    }

public void process(){

    for(int i=0; i<10000; i++){
        stage1();
        stage2();
    }
}

    public void main(){
        System.out.println("Hello Worker Class.");
        System.out.println("Starting......");

        long start = System.currentTimeMillis();
       // process();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
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
        long end = System.currentTimeMillis();

        System.out.println("Time Taken: "+ (end - start));
        System.out.println("List1: "+ list1.size() +"; List2: "+ list2.size());
    }
}
