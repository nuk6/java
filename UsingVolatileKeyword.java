public class Test1 {
    public volatile static boolean shared = true; // Using volatile the updates will be push to shared cache
    /*
    * So each thread has : Local Cache >> Shared cache
    * when a shared value is changes by one thread, the other thread may keep reading the stale val
    * But using volatile flushed the updates to the shared cache
    * */
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(Test1::run);
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2500);
                shared = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t2.start();
    }

    private static void run() {
        try {
            int i = 0;
            while(Test1.shared) {
                System.out.println("Running..." + ++i);
                //Thread.sleep(500);
            }
            System.out.println("Came out...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
