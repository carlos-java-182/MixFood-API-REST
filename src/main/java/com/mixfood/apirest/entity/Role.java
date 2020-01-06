package com.mixfood.apirest.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRole")
    private int id;
    @Enumerated(EnumType.STRING)
    private Type type;

    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    public enum Type{
        USER,
        ADMIN
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
