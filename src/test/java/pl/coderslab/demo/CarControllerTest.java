package pl.coderslab.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.coderslab.demo.cars.Car;
import pl.coderslab.demo.cars.CarRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CarRepository carRepository;

//    @Test
//    public void shouldReturnDefaultMessage() throws Exception {
//        //given
//        Car expectedCar = new Car();
//        given(carRepository.findById(1L)).willReturn(Optional.of(expectedCar));
//
//        //when
//        MockHttpServletResponse response = this.mockMvc.perform(get("/api/cars/1")).andReturn().getResponse();
//        response.getContentAsString();
//
//        //then
//        assertThat(response.getStatus())
//                .isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString())
//                .isEqualTo(objectMapper.writeValueAsString(expectedCar));
//    }

    @Nested
    class GetOneCarTests {
        @Test
        void shouldReturn200GivenProperParameters() throws Exception {
            //given
            int id = 1;
            String url = "/api/cars/" + id;
            given(carRepository.findById(any())).willReturn(Optional.of(new Car()));

            //when
            MvcResult mvcResult = mockMvc.perform(get(url)).andReturn();

            //then
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        }

        @Test
        void shouldReturnCarReturnedByRepository() throws Exception {
            //given
            long id = 1;
            String url = "/api/cars/" + id;

            Car expectedCar = new Car();
            expectedCar.setName("Skoda");

            given(carRepository.findById(id)).willReturn(Optional.of(expectedCar));

            //nie uzywac Object Mappera tylko doczytać o tym jak sprawdzać jsony z mock responsu
            String expectedJson = objectMapper.writeValueAsString(expectedCar);

            //when
            MvcResult mvcResult = mockMvc.perform(get(url)).andReturn();

            //then
            assertThat(mvcResult.getResponse().getContentAsString())
                    .isEqualTo(expectedJson);
        }
    }
}
