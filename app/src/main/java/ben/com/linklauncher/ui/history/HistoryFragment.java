package ben.com.linklauncher.ui.history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import ben.com.linklauncher.R;
import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.ui.history.adapter.HistoryListAdapter;
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

        return view;
    }

    @Override
    public void updateListView(List<LinkModel> list) {
        adapter.addItems(list);
    }

    @Override
    public void showMessage(String str) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
        presenter.detachView();
    }
}
