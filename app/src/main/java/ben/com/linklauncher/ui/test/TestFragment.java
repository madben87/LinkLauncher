package ben.com.linklauncher.ui.test;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import ben.com.linklauncher.R;
import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.data.model.Status;
import ben.com.linklauncher.util.DateUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TestFragment extends Fragment implements TestView {

    @BindView(R.id.inputLinkLayout)
    TextInputLayout inputLinkLayout;
    @BindView(R.id.inputLinkText)
    TextInputEditText inputLinkText;

    @Inject
    TestPresenter presenter;

    private Unbinder unbinder;

    public TestFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);

        unbinder = ButterKnife.bind(this, view);

        App.getScreenInjector().inject(this);

        return view;
    }

    @OnClick(R.id.openLinkBtn)
    public void click() {
        LinkModel model = getValidLink();
        if (model != null) {
            presenter.sendLink(model);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(this.getContext(), str, Toast.LENGTH_SHORT).show();
    }

    private LinkModel getValidLink() {
        if (!Objects.requireNonNull(inputLinkText.getText()).toString().isEmpty()) {
            LinkModel model = new LinkModel();
            model.setLink(inputLinkText.getText().toString());
            model.setDate(DateUtil.getCurrentDate());
            model.setId(model.hashCode());
            model.setStatus(Status.UNKNOWN.getValue());
            return model;
        }else {
            showMessage("Please, enter a link");
            return null;
        }
    }
}
