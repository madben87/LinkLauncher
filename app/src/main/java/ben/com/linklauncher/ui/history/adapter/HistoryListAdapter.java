package ben.com.linklauncher.ui.history.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ben.com.linklauncher.R;

import java.util.List;

import ben.com.linklauncher.core.ItemClick;
import ben.com.linklauncher.data.model.LinkModel;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryHolder> {

    private List<LinkModel> linkModelList;

    public void addItems(List<LinkModel> list) {
        this.linkModelList = list;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_tem, viewGroup, false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int i) {

        LinkModel model = linkModelList.get(i);

        if (model != null) {

            holder.linkView.setText(model.getLink());
            holder.dateLinkView.setText(model.getDate());
            holder.historyItem.setCardBackgroundColor(model.getStatus());

            holder.setOnItemClickListener(new ItemClick() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return linkModelList.size();
    }
}
