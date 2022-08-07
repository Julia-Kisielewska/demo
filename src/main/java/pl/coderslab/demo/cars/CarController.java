package pl.coderslab.demo.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.demo.exceptions.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @GetMapping("/{id}")
    public Car getOne(@PathVariable("id") Long id) {
        return carRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

//    @GetMapping("/search")
//    public List<Car> searchByText(@RequestParam("query") String query) {
//        return carRepository.findByBrandContaining(query);
//    }
}
