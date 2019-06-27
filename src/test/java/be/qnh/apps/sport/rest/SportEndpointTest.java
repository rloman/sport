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


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    public void testAddSport() throws Exception {
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

    @Test
    public void testFindOneById() throws Exception {
        Sport sport = new Sport(3L);
        sport.setName("Volleybal");

        Mockito.when(this.sportService.findById(3L)).thenReturn(sport);//any(Sport.class))).thenReturn(sport);

        this.mockMvc.perform(get("/api/sports/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")).andDo(print())
                .andExpect(jsonPath("$.id", is(Long.valueOf(sport.getId()).intValue())))
                .andExpect(jsonPath("$.name", is(sport.getName())))
                .andExpect(jsonPath("$.mixed", is(sport.isMixed())))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void testFindAll() throws Exception {

        // given
        Sport sport = new Sport(3L);
        sport.setName("Volleybal");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sport);

        List<Sport> sports = new ArrayList<>();
        sports.add(sport);


        Mockito.when(this.sportService.getAll()).thenReturn(sports);

        //when
        this.mockMvc.perform(get("/api/sports")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")).andDo(print())

                // then
                .andExpect(jsonPath("$[0].id", is(Long.valueOf(sport.getId()).intValue())))
                .andExpect(jsonPath("$[0].name", is(sport.getName())))
                .andExpect(jsonPath("$[0].mixed", is(sport.isMixed())))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void testUpdate() throws Exception {

        // given
        Sport sport = new Sport(3L);
        sport.setName("Volleybal");

        // and
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sport);

        // and
        Mockito.when(this.sportService.update(3L, sport)).thenReturn(sport);

        //when
        this.mockMvc.perform(put("/api/sports/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())

                // then
                .andExpect(jsonPath("$.id", is(Long.valueOf(sport.getId()).intValue())))
                .andExpect(jsonPath("$.name", is(sport.getName())))
                .andExpect(jsonPath("$.mixed", is(sport.isMixed())))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void testDelete() throws Exception {

        // given
        Sport sport = new Sport(3L);
        sport.setName("Volleybal");

        // and
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sport);

        // and
//        Mockito.when(this.sportService.deleteById(3L));

        //when
        this.mockMvc.perform(delete("/api/sports/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())

                // then
                .andExpect(status().isNoContent()
                );
    }

}
