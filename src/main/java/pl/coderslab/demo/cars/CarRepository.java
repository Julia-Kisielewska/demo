package pl.coderslab.demo.cars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select c from Car c where c.name like %?1%")
    List<Car> findByBrandContaining(String text);
}
