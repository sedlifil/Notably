package fel.cvut.cz.notably.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fel.cvut.cz.notably.entity.Note;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAll();

    @Query("SELECT * FROM  note  ORDER BY title ASC LIMIT  :position,1")
    Note findByPosition(int position);

    @Query("SELECT * FROM note where title LIKE  :title")
    Note findByTitle(String title);

    @Query("SELECT * FROM note where id = :id ")
    LiveData<Note> findById(int id);

    @Query("SELECT COUNT(*) from note")
    int countHomes();

    @Insert
    void insertAll(Note... notes);

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
}

