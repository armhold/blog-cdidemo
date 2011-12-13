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
    private Sport sport;

    private String name;

    public Long getId()
    {
        return id;
    }

    public Sport getSport()
    {
        return sport;
    }

    public String getName()
    {
        return name;
    }

}
