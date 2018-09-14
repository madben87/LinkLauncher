package ben.com.linklauncher.ui.history;

import java.util.List;

import ben.com.linklauncher.core.MVPView;
import ben.com.linklauncher.data.model.LinkModel;

public interface HistoryView extends MVPView {

    void updateListView(List<LinkModel> list);
}
