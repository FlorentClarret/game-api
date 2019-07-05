package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
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

    @Test
    public void testGetPublisherWithoutGames() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("id", is(2)))
                .andExpect(jsonPath("name", is("publisher2")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/2")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame").doesNotExist());
    }

    @Test
    public void testGetPublisherWithGames() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("publisher1")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/1")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/videogame/2")));
    }

    @Test
    public void testGetUnknownPublisher() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Publisher with id [666] not found"));
    }

    @Test
    public void testGetAllPublishers() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.publisherList", hasSize(2)))
                .andExpect(jsonPath("_embedded.publisherList[0].id", is(1)))
                .andExpect(jsonPath("_embedded.publisherList[0].name", is("publisher1")))
                .andExpect(jsonPath("_embedded.publisherList[0].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.publisherList[0].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.publisherList[0]._links.self.href", is("http://localhost/api/v1.0/publisher/1")))
                .andExpect(jsonPath("_embedded.publisherList[0]._links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_embedded.publisherList[0]._links.videogame.href", is("http://localhost/api/v1.0/videogame/2")))
                .andExpect(jsonPath("_embedded.publisherList[1].id", is(2)))
                .andExpect(jsonPath("_embedded.publisherList[1].name", is("publisher2")))
                .andExpect(jsonPath("_embedded.publisherList[1].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.publisherList[1].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.publisherList[1]._links.self.href", is("http://localhost/api/v1.0/publisher/2")))
                .andExpect(jsonPath("_embedded.publisherList[1]._links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_embedded.publisherList[1]._links.videogame").doesNotExist())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/")));
    }
}
