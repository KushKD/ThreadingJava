package Thread_Pools;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by kuush on 9/5/2016.
 */
public class App {

    //ThreadPools are a way of managing lots of Threads at the same time.
    //Lets say we have a processor class here which implements the runnable interface and implement the run methord.
    //As we are going to fire lots of Processors at the same time so we are going to have a constructor with id , so we can tell which one is which
    //When the processing starts we will display a message Starting ..... with ID
    //And when our processing finishes we will display a Message Completed .... with ID
    //and in the middle here we are going to simulate some useful work like for e.g. handling requests if it is a server
    // or processing file . As usual we will be using thread.sleep() method to sleep for 5 millisecond.
    //Lets say we want to create 10 of these Processors and make them work though 10 of these tasks ,We could use the Thread class as usual
    //but instead we can use ExecutorService and we call it executor , and we use the static methord of the executors class
    //called the new fixedthreadpools(2) to create two threads.
     //A threadpool is like having number of workers in a factory , in this case we are having two and we can allot them tasks
    // that we want them to be done.
    //It is just like giving two factory workers a bunch of tasks saying here please work on these tasks one at a time and
    //when you finish one task start on a new task
    //Now to allot the tasks here we want to submit the tasks to executor
    //The ececutor Service will run its own managable thread that will handle the tasks that we will give to the executr.
    //So we want to submit a bunch of tasks to it (Executor)
    //Suppose we want to give 5 tasks in this example , so we have a loop
    //Now we'll say executor.submit to submit a task  and pass the processor to the submit methord with ID.
    //As we know that executor service has its own special managable thread , and we want to tell that thread to stop accepting new tasks (after the loop finishes)
    //and to shut it self down when all the tasks have finished
    // We'll say executor.shutdown().It will not shutdown immediately , it will wait for all the task to be completed and then it will terminate.
    //Now suppose we want to wait for all the tasks to complete,what we can do is we can use executor.awaitTermination(Time,UnitsInTime)
    //Time  == Specify the time we want to wait for lets say 1
    //UnitsInTime == Is the Units in time lets say TimeUnits.DAY
    //


    public static void main(String Args[]){
        ExecutorService executor = Executors.newFixedThreadPool(2); //To prevent the overhead

        for(int i=0; i<5 ; i++){
            executor.submit(new Processor(i));
        }

        executor.shutdown(); //it will only shutdown if all the tasks are completed

        System.out.println("All tasks Submitted..");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);  //Wait for one day
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks Submitted..");
    }
}
