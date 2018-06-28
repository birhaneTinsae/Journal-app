package birhane.com.journalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import birhane.com.journalapp.data.model.Journal;

public class AddJournalViewModel extends ViewModel {
    private LiveData<Journal> mJournal;

    public LiveData<Journal> getJournal() {
        return mJournal;
    }

}
