package ben.com.linklauncher.core;

import android.app.Application;

import ben.com.linklauncher.modules.ContextModule;
import ben.com.linklauncher.modules.PresenterModule;
import ben.com.linklauncher.modules.RepositoryModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    public static String SHOW_LINK = "ben.com.linklauncher.show_link";
    public static String SHOW_LINK_SUCCESS = "ben.com.linklauncher.show_link_success";
    public static String KEY_LINK = "link";

    private static App appInstance;
    private AppInjector appInjector;
    private ScreenInjector screenInjector;

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

        screenInjector = DaggerScreenInjector
                .builder()
                .presenterModule(new PresenterModule())
                .build();
    }

    public static AppInjector getAppInjector() {
        return getAppInstance().appInjector;
    }

    public static ScreenInjector getScreenInjector() {
        return getAppInstance().screenInjector;
    }
}
