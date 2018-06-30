package birhane.com.journalapp;

import android.app.Application;

import birhane.com.journalapp.database.AppDatabase;
import birhane.com.journalapp.repository.JournalRepository;

public class JournalApp extends Application {

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public JournalRepository getJournalRepository() {
        return JournalRepository.getInstance(getDatabase());
    }
}
