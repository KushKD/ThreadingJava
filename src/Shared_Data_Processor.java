/**
 * Created by kush on 04/09/16.
 */
public class Shared_Data_Processor extends Thread {

    //Stage 1 : -  This code will run without an error. But there is a catch Thare two threadsi.e. the main thread aand the Shared_Data_Processor thread.
    //Both these threads are trying to access the running keyword.Main thread is writing the state to runing and
    //Shared_Data_Processor is reading the state of running .
    //To stop the cashing of the variables we use the volatile keyword.
    //private  boolean running = true;

    //Stage II:-  using the volatile keyword to stop the chaching of the variable.
    private volatile  boolean running = true;

    //It can be used to shout down other threads from the main thread gracefully (In an appropriate manner).
    @Override
    public void run() {
        //super.run();

        while(running){
            System.out.println("Shared Data Processor Hello");

            //Pause the thread for 10th of the millisecond

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }

    public void shutdown(){
        running = false;
    }
}
