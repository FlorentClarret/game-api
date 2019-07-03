package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.PublisherRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherRepository publisherRepository;

    @Before
    public void before() {
        final Publisher publisher1 = new Publisher(1L, "pub1");
        final Publisher publisher2 = new Publisher(2L, "pub2");
        Mockito.when(publisherRepository.findAll()).thenReturn(List.of(publisher1, publisher2));
        Mockito.when(publisherRepository.findById(ArgumentMatchers.eq(1L))).thenReturn(Optional.of(publisher1));
        Mockito.when(publisherRepository.findById(ArgumentMatchers.eq(2L))).thenReturn(Optional.of(publisher2));
    }

    @Test
    public void testGetPublisher() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("pub1")))
                .andExpect(jsonPath("videoGameList", IsEmptyCollection.empty()))
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/platform/1")))
                .andExpect(jsonPath("_links.publisher.href", is("http://localhost/api/v1.0/platform/")));
    }

    @Test
    public void testGetUnknownPublisher() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Publisher with id [666] not found"));
    }

    @Test
    public void testGetNoPublisher() throws Exception {
        Mockito.reset(this.publisherRepository);
        this.mockMvc.perform(get("/api/v1.0/publisher/")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("No publisher found"));
    }
}
