package com.klevytska.dm.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by katerynalevytska on 21.12.16.
 */
@XmlRootElement
@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "domain_name")
    private String domain_name;

    @Column(name = "nick_name")
    private String nick_name;

    @Column(name = "type")
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User message = (User) o;

        if (id != message.id) return false;
        if (domain_name != null ? !domain_name.equals(message.domain_name) : message.domain_name != null) return false;
        if (nick_name != null ? !nick_name.equals(message.nick_name) : message.nick_name != null) return false;
        return type == message.type;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (domain_name != null ? domain_name.hashCode() : 0);
        result = 31 * result + (nick_name != null ? nick_name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", domain_name='" + domain_name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", type=" + type +
                '}';
    }
}
