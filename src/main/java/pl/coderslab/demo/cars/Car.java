package pl.coderslab.demo.cars;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.coderslab.demo.BaseEntity;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "car")
public class Car extends BaseEntity {
    @Column(name = "name")
    private String name;
}
