package io.box.boxapp.models.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User extends RealmObject{

    @PrimaryKey
    private String id;

    private boolean inApp;

    private String email;
    private String password;
    private String userInfo;
    private long timestamp;

    private RealmList<Box> boxes = new RealmList<>();
}
