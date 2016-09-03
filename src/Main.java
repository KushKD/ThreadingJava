/**
 * Created by kush on 03/09/16.
 */
public class Main {

    public static void main(String args[]){

       // Runnable_Interface_Class T = new Runnable_Interface_Class();
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

        t1.start(); //Runnable_Interface_Class One Running in its Own Thread
        t2.start(); //Runnable_Interface_Class Two Running in its Own Thread

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



    }


}
