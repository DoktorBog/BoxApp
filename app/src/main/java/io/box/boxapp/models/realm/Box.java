package io.box.boxapp.models.realm;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Box extends RealmObject{

    @PrimaryKey
    private String id;

    private int boxId;
    private String color;
}
