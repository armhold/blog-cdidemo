package net.ftlines.blog.cdidemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Player
{
    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    private Sport home;

    private String name;

    public Long getId()
    {
        return id;
    }

    public Sport getHome()
    {
        return home;
    }

    public String getName()
    {
        return name;
    }

}
