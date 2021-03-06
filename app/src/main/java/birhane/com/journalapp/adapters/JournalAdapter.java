package birhane.com.journalapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import birhane.com.journalapp.R;
import birhane.com.journalapp.data.model.Journal;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.EntryViewHolder> {
    private ItemClickListener mOnItemClickListener;
    private List<Journal> mJournalEntries=new ArrayList<>();

    public JournalAdapter(ItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setJournals(List<Journal> journalEntries) {
        this.mJournalEntries = journalEntries;
        notifyDataSetChanged();
    }

    public Journal getJournal(int position) {
        return mJournalEntries.get(position);
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
        holder.mTextViewEntryContent.setText(mJournalEntries.get(position).getContent());
        holder.mTextViewEntryTitle.setText(mJournalEntries.get(position).getTitle());

        holder.getmTextViewEntryDate.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(mJournalEntries.get(position).getCreatedDate()));
    }

    @Override
    public int getItemCount() {
        return mJournalEntries.size();
    }

    public interface ItemClickListener {
        void onItemClickListener(int clickedItemIndex);
    }

    class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewEntryContent, mTextViewEntryTitle,getmTextViewEntryDate;

        EntryViewHolder(View itemView) {
            super(itemView);
            mTextViewEntryContent = itemView.findViewById(R.id.text_view_entry_content);
            mTextViewEntryTitle = itemView.findViewById(R.id.text_view_entry_title);
            getmTextViewEntryDate=itemView.findViewById(R.id.text_view_date);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnItemClickListener.onItemClickListener(clickedPosition);
        }
    }
}
