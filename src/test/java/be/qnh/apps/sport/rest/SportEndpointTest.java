package be.qnh.apps.sport.rest;

import be.qnh.apps.sport.domain.Sport;
import be.qnh.apps.sport.service.SportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SportEndpointTest {

    @InjectMocks
    private SportEndpoint sportEndpoint;

    @Mock
    private SportService sportService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.sportEndpoint).build();
    }

    @Test
    public void addSportTest() throws Exception {
        Sport sport = new Sport();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sport);

        Mockito.when(this.sportService.insert(sport)).thenReturn(sport);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/sports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is("1")))
               .andExpect(MockMvcResultMatchers.status().isCreated() // good to fail which should be 201 (Created) #nice
        );
    }

}
