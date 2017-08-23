package io.box.boxapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.box.boxapp.R;
import io.box.boxapp.models.realm.User;
import io.realm.Realm;

public class MainActivity extends BaseActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = Realm.getDefaultInstance().where(User.class).equalTo("inApp", true).findFirst();
        if(user == null) launch(this, SignActivity.class);

    }
}
