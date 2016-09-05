package Thread_Pools;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kuush on 9/5/2016.
 */
public class App {

    //A threadpool is like having number of workers in a factory , in this case we are having two and we can allot them tasks
    // that we want to be done.


    public static void main(String Args[]){
        ExecutorService executors = Executors.newFixedThreadPool(2);
    }
}
