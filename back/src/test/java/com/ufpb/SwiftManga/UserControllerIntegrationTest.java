package com.ufpb.SwiftManga;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUserById() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyOTM4NTkzMCwiZXhwIjoxNzI5Mzg5NTMwfQ.dnDNZRSMdQkh7Aq-qMX1pN1jesFwIlPaZbJtegJi52o";
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/5")
               .header("Authorization", "Bearer " + token))  // Inclui o cabeçalho Authorization
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.mangas", hasSize(greaterThan(0))))
               .andExpect(jsonPath("$.hqs", hasSize(greaterThan(0))));
    }
}
