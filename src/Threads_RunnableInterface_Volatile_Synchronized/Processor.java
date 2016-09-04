package Threads_RunnableInterface_Volatile_Synchronized;

/**
 * Created by kush on 04/09/16.
 */
public class Processor extends Thread {

    @Override
    public void run() {
        //super.run();
        //First we have an infinite loop

        while(true){
            System.out.println("Threads_RunnableInterface_Volatile_Synchronized.Processor Hello Infinite Running");

            //Pause the thred for 10th of the second
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
