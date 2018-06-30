package birhane.com.journalapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import birhane.com.journalapp.JournalApp;
import birhane.com.journalapp.data.model.Journal;
import birhane.com.journalapp.repository.JournalRepository;

public class MainViewModel extends AndroidViewModel {
    private int journalId;
    private LiveData<Journal> journal;
    private JournalRepository journalRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.journalRepository=new JournalApp().getJournalRepository();
    }

//    @Inject
//    public MainViewModel(JournalRepository journalRepository) {
//        this.journalRepository = journalRepository;
//    }

    public void init(int journalId) {
        if (this.journal != null) {
            return;
        }
        this.journal = this.journalRepository.getJournal(journalId);
    }

    public LiveData<Journal> getJournal() {
        return this.journal;
    }

    public LiveData<List<Journal>> getJournals() {
        return this.journalRepository.getJournals();
    }

}
