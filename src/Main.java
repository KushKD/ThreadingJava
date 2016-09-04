import java.util.Scanner;

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


        //We have a problem if data is shared in more than one thread
        //Data being cashed   Use of the VOLATILE keyword  We have a class called processor

        Processor p1 = new Processor();
        p1.start();



        Shared_Data_Processor SDP = new Shared_Data_Processor();
        SDP.start();
        //Problem comes when we have to stop the tread in a proper way. So we use Intrupttions. We will be sharing the data in the Thread
        //Shared_Data_Processor is the class
        System.out.println("Press return key to stop the shared data processor thread");
        Scanner scanner = new Scanner(System.in);  //Taking the input to inturrept the thread
        scanner.nextLine();

        //Stoping the Thread
        SDP.shutdown();




    }


}
