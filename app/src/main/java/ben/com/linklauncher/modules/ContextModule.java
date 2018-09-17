package ben.com.linklauncher.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private static Context context;

    public ContextModule(Context context) {
        ContextModule.context = context;
    }

    @Provides
    @Singleton
    public static  Context providesContext() {
        return context;
    }
}
