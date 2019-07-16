import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        NewThread newThread1 = new NewThread();
        NewThread newThread2 = new NewThread();
        NewThread newThread3 = new NewThread();
        NewThread newThread4 = new NewThread();
        NewThread newThread5 = new NewThread();
        newThread1.start();
        newThread2.start();
        newThread3.start();
        newThread4.start();
        newThread5.start();
        newThread1.run();
        newThread2.run();
        newThread3.run();
        newThread4.run();
        newThread5.run();
        newThread1.start();
        newThread2.stop();
        newThread3.stop();
        newThread3.stop();
        newThread4.stop();
        newThread5.stop();
    }

}
