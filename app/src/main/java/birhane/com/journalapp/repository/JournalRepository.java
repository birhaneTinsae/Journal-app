package birhane.com.journalapp.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import birhane.com.journalapp.AppExecutors;
import birhane.com.journalapp.dao.JournalDao;
import birhane.com.journalapp.data.model.Journal;


public class JournalRepository {
    private final JournalDao mJournalDao;
    private static JournalRepository sInstance;
    private static final Object LOCK=new Object();


    private JournalRepository(JournalDao journalDao) {
        this.mJournalDao = journalDao;
    }

    public static JournalRepository getInstance(JournalDao journalDao) {
        if (sInstance==null){
            synchronized (LOCK){
                sInstance=new JournalRepository(journalDao);
            }
        }
        return sInstance;
    }

    public LiveData<Journal> getJournal(int journalId) {

        return mJournalDao.loadJournalById(journalId);
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
        return mJournalDao.getAllJournals();

    }
}
