package fel.cvut.cz.notably;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fel.cvut.cz.notably.entity.Note;

public class
NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder> implements Filterable {

    private List<Note> noteList, selectedItemID, filterNoteList;
    private Context context;
    private OnItemLongClick onItemLongClickReceiver;
    private OnItemClick onItemClickReceiver;


    /**
     * This interface will be called when a view is long clicked
     * to be able to highlight the selected item view
     */
    public interface OnItemLongClick {
        void onItemLongClick();
    }

    /**
     * This interface will be called when a view is clicked
     * and the Contextual Action Mode is Visible
     * this will make sure when user long click to select an item
     * user will be able to click to select next item in recycler view
     * to be able to highlight the selected item view
     */
    public interface OnItemClick {
        void onItemClick();
    }

    public NoteRecyclerViewAdapter(List<Note> noteList, Context context, List<Note> selectedItemID) {
        this.noteList = noteList;
        this.context = context;
        this.selectedItemID = selectedItemID;
        filterNoteList = new ArrayList<>();
    }

    public void updateFilterNoteList(List<Note> noteList) {
        filterNoteList.clear();
        filterNoteList.addAll(noteList);
    }

    @Override
    public Filter getFilter() {
        return filterList;
    }

    private Filter filterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            System.out.println("perform Filtering");
            List<Note> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filterNoteList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Note noteItems : filterNoteList) {
                    if (noteItems.getTitle().toLowerCase().contains(filterPattern) || noteItems.getText().toLowerCase().contains(filterPattern)) {
                        filteredList.add(noteItems);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            System.out.println("publish Results");
            noteList.clear();
            noteList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    /*/**
     * Filter the recycler view with data that contains user input in search view
     * @param filterList
     *//*
    public void filterRecyclerView(List<NoteItems> filterList){
        noteList=new ArrayList<>();
        noteList.addAll(filterList);
        notifyDataSetChanged();
    }*/


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes, viewGroup, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder noteViewHolder, final int position) {
        final Note noteItems = noteList.get(position);
        System.out.println("onBindViewHolder" +noteList.size());

        //noteViewHolder.textID.setText(String.valueOf(noteItems.g()));
        noteViewHolder.textTitle.setText(noteItems.getTitle());
        noteViewHolder.textNote.setText(noteItems.getText());
        //noteViewHolder.textDateTime.setText(noteItems.getDateTime());

//        //if a note has reminder show an icon on it's recycler view else don't
//        noteDB.open();
//        Cursor cursor = noteDB.getSelectedNote(noteItems.getId());
//        if (cursor.getLong(4)!=0) {
//            //Log.d("MY_TAG",cursor.getLong(4)+"\n"+cursor.getInt(5)+"\n"+cursor.getString(6));
//            //Note Has reminder
//            noteViewHolder.reminderIcon.setVisibility(View.VISIBLE);
//
//            //this change the notification bell icon
//            //when the reminder time has passed or equals to the current time
//            Calendar calendar = new GregorianCalendar();
//            calendar.setTimeInMillis(cursor.getLong(4));
//            Date currentDate = new Date();
//            if (cursor.getString(6).equals("Don't Repeat")) {
//                if (calendar.getTime().before(currentDate) || calendar.getTime().equals(currentDate)) {
//                    noteViewHolder.reminderIcon.setImageResource(R.drawable.ic_notifications_off);
//                }
//            }else {
//                noteViewHolder.reminderIcon.setImageResource(R.drawable.ic_notifications);
//            }
//        }else {
//            //Log.d("MY_TAG",cursor.getLong(4)+"\n"+cursor.getInt(5)+"\n"+cursor.getString(6));
//            noteViewHolder.reminderIcon.setVisibility(View.GONE);
//        }
//        noteDB.close();
//
//        noteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
//                boolean itemIsLongClicked=sharedPreferences.getBoolean(MainActivity.ITEM_IS_LONG_CLICKED,false);
//
//                if (itemIsLongClicked){
//                    if (selectedItemID.contains(noteItems)) {
//                        selectedItemID.remove(noteItems);
//                        unHighlightView(noteViewHolder);
//                    } else {
//                        selectedItemID.add(noteItems);
//                        highlightView(noteViewHolder);
//                    }
//                    onItemClickReceiver.onItemClick();
//                }else {
//                    Intent editNoteIntent = new Intent(context, EditNoteActivity.class);
//                    editNoteIntent.putExtra("NOTE_ID", noteList.get(position).getId());
//                    context.startActivity(editNoteIntent);
//                }
//            }
//        });
//
//        noteViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (selectedItemID.contains(noteItems)) {
//                    selectedItemID.remove(noteItems);
//                    unHighlightView(noteViewHolder);
//                } else {
//                    selectedItemID.add(noteItems);
//                    highlightView(noteViewHolder);
//                }
//                onItemLongClickReceiver.onItemLongClick();
//
//                return true;
//            }
//        });
//
//        //when all note is selected
//        if (selectedItemID.contains(noteItems))
//            highlightView(noteViewHolder);
//        else
//            unHighlightView(noteViewHolder);

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    private void highlightView(NoteViewHolder holder) {
        holder.itemView.setBackgroundColor(Color.parseColor("#0089ff"));
        holder.textTitle.setTextColor(Color.WHITE);
        holder.textNote.setTextColor(Color.WHITE);
        holder.textDateTime.setTextColor(Color.WHITE);
        holder.reminderIcon.setColorFilter(Color.WHITE);
    }

    private void unHighlightView(NoteViewHolder holder) {
        holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        holder.textTitle.setTextColor(Color.parseColor("#808080"));
        holder.textNote.setTextColor(Color.parseColor("#808080"));
        holder.textDateTime.setTextColor(Color.parseColor("#808080"));
        holder.reminderIcon.setColorFilter(Color.parseColor("#0089ff"));
    }

    public Note getNoteAt(int position) {
        return noteList.get(position);
    }

    public void setActionModeReceiver(OnItemLongClick longClickReceiver,OnItemClick clickReceiver) {
        this.onItemLongClickReceiver = longClickReceiver;
        this.onItemClickReceiver = clickReceiver;
    }

    public void selectAllNote() {
        selectedItemID.clear();
        selectedItemID.addAll(noteList);
        notifyDataSetChanged();
    }

    public void unSelectAllNote() {
        selectedItemID.clear();
        notifyDataSetChanged();
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView textID, textTitle, textNote, textDateTime;
        private ImageView reminderIcon;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textID = itemView.findViewById(R.id.textID);
            textTitle = itemView.findViewById(R.id.textTitle);
            textNote = itemView.findViewById(R.id.textNote);
            textDateTime = itemView.findViewById(R.id.textDateTime);
            reminderIcon = itemView.findViewById(R.id.reminderIcon);
        }
    }

}
