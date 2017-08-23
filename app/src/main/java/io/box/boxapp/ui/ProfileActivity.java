package io.box.boxapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.box.boxapp.BoxAppApplication;
import io.box.boxapp.R;
import io.box.boxapp.models.realm.User;
import io.realm.Realm;

public class ProfileActivity extends BaseActivity{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.email) TextView email;
    @BindView(R.id.info) EditText info;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        user = Realm.getDefaultInstance().where(User.class).equalTo("inApp", true).findFirst();

        if(user.getUserInfo()!=null)
             info.setText(user.getUserInfo());
        email.setText(user.getEmail());

        info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Realm.getDefaultInstance().executeTransaction(realm -> {
                    user.setUserInfo(info.getText().toString());
                    user.setTimestamp(System.currentTimeMillis());
                });
            }
        });

        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(e -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            BoxAppApplication.logout();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
