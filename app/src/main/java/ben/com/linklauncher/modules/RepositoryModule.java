package ben.com.linklauncher.modules;

import javax.inject.Singleton;

import ben.com.linklauncher.data.db.realm.RealmRepository;
import ben.com.linklauncher.data.db.realm.RealmRepositoryImpl;
import ben.com.linklauncher.data.model.LinkModel;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public static RealmRepository<LinkModel> providesRepository() {
        return new RealmRepositoryImpl();
    }
}
