package palettea.api.presentation.controller.paint;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostPaintControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  void test() throws Exception {
    mockMvc
      .perform(MockMvcRequestBuilders
        .post("/api/paint")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
        {
          "name": "test name",
          "manufacturerId": "852ccffc-c1ac-4f9b-b8ef-8c8d14ce3645",
          "rgb": {
            "r": 200,
            "g": 100,
            "b": 50
          },
          "coatingType": "LACQUER"
        }
        """)
      )
      .andExpect(status().isOk());
  }

  @Test
  void error() throws Exception {
    mockMvc
      .perform(MockMvcRequestBuilders
        .post("/api/paint")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
        {
          "name": "test name",
          "manufacturerId": "error id",
          "rgb": {
            "r": 256,
            "g": 0,
            "b": -1
          },
          "coatingType": "LACQUER"
        }
        """)
      )
      .andExpect(status().isBadRequest())
      .andExpect(content().string("error id,256,-1"));
  }
}