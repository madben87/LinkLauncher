package ben.com.linklauncher.ui.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import ben.com.linklauncher.R;
import ben.com.linklauncher.ui.history.HistoryFragment;
import ben.com.linklauncher.ui.main.adapter.MainPagerAdapter;
import ben.com.linklauncher.ui.test.TestFragment;
import ben.com.linklauncher.util.MessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

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

        //EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_VIEW));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            showMessage("SORT LIST");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_VIEW));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
