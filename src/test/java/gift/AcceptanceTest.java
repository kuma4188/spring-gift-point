package gift;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void cors() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.options("/api/products")
                    .header(HttpHeaders.ORIGIN, "http://localhost:8080")
                    .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET")
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8080"))
            .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE,OPTIONS"))
            .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600"))
            .andDo(MockMvcResultHandlers.print());
    }
}