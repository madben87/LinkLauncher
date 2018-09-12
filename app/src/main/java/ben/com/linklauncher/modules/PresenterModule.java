package ben.com.linklauncher.modules;

import ben.com.linklauncher.core.ScreenScope;
import ben.com.linklauncher.ui.test.TestPresenter;
import ben.com.linklauncher.ui.test.TestPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @ScreenScope
    TestPresenter providesTestPresenter() {
        return new TestPresenterImpl();
    }

    /*@Provides
    @ScreenScope
    AddWordPresenter providesAddPresenter() {
        return new AddWordPresenterImpl();
    }

    @Provides
    @ScreenScope
    AddVerbPresenter providesAddVerbPresenter() {
        return new AddVerbPresenterImpl();
    }*/
}
