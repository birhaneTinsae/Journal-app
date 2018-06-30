package birhane.com.journalapp.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import birhane.com.journalapp.AppExecutors;
import birhane.com.journalapp.dao.JournalDao;
import birhane.com.journalapp.data.model.Journal;
import birhane.com.journalapp.database.AppDatabase;


public class JournalRepository {

    private final AppDatabase mDb;
    private static JournalRepository sInstance;
    private static final Object LOCK=new Object();


    private JournalRepository(final AppDatabase database) {
       this.mDb=database;
    }

    public static JournalRepository getInstance(final AppDatabase database) {
        if (sInstance==null){
            synchronized (LOCK){
                sInstance=new JournalRepository(database);
            }
        }
        return sInstance;
    }

    public LiveData<Journal> getJournal(int journalId) {

        return mDb.journalDao().loadJournalById(journalId);
    }

    private void refreshJournals() {
        AppExecutors.getInstance().diskIO.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public LiveData<List<Journal>> getJournals() {
        refreshJournals();
        return mDb.journalDao().getAllJournals();

    }
}
