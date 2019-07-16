import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NewThread extends Thread {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId   = new AtomicInteger(0);
    static int n;
    static Person person;
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
       // using a ThreadLocal variable,primitive type variable and custom object
        synchronized (this) {
            try {
                TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
                //getting random value for the thread squared and multiplied with the thread id
                Random random = new Random();
                n = random.nextInt(50);
                n = (n * n) * threadId.get();
                //printing a Thread Local in synchronized block
                System.out.println("Thread id is (printed in synchronized block like a thread local) \t" + threadId.get());
                String name="personname"+ threadId.get();
                String surname="personsurname"+threadId.get();
                person=new Person(name,surname);
                //printing a primitive number in  synchronized block
                System.out.println("The random primitive generated number(in synchronized block) squared and multiplied with current threadID is \t" + n);
                //printing a custom object in  synchronized block
                System.out.println("My custom created object Person in synchronized block has name \t"+person.getName()+"\t and surname \t"+ person.getSurname());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread Finished: \t : \t\n"+getThreadId()+startDate.get());
    }
}
