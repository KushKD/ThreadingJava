package ThreadPractice;

/**
 * Created by kush on 08/09/16.
 */
public class Addition {



    public  int count = 0;

    public synchronized void increment(){

       count ++;
    }

    public synchronized void Addition(int i){


        count += i;
        System.out.println( "i is: "+ i +" count is: " + count);
    }



    public static void main(String args[]) {



        Addition main = new Addition();
        main.dowork();



    }

    private void dowork() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<1000;i++){

                    if(i%3==0 ){
                        System.out.println("Multiple of 3 is:"+ i+"\n");

                        //sum++;
                       // increment();
                        Addition(i);

                    }

                }

            }
        });

        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<1000;i++){

                    if(i%5==0 ){
                        System.out.println("Multiple of 5 is:"+ i+"\n");

                        //sum++;
                        // increment();
                        Addition(i);

                    }

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
