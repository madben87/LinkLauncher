package ben.com.linklauncher.core;

import android.app.Application;

import ben.com.linklauncher.modules.ContextModule;
import ben.com.linklauncher.modules.RepositoryModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    public static String SHOW_LINK = "ben.com.linklauncher.show_link";

    private static App appInstance;
    private AppInjector appInjector;

    public static App getAppInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder().name("links.realm").build();

        Realm.setDefaultConfiguration(config);

        appInstance = this;

        appInjector = DaggerAppInjector
                .builder()
                .contextModule(new ContextModule(this))
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public static AppInjector getAppInjector() {
        return getAppInstance().appInjector;
    }
}
