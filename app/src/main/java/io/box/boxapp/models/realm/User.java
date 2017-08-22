package io.box.boxapp.models.realm;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User extends RealmObject{

    private String id;
    private String email;
    private String password;
    private String userInfo;
    private long timestamp;
}
