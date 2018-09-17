package ben.com.linklauncher.modules;

import ben.com.linklauncher.core.ScreenScope;
import ben.com.linklauncher.ui.history.HistoryPresenter;
import ben.com.linklauncher.ui.history.HistoryPresenterImpl;
import ben.com.linklauncher.ui.test.TestPresenter;
import ben.com.linklauncher.ui.test.TestPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @ScreenScope
    public static TestPresenter providesTestPresenter() {
        return new TestPresenterImpl();
    }

    @Provides
    @ScreenScope
    public static HistoryPresenter providesHistoryPresenter() {
        return new HistoryPresenterImpl();
    }
}
