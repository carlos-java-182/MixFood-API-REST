package com.mixfood.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "socialnetworks")
public class SocialNetwork implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSocialnetwork")
    private int id;
    @Column(nullable = false,length = 20)
    @Enumerated(EnumType.STRING)
    private Network network;

    @NotEmpty
    @Column(nullable = false)
    private String Link;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public enum Network
    {
        FACEBOOK,
        INSTAGRAM,
        YOUTUBE,
        TWITTER,
        LINKEDIN,
        PINTEREST
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

   ///



    public void setUser(User user) {
        this.user = user;
    }
}

