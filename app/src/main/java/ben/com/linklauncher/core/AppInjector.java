package ben.com.linklauncher.core;

import javax.inject.Singleton;

import ben.com.linklauncher.data.provider.LinkContentProvider;
import ben.com.linklauncher.modules.ContextModule;
import ben.com.linklauncher.modules.RepositoryModule;
import ben.com.linklauncher.ui.test.TestPresenterImpl;
import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class})
public interface AppInjector {

    //void inject(LinkContentProvider provider);
    void inject(TestPresenterImpl presenter);
}
