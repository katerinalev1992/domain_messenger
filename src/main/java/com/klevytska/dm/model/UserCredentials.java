package com.klevytska.dm.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by katerynalevytska on 26.12.16.
 */
@XmlRootElement
public class UserCredentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserCredentials() {
        super();
    }
}
