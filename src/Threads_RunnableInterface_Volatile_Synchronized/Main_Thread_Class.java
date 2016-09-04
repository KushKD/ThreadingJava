package Threads_RunnableInterface_Volatile_Synchronized;

/**
 * Created by kush on 03/09/16.
 */
public class Main_Thread_Class extends Thread {

    @Override
    public void run() {
       // super.run();

        for (int i=0;i<20;i++){
            System.out.println("Hello" + i);
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
