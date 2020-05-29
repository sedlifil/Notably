package fel.cvut.cz.notably;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    private EditText textTitle, textNote;
    private TextView textReminderTimeDate;
    private LinearLayout linearLayout;
    private String time, date, reminderRepeatItem;
    private int reminderRepeatIndex,pendingIntentID;
    //private long calendarTimeMillieSec;
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String REMINDER_REPEAT = "repeat";
    //private Bundle reminderValuesBundle;
    private Calendar calendar;
    private String dateTime;
    //private long insertedNoteID;

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
}