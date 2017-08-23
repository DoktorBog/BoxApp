package io.box.boxapp.ui;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.box.boxapp.BoxAppApplication;
import io.box.boxapp.R;
import io.box.boxapp.adapters.ColorListAdapter;
import io.box.boxapp.adapters.ItemClickSupport;
import io.box.boxapp.dageer.components.DaggerMainActivityComponent;
import io.box.boxapp.dageer.components.MainActivityComponent;
import io.box.boxapp.models.realm.Box;
import io.box.boxapp.models.realm.User;
import io.box.boxapp.presenter.MainActivityPresenter;
import io.box.boxapp.view.MainView;
import io.realm.Realm;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.box_button) AppCompatButton boxButton;
    @BindView(R.id.text_size) TextView textSize;
    @BindView(R.id.r_small) RadioButton rSmall;
    @BindView(R.id.r_medium) RadioButton rMedium;
    @BindView(R.id.r_large) RadioButton rLarge;
    @BindView(R.id.size_group) RadioGroup sizeGroup;
    @BindView(R.id.size) RelativeLayout size;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.next) Button next;
    @BindView(R.id.pick_color) TextView pickColor;

    private MainActivityComponent mainActivityComponent;
    private User user;

    @Inject MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        user = Realm.getDefaultInstance().where(User.class).equalTo("inApp", true).findFirst();
        if (user == null) launch(this, SignActivity.class);

        mainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(BoxAppApplication.getInstance().getAppComponent())
                .build();
        mainActivityComponent.inject(this);

        mainActivityPresenter.onCreate();
        mainActivityPresenter.attachView(this);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        user = Realm.getDefaultInstance().where(User.class).equalTo("inApp", true).findFirst();
        if (user == null) launch(this, SignActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            launchWithOutFinish(this, ProfileActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError() {

    }

    Box box = null;

    @OnClick({R.id.box_button, R.id.next})
    public void onViewClicked(View view) {
        List<String> colorsList;

        switch (view.getId()) {
            case R.id.box_button:
                boxButton.setVisibility(View.GONE);
                size.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);

                box = new Box();
                box.setId(UUID.randomUUID().toString());
                break;
            case R.id.next:

                try {
                    String[] myResArray;
                    switch (sizeGroup.getCheckedRadioButtonId()) {
                        case R.id.r_small:
                            myResArray = getResources().getStringArray(R.array.small_colors);
                            box.setBoxId(0);
                            break;
                        case R.id.r_medium:
                            myResArray = getResources().getStringArray(R.array.medium_colors);
                            box.setBoxId(1);
                            break;
                        case R.id.r_large:
                            myResArray = getResources().getStringArray(R.array.large_colors);
                            box.setBoxId(2);
                            break;
                        default:
                            myResArray = getResources().getStringArray(R.array.small_colors);
                            break;
                    }
                    colorsList = Arrays.asList(myResArray);

                    size.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);

                    pickColor.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    ColorListAdapter mAdapterTag = new ColorListAdapter(this, colorsList);
                    recyclerView.setAdapter(mAdapterTag);

                    Box finalBox = box;
                    ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
                        finalBox.setColor(colorsList.get(position));

                        /*
                         * mock of the network function that send box subscription
                         */
                        // mainActivityPresenter.fetch(finalBox.getBoxId(), finalBox.getColor());

                        imitateBoxRequest(finalBox);

                        size.setVisibility(View.GONE);
                        next.setVisibility(View.GONE);
                        pickColor.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);

                        boxButton.setVisibility(View.VISIBLE);
                    });
                } catch (Exception e){
                    Log.e("error", e.toString());
                }
        }
    }

    public void imitateBoxRequest(Box finalBox){
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(finalBox);
            user.getBoxes().add(finalBox);
        });
    }
}
