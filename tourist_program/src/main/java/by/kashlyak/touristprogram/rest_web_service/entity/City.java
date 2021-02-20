package by.kashlyak.touristprogram.rest_web_service.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;
}
