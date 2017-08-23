package io.box.boxapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public static void launch(Activity activity, Class<?> cls) {
        activity.startActivity(new Intent(activity, cls));
        activity.finish();
    }

    public static void launchWithOutFinish(Activity activity, Class<?> cls) {
        activity.startActivity(new Intent(activity, cls));
    }

    public static void launch(Intent intent, Activity activity){
        activity.startActivity(intent);
    }

    public static void launch(Bundle bundle, Activity activity, Class activityTo){
        Intent intent = new Intent(activity, activityTo);
        intent.putExtras(bundle);
        launch(intent, activity);
    }
}
