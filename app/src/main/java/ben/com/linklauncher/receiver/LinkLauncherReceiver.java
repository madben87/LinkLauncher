package ben.com.linklauncher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import ben.com.linklauncher.core.App;

public class LinkLauncherReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {
            if (intent.getAction() != null && intent.getAction().equals(App.SHOW_LINK_SUCCESS)) {
                if (intent.getIntExtra("status", 0) == 1) {
                    Toast.makeText(context, "LINK IS OPEN", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
