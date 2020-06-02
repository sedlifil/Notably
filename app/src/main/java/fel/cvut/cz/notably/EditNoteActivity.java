package fel.cvut.cz.notably;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import fel.cvut.cz.notably.entity.Note;
import fel.cvut.cz.notably.modelView.NoteViewModel;

public class EditNoteActivity extends AppCompatActivity {


    private EditText textTitle, textNote;
    private TextView textViewTitle, textViewNote, textTime, textReminderDate;
    private LinearLayout linearLayout;
    private int selectedNoteId;
    private Menu menu;
    private FloatingActionButton fab;
    private InputMethodManager inputMethodManager;
    private Bundle reminderValuesBundle;
    private Calendar calendar;
    private String time, date, reminderRepeatItem;
    private int reminderRepeatIndex, pendingIntentID;
    private long calendarTimeMillieSec;
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String REMINDER_REPEAT = "repeat";
    public static final String SELECTED_NOTE_PENDING_INTENT_ID = "selectedNotePendingIntentID";
    public static final String SELECTED_NOTE_ID = "selectedNoteID";
    private int selectedNotePendingIntentID;
    private ImageView reminderEditIcon;
    private String selectedTitle, selectedNote, selectedNoteRepeatStatus;
    public Note note;
    private int hid;

    public static final String EXTRA_REPLY = "EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        textTitle = findViewById(R.id.editTextTitle);
        textNote = findViewById(R.id.editTextNote);
        linearLayout = findViewById(R.id.linearLayout);


        //textReminderTimeDate = findViewById(R.id.textReminderTimeDate);
        //Toolbar toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        hid = extras.getInt("note_id");
        System.out.println("extras: " + extras);
        note = (Note) extras.getSerializable("note");
        System.out.println("hid: "+ hid);
        System.out.println(note);
//        System.out.println(note.getText());
        if (note != null){
            textTitle.setText(note.getTitle());
            textNote.setText(note.getText());
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                String title = textTitle.getText().toString().trim();
                String note = textNote.getText().toString().trim();
                //SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd/MMM/yy", Locale.UK);
                //dateTime = dateFormat.format(new Date());
                if (title.isEmpty() && note.isEmpty()) {
                    Toast.makeText(EditNoteActivity.this, "Insert Your Title and Note", Toast.LENGTH_SHORT).show();
                } else if (title.isEmpty()) {
                    Toast.makeText(EditNoteActivity.this, "Insert Your Title", Toast.LENGTH_SHORT).show();
                } else if (note.isEmpty()) {
                    Toast.makeText(EditNoteActivity.this, "Insert Your Note", Toast.LENGTH_SHORT).show();
                } else {
                    Note newNote = new Note(title, note);


                    Intent replyIntent = new Intent();

                    replyIntent.putExtra(EXTRA_REPLY, newNote);
                    setResult(RESULT_OK, replyIntent);
                    Toast.makeText(EditNoteActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();

                    finish();
                }
        }


        return true;
    }


}