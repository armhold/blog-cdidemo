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

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Player> players = new ArrayList<Player>();

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

    public List<Player> getPlayers()
    {
        return players;
    }

    @Override
    public String toString()
    {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
