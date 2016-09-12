package Threads_RunnableInterface_Volatile_Synchronized;

/**
 * Created by kush on 03/09/16.
 */
public class Runnable_Interface_Class implements Runnable {
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Hello This is Runnable" + i);
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
}


