package birhane.com.journalapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import birhane.com.journalapp.R;
import birhane.com.journalapp.data.model.Journal;
import birhane.com.journalapp.viewmodel.MainViewModel;

public class AddNewActivity extends AppCompatActivity {
    private int mJournalId;
    public final static String EXTRA_ENTRY_ID = "entry_id";
    public final static int DEFAULT_ENTRY_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_ENTRY_ID)) {
            mJournalId = intent.getIntExtra(EXTRA_ENTRY_ID, DEFAULT_ENTRY_ID);
            MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
            viewModel.init(mJournalId);
            viewModel.getJournal().observe(this, new Observer<Journal>() {
                @Override
                public void onChanged(@Nullable Journal journal) {
                    populateUI(journal);
                }
            });
        }

    }

    public void populateUI(Journal journal) {

    }

    public void onSaveButtonClicked() {

    }
}
