package ben.com.linklauncher.ui.history.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import ben.com.linklauncher.R;

import ben.com.linklauncher.core.ItemClick;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {

    @BindView(R.id.historyItem)
    CardView historyItem;
    @BindView(R.id.linkView)
    TextView linkView;
    @BindView(R.id.dateLinkView)
    TextView dateLinkView;

    private ItemClick itemClick;

    public HistoryHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        historyItem.setPreventCornerOverlap(false);
        historyItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClick.onItemClick(view, this.getLayoutPosition());
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }
}
