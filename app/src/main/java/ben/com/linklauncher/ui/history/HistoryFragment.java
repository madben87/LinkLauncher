package ben.com.linklauncher.ui.history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import ben.com.linklauncher.R;
import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.ui.history.adapter.HistoryListAdapter;
import ben.com.linklauncher.util.MessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends Fragment implements HistoryView {

    @BindView(R.id.historyListView)
    RecyclerView historyListView;

    @Inject
    HistoryPresenter presenter;

    private Unbinder unbinder;
    private HistoryListAdapter adapter;

    public HistoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        unbinder = ButterKnife.bind(this, view);

        App.getScreenInjector().inject(this);

        presenter.attachView(this);

        adapter = new HistoryListAdapter();

        historyListView.setHasFixedSize(true);
        historyListView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyListView.setAdapter(adapter);

        presenter.updateList();

        EventBus.getDefault().register(this);

        return view;
    }

    @Override
    public void updateListView(List<LinkModel> list) {
        adapter.addItems(list);
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(this.getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.msg == MessageEvent.UPDATED_DB) {
            presenter.updateList();
        }else if (event.msg == MessageEvent.UPDATED_VIEW) {
            presenter.updateList();
        }else if (event.msg == MessageEvent.SORT_HISTORY) {
            adapter.sortItems();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
        presenter.detachView();
        EventBus.getDefault().unregister(this);
    }
}
