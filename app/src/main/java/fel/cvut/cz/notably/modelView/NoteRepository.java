package fel.cvut.cz.notably.modelView;

import android.app.Application;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;


import fel.cvut.cz.notably.dao.NoteDao;
import fel.cvut.cz.notably.database.AppDatabase;
import fel.cvut.cz.notably.entity.Note;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAll();
    }

    public LiveData<List<Note>> getmAllNotes() {
        return mAllNotes;
    }

    public LiveData<Note> getNoteById(int id) {
        return mNoteDao.findById(id);
    }

    public void insert (Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }

    public void update (Note note) {
        new updateAsyncTask(mNoteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        updateAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}




