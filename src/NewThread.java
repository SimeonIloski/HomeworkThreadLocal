import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NewThread extends Thread {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId   = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>()
    {
        @Override
        protected Integer initialValue()
        {
            return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public int getThreadId()
    {
        return threadId.get();
    }
    // Returns the current thread's starting timestamp
    private static final ThreadLocal<Date> startDate = new ThreadLocal<Date>()
    {
        protected Date initialValue()
        {
            return new Date();
        }
    };



    @Override
    public void run()
    {
        //printing the date of creating of the thread
        System.out.println("Starting Thread: \t : \t"+ getThreadId()+startDate.get());
       try
        {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
            //getting random value for the thread squared and multiplied with the thread id
            Random random=new Random();
            int n=random.nextInt(50);
            n=(n*n)*threadId.get();
            System.out.println("Thread id is \t"+threadId.get());
            System.out.println("The random generated number squared and multiplied with current threadID is \t"+n);

        }  catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Thread Finished: \t : \t\n"+getThreadId()+startDate.get());
    }
}
