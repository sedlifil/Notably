package fel.cvut.cz.notably;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fel.cvut.cz.notably.entity.Note;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textNoNote;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NoteRecyclerViewAdapter viewAdapter;
    private List<Note> noteList, selectedItemID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textNoNote = findViewById(R.id.textNoNote);
        recyclerView = findViewById(R.id.recyclerView);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navView);

        //This will place a Navigation Drawer icon on the Tool bar
        //When Clicked it will open the Navigation Drawer and Animation happens on the icon
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        noteList = new ArrayList<>();
        selectedItemID = new ArrayList<>();

        viewAdapter = new NoteRecyclerViewAdapter(noteList, this, selectedItemID);
        recyclerView.setAdapter(viewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        //viewAdapter.setActionModeReceiver(this,this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
/*
        private void displayAllNote() {
            noteList.clear();
            selectedItemID.clear();

            noteDB.open();
            Cursor cursor = noteDB.getAllNotes();
            while (cursor.moveToNext()) {
                noteList.add(new NoteItems
                        (cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)));
                //Log.d("My Tag",cursor.getLong(4)+"\t\t"+cursor.getInt(5)+"\t\t"+cursor.getString(6));
            }
            setRecyclerViewVisibility();
            viewAdapter.notifyDataSetChanged();
            noteDB.close();

            viewAdapter.updateFilterNoteList(noteList);
        }


        private void displayAllReminderNote(){
            noteList.clear();
            selectedItemID.clear();

            noteDB.open();
            Cursor cursor = noteDB.getAllReminderNotes();
            while (cursor.moveToNext()) {
                noteList.add(new NoteItems
                        (cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)));
                //Log.d("My Tag",cursor.getLong(4)+"\t\t"+cursor.getInt(5)+"\t\t"+cursor.getString(6));
            }
            setRecyclerViewVisibility();
            viewAdapter.notifyDataSetChanged();
            noteDB.close();

            viewAdapter.updateFilterNoteList(noteList);
        }
*/


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
}
