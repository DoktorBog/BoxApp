package io.box.boxapp.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponse implements Serializable{
    private boolean success;
}
