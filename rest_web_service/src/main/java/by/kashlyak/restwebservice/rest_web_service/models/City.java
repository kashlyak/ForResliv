package by.kashlyak.restwebservice.rest_web_service.models;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;

    public City(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
