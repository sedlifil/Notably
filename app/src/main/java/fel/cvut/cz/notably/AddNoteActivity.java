package fel.cvut.cz.notably;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fel.cvut.cz.notably.entity.Note;
import fel.cvut.cz.notably.modelView.NoteViewModel;

public class AddNoteActivity extends AppCompatActivity {

    private EditText textTitle, textNote;
    private TextView textReminderTimeDate;
    private LinearLayout linearLayout;
    private String time, date, reminderRepeatItem;
    private int reminderRepeatIndex, pendingIntentID;
    //private long calendarTimeMillieSec;
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String REMINDER_REPEAT = "repeat";
    //private Bundle reminderValuesBundle;
    private Calendar calendar;
    private String dateTime;
    //private long insertedNoteID;
    private NoteViewModel noteView;

    public static final String EXTRA_REPLY = "EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        textTitle = findViewById(R.id.editTextTitle);
        textNote = findViewById(R.id.editTextNote);
        //textReminderTimeDate = findViewById(R.id.textReminderTimeDate);
        linearLayout = findViewById(R.id.linearLayout);
        //Toolbar toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note, menu);
        return true;
    }

    //Save Note when the save icon is selected on the Menu Bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                String title = textTitle.getText().toString().trim();
                String note = textNote.getText().toString().trim();
                //SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd/MMM/yy", Locale.UK);
                //dateTime = dateFormat.format(new Date());
                if (title.isEmpty() && note.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Insert Your Title and Note", Toast.LENGTH_SHORT).show();
                } else if (title.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Insert Your Title", Toast.LENGTH_SHORT).show();
                } else if (note.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Insert Your Note", Toast.LENGTH_SHORT).show();
                } else {
                    Note newNote = new Note(title, note);


                    Intent replyIntent = new Intent();

                    replyIntent.putExtra(EXTRA_REPLY, newNote);
                    setResult(RESULT_OK, replyIntent);
                    Toast.makeText(AddNoteActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();

                    finish();
                }
        }


        return true;
    }


}