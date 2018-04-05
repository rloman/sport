package be.qnh.apps.sport.rest;

import be.qnh.apps.sport.domain.Sport;
import be.qnh.apps.sport.service.SportService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 */


@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SportEndpointTest {

    @InjectMocks
    private SportEndpoint sportEndpoint;

    @Mock
    private SportService sportService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.sportEndpoint).build();
    }

    @Test
    public void addSportTest() throws Exception {
        Sport sport = new Sport(3L);
        sport.setName("Volleybal");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sport);

        Mockito.when(this.sportService.insert(any(Sport.class))).thenReturn(sport);

        this.mockMvc.perform(post("/api/sports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(jsonPath("$.id", is(Long.valueOf(sport.getId()).intValue())))
                .andExpect(jsonPath("$.name", is(sport.getName())))
                .andExpect(jsonPath("$.mixed", is(sport.isMixed())))
                .andExpect(status().isCreated()
                );
    }

}
