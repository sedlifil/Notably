package fel.cvut.cz.notably;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fel.cvut.cz.notably.entity.Note;
import fel.cvut.cz.notably.modelView.NoteViewModel;

public class MainActivity extends AppCompatActivity implements NoteRecyclerViewAdapter.OnItemLongClick,NoteRecyclerViewAdapter.OnItemClick {

    private RecyclerView recyclerView;
    private TextView textNoNote;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NoteRecyclerViewAdapter viewAdapter;
    private ActionMode actionMode;
    private SharedPreferences.Editor editor;
    private NoteViewModel noteViewModel;


    private List<Note> noteList, selectedItemID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        textNoNote = findViewById(R.id.textNoNote);
        recyclerView = findViewById(R.id.recyclerView);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navView);

        //This will place a Navigation Drawer icon on the Tool bar
        //When Clicked it will open the Navigation Drawer and Animation happens on the icon
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        noteList = new ArrayList<>();
        selectedItemID = new ArrayList<>();

        System.out.println("Adapter test"+ noteList.size());
        viewAdapter = new NoteRecyclerViewAdapter(noteList,this, selectedItemID);
        recyclerView.setAdapter(viewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        viewAdapter.setActionModeReceiver(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable final List<Note> notes) {
                // Update the cached copy of the words in the adapter.
                System.out.println("ON CHANGE");
                viewAdapter.setNoteList(notes);
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddNoteActivity.class), 12);
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK) {
            Note note = (Note) data.getSerializableExtra(AddNoteActivity.EXTRA_REPLY);
            Toast.makeText(
                    getApplicationContext(),
                    "Note saved",
                    Toast.LENGTH_LONG).show();
            noteViewModel.insert(note);
            }
         else {
            Toast.makeText(
                    getApplicationContext(),
                    "Note not saved",
                    Toast.LENGTH_LONG).show();
        }
    }


    private void displayAllNote() {
        noteList.clear();
        selectedItemID.clear();

        //noteDB.open();
        //Cursor cursor = mock.getAllNotes();
        setRecyclerViewVisibility();
        viewAdapter.notifyDataSetChanged();
        //noteDB.close();

        viewAdapter.updateFilterNoteList(noteList);
    }
    private void setRecyclerViewVisibility() {
        if (!noteList.isEmpty()) {
            textNoNote.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            textNoNote.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
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
/*    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.delete_note, menu);
            actionMode.setTitle("Delete Note");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.deleteIcon:
                    new DeleteNote(MainActivity.this).execute();

                    disableItemClick();

                case R.id.selectAll:
                    viewAdapter.selectAllNote();
                    return true;

                case R.id.unSelectAll:
                    viewAdapter.unSelectAllNote();
                    actionMode.finish();

                    disableItemClick();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            viewAdapter.unSelectAllNote();
            actionMode = null;
        }
    };*/


    @Override
    public void onItemLongClick() {
  /*      if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);

            editor=sharedPreferences.edit();
            editor.putBoolean(ITEM_IS_LONG_CLICKED,true);
            editor.apply();
        } else {
            if (selectedItemID.isEmpty()) {
                actionMode.finish();

                disableItemClick();
            }
        }*/
    }

    @Override
    public void onItemClick() {
        /*if (selectedItemID.isEmpty()) {
            actionMode.finish();

            disableItemClick();
        }*/
    }
}
