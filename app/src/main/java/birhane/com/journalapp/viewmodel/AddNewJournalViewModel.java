package birhane.com.journalapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import birhane.com.journalapp.JournalApp;
import birhane.com.journalapp.data.model.Journal;
import birhane.com.journalapp.repository.JournalRepository;

public class AddNewJournalViewModel extends ViewModel {
    private LiveData<Journal> mJournal;

    public AddNewJournalViewModel(/*Application application,*/ JournalRepository journalRepository, final int journalId) {
//        super(application);
        this.mJournal = journalRepository.getJournal(journalId);
    }

    public LiveData<Journal> getJournal() {
        return mJournal;
    }


}
