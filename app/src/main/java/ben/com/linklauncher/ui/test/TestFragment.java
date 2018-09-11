package ben.com.linklauncher.ui.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ben.com.linklauncher.R;
import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.data.model.RealmStatus;
import ben.com.linklauncher.data.model.Status;
import ben.com.linklauncher.util.DateUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TestFragment extends Fragment {

    @BindView(R.id.inputLinkLayout)
    TextInputLayout inputLinkLayout;
    @BindView(R.id.inputLinkText)
    TextInputEditText inputLinkText;

    private Unbinder unbinder;

    public TestFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.openLinkBtn)
    public void click() {
        Intent intent = new Intent(App.SHOW_LINK);
        LinkModel model = new LinkModel();
        model.setLink(inputLinkText.getText().toString());
        model.setDate(DateUtil.getCurrentDate());
        model.setId(model.hashCode());
        model.setStatus(Status.UNKNOWN.getValue());
        intent.putExtra("model", model);
        startActivity(intent);
    }
}
