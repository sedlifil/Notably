package fel.cvut.cz.notably.modelView;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.List;

import fel.cvut.cz.notably.entity.Note;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    private LiveData<Note> searchByLiveData;
    private MutableLiveData<Integer> filterLiveData = new MutableLiveData<Integer>();


    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getmAllNotes();

        searchByLiveData = Transformations.switchMap(filterLiveData,v -> noteRepository.getNoteById(v));
    }


    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
    public LiveData<Note> getNoteById(){return searchByLiveData;}

    public void setFilter (Integer filter){filterLiveData.setValue(filter);}

    public void insert (Note note){noteRepository.insert(note);}

    public void update (Note note){noteRepository.update(note);}
}
