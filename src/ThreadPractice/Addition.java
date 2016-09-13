package ThreadPractice;


/**
 * Created by kush on 08/09/16.
 */

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

 Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class Addition {





    private volatile int i;
    private  int count =0;


Object lock = new Object();
    Object lock2 = new Object();

    public  void increment_i(String ThreadName){


synchronized (lock) {

    if (i % 3 == 0 || i % 5 == 0 && i<1000) {
        System.out.print(i + "\t" + ThreadName + "\n");
        count = count + i;
    }
    i++;
}





    }






    public static void main(String args[]) {



        Addition main = new Addition();
        main.dowork();



    }

    private void dowork() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {



                process("Thread 1");



            }
        });

        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {


                    process("Thread 2");


            }
        });

        Thread t3= new Thread(new Runnable() {
            @Override
            public void run() {

                process("Thread 3");


            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            System.out.println("Count is:- " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    private void process(String ThreadName) {


            while (i < 1000) {
                increment_i(ThreadName);
            }



    }


}
