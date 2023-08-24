import java.util.*;



public class Main {
    public static void main(String[] args) {
        /*
         * Each thread will run the session class.
         * This is done to achieve concurrency.
         */
        int n = 2;
        for (int i = 0; i < n; i++) {
            Session newSession = new Session();
            Thread thread = new Thread(newSession);
            thread.start();
            /*
             * Here, thread.join() method to used to differntiate each customer output from
             * each.
             * thread.join() makes each customer start after the previous customer leaves.
             * To get concurrency, Comment the try-catch block.
             * By doing so, all customers can access the Online-Book store concurrently.
             */
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
