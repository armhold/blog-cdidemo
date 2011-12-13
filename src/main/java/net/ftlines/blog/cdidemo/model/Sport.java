package net.ftlines.blog.cdidemo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sport implements Serializable
{
    @GeneratedValue
    @Id
    private Long id;

    @Basic(optional = false)
    private String name;

    @OneToMany(mappedBy="home", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Player> rooms = new ArrayList<Player>();

    public Sport()
    {
        System.out.println("***** Sport() constructor *****");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public List<Player> getRooms()
    {
        return rooms;
    }
}
