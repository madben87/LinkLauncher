package ben.com.linklauncher.core;

import javax.inject.Singleton;

import ben.com.linklauncher.modules.ContextModule;
import ben.com.linklauncher.modules.RepositoryModule;
import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class})
public interface AppInjector {

    //void inject(AddWordPresenterImpl presenter);
}
