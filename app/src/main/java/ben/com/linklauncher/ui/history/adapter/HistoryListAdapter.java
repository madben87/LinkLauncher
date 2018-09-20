package ben.com.linklauncher.ui.history.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ben.com.linklauncher.R;
import ben.com.linklauncher.core.App;
import ben.com.linklauncher.core.ItemClick;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.data.model.Status;
import ben.com.linklauncher.ui.history.HistoryPresenter;
import ben.com.linklauncher.util.SortLinkFactory;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryHolder> {

    @Inject
    HistoryPresenter presenter;

    private List<LinkModel> linkModelList;
    private int defaultSortCriteria = 0;

    private static final Map<Integer, Integer> colorMap = new HashMap<>();

    static {
        colorMap.put(Status.ACTIVE.getValue(), R.color.linkActive);
        colorMap.put(Status.ERROR.getValue(), R.color.linkError);
        colorMap.put(Status.UNKNOWN.getValue(), R.color.linkUnknown);
    }

    public HistoryListAdapter() {
        linkModelList = new ArrayList<>();
        App.getScreenInjector().inject(this);
    }

    public void addItems(List<LinkModel> list) {
        this.linkModelList = list;
        Collections.sort(linkModelList, SortLinkFactory.getSort(defaultSortCriteria));
        notifyDataSetChanged();
    }

    public void sortItems() {

        if (++defaultSortCriteria < SortLinkFactory.getCriteriaCount()) {
            Collections.sort(linkModelList, SortLinkFactory.getSort(defaultSortCriteria));
        }else {
            defaultSortCriteria = 0;
            Collections.sort(linkModelList, SortLinkFactory.getSort(defaultSortCriteria));
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_tem, viewGroup, false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryHolder holder, int i) {

        Resources res = App.getAppInstance().getResources();

        final LinkModel model = linkModelList.get(i);

        if (model != null) {

            holder.linkView.setText(model.getLink());
            holder.dateLinkView.setText(model.getDate());
            holder.historyItem.setCardBackgroundColor(res.getColor(colorMap.get(model.getStatus())));

            holder.setOnItemClickListener(new ItemClick() {
                @Override
                public void onItemClick(View view, int position) {
                    presenter.sendLink(model);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return linkModelList.size();
    }
}
