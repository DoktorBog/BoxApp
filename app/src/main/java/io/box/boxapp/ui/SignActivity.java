package io.box.boxapp.ui;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.box.boxapp.BoxAppApplication;
import io.box.boxapp.R;
import io.box.boxapp.dageer.components.DaggerSignActivityComponent;
import io.box.boxapp.dageer.components.SignActivityComponent;
import io.box.boxapp.presenter.SignPresenter;
import io.box.boxapp.view.SignView;

public class SignActivity extends BaseActivity implements SignView{

    @BindView(R.id.email) MaterialEditText emailEditText;
    @BindView(R.id.password) MaterialEditText passwordEditText;
    @BindView(R.id.login) AppCompatButton login;
    @BindView(R.id.sign_action) TextView signAction;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private SignActivityComponent signActivityComponent;
    private boolean stateSignUp = false;

    @Inject SignPresenter signPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        initializeToolbar();

        signActivityComponent = DaggerSignActivityComponent.builder()
                .appComponent(BoxAppApplication.getInstance().getAppComponent())
                .build();
        signActivityComponent.inject(this);

        signPresenter.onCreate();
        signPresenter.attachView(this);
    }

    public void initializeToolbar() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    @Override
    public void showContent() {
        launch(this, MainActivity.class);
    }

    @Override
    public void showLoading() {
        // showLoading
    }

    @Override
    public void showError() {
        // showError
        if(!stateSignUp) {
            emailEditText.setError(getString(R.string.no_user));
            requestFocus(emailEditText);
        }
    }

    @OnClick({R.id.login, R.id.sign_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                validate();
                break;
            case R.id.sign_action:
                singAction();
                break;
        }
    }

    private void validate(){

        if(!validateEmail())
            return;

        if(!validatePassword())
            return;

        /*
         * mock of the network function that validates the new user and returns a valid user
         */
        // signPresenter.fetch(emailEditText.getText().toString(), passwordEditText.getText().toString(), stateSignUp);
        signPresenter.imitateSignAction(emailEditText.getText().toString(), passwordEditText.getText().toString(), stateSignUp);
    }

    private boolean validateEmail() {
        String email = emailEditText.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            emailEditText.setError(getString(R.string.error_validate_email));
            requestFocus(emailEditText);
            return false;
        } else return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword() {

        if(stateSignUp) {
            String password = passwordEditText.getText().toString();
            if (password.isEmpty()) {
                passwordEditText.setError(getString(R.string.eror_write_pass));
                requestFocus(passwordEditText);
                return false;
            }
            if (passwordEditText.getText().toString().length() < 5) {
                passwordEditText.setError(getString(R.string.password_chars_error));
                requestFocus(passwordEditText);
                return false;
            }

            if (passwordEditText.getText().toString().length() > 15) {
                passwordEditText.setError(getString(R.string.password_short_error));
                requestFocus(passwordEditText);
                return false;
            }
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void singAction(){
        if(!stateSignUp){
            signAction.setText(getString(R.string.sign_in));
            login.setText(getString(R.string.sign_up));
        } else {
            login.setText(getString(R.string.sign_in));
            signAction.setText(getString(R.string.sign_up));
        }
        stateSignUp = !stateSignUp;

        emailEditText.setText("");
        passwordEditText.setText("");
    }
}
