package ben.com.linklauncher.core;

import ben.com.linklauncher.modules.PresenterModule;
import ben.com.linklauncher.ui.history.HistoryFragment;
import ben.com.linklauncher.ui.main.MainActivity;
import ben.com.linklauncher.ui.test.TestFragment;
import dagger.Component;

@ScreenScope
@Component(modules = {PresenterModule.class})
public interface ScreenInjector {

    void inject(MainActivity activity);
    void inject(TestFragment activity);
    void inject(HistoryFragment activity);
}
