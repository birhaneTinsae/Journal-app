package birhane.com.journalapp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final Object LOCK = new Object();
    public final Executor diskIO;
    private static AppExecutors sInstance;

    private AppExecutors(Executor diskIO) {
        this.diskIO = diskIO;
    }

    public static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return sInstance;
    }
}
