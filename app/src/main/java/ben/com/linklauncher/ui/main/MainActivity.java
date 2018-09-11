package ben.com.linklauncher.ui.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ben.com.linklauncher.R;
import ben.com.linklauncher.ui.history.HistoryFragment;
import ben.com.linklauncher.ui.main.adapter.MainPagerAdapter;
import ben.com.linklauncher.ui.test.TestFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;

    private MainPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new TestFragment(), "Test");
        pagerAdapter.addFragment(new HistoryFragment(), "History");
        mainViewPager.setAdapter(pagerAdapter);
        //mainViewPager.setCurrentItem(App.getAppInstance().getMainPageState());
    }
}
