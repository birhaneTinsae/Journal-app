package birhane.com.journalapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import birhane.com.journalapp.AppExecutors;
import birhane.com.journalapp.R;
import birhane.com.journalapp.adapters.JournalAdapter;
import birhane.com.journalapp.data.model.Journal;
import birhane.com.journalapp.database.AppDatabase;
import birhane.com.journalapp.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements JournalAdapter.ItemClickListener {
    private RecyclerView mRecyclerView;
    private JournalAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.rv_articles);
        mAdapter = new JournalAdapter(this);
       // mAdapter.setJournals(getEntries());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                AppExecutors.getInstance().diskIO.execute(new Runnable() {
                    @Override
                    public void run() {
                        mDb.journalDao().deleteJournal(mAdapter.getJournal(position));
                    }
                });

            }
        }).attachToRecyclerView(mRecyclerView);

        FloatingActionButton fab = findViewById(R.id.fab_add_new_journal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addEntryIntent =
                        new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(addEntryIntent);
            }
        });
        mDb = AppDatabase.getInstance(this);
        setupViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handles click event on recyclerview item and pass item's index
     *
     * @param clickedItemIndex item index
     */
    @Override
    public void onItemClickListener(int clickedItemIndex) {
        Intent intent = new Intent(this, AddNewActivity.class);
        intent.putExtra(AddNewActivity.EXTRA_ENTRY_ID, mAdapter.getJournal(clickedItemIndex).getId());
        startActivity(intent);

    }

    private void setupViewModel() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getJournals().observe(this, new Observer<List<Journal>>() {
            @Override
            public void onChanged(@Nullable List<Journal> journals) {
                mAdapter.setJournals(journals);
            }
        });
    }

    /**
     * TODO: delete after firebase store functionality added
     */
    private List<Journal> getEntries() {
        ArrayList<Journal> entries = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            entries.add(new Journal("Snowy day today", "Still testing journaling apps,but got a few posts edited.December posts lool goods o far.", new Date(), new Date()));
        }
        return entries;
    }
}
