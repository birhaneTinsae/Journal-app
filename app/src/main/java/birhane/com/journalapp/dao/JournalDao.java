package birhane.com.journalapp.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import birhane.com.journalapp.data.model.Journal;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface JournalDao {
    @Insert(onConflict = REPLACE)
    void save(Journal journal);

    @Query("SELECT * FROM Journal")
    LiveData<List<Journal>> getAllJournals();

    @Query("SELECT * FROM Journal WHERE id=:id")
    LiveData<Journal> loadJournalById(int id);

    @Update(onConflict = REPLACE)
    void updateJournal(Journal journal);

    @Delete
    void deleteJournal(Journal journal);


}
