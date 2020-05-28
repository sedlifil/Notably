package fel.cvut.cz.notably;

import java.util.ArrayList;
import java.util.List;

import fel.cvut.cz.notably.entity.Note;

public class DBMock {

    private final List<Note> noteList;

    public DBMock() {
        noteList = new ArrayList<>();
        noteList.add(new Note("title 1", " nejaka poznamka"));
        noteList.add(new Note("title 2", " nejaka poznamka 2"));
        noteList.add(new Note("title 3", " nejaka poznamka 3"));
        noteList.add(new Note("title 4", " nejaka poznamka 4"));
    }

    public List<Note> getNoteList() {
        return noteList;
    }
}

