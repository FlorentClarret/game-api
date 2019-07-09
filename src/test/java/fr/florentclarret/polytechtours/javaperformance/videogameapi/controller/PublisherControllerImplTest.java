package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PublisherControllerImplTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPublisherWithoutGames() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("publisher2")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/2")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/publisher/2/videogame")));
    }

    @Test
    public void testGetPublisherWithGames() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("publisher1")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/1")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/publisher/1/videogame")));
    }

    @Test
    public void testGetUnknownPublisher() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    public void testGetAllPublishers() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.publisherList", hasSize(2)))
                .andExpect(jsonPath("_embedded.publisherList[0].name", is("publisher1")))
                .andExpect(jsonPath("_embedded.publisherList[0].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.publisherList[0].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.publisherList[0]._links.self.href", is("http://localhost/api/v1.0/publisher/1")))
                .andExpect(jsonPath("_embedded.publisherList[0]._links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_embedded.publisherList[0]._links.videogame.href", is("http://localhost/api/v1.0/publisher/1/videogame")))
                .andExpect(jsonPath("_embedded.publisherList[1].name", is("publisher2")))
                .andExpect(jsonPath("_embedded.publisherList[1].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.publisherList[1].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.publisherList[1]._links.self.href", is("http://localhost/api/v1.0/publisher/2")))
                .andExpect(jsonPath("_embedded.publisherList[1]._links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_embedded.publisherList[1]._links.videogame.href", is("http://localhost/api/v1.0/publisher/2/videogame")))
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/")));
    }

    @Test
    public void testPostEntityAlreadyExists() throws Exception {
        final Publisher publisher = new Publisher();
        publisher.setName("publisher1");
        this.mockMvc.perform(post("/api/v1.0/publisher/").content(OBJECT_MAPPER.writeValueAsString(publisher)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isConflict())
                .andExpect(content().string("The entity with name [publisher1] already exists"));
    }

    @Test
    @DirtiesContext
    public void testPost() throws Exception {
        final Publisher publisher = new Publisher();
        publisher.setName("publisher666");
        final MvcResult mvcResult = this.mockMvc.perform(post("/api/v1.0/publisher/").content(OBJECT_MAPPER.writeValueAsString(publisher)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("name", is("publisher666")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/3")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/publisher/3/videogame"))).andReturn();
        Assert.assertEquals("http://localhost/api/v1.0/publisher/3", mvcResult.getResponse().getHeader("Location"));
        Assert.assertEquals("http://localhost/api/v1.0/publisher/3", mvcResult.getResponse().getRedirectedUrl());
    }

    @Test
    public void testPutEntityNotFound() throws Exception {
        final Publisher publisher = new Publisher();
        publisher.setName("publisher666");
        this.mockMvc.perform(put("/api/v1.0/platform/666").content(OBJECT_MAPPER.writeValueAsString(publisher)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testPut() throws Exception {
        final Publisher publisher = new Publisher();
        publisher.setName("publisher666");
        final MvcResult mvcResult = this.mockMvc.perform(put("/api/v1.0/publisher/1").content(OBJECT_MAPPER.writeValueAsString(publisher)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("name", is("publisher666")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/1")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/publisher/1/videogame"))).andReturn();
        Assert.assertEquals("http://localhost/api/v1.0/publisher/1", mvcResult.getResponse().getHeader("Location"));
        Assert.assertEquals("http://localhost/api/v1.0/publisher/1", mvcResult.getResponse().getRedirectedUrl());
    }

    @Test
    public void testDeleteEntityNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/publisher/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/platform/2")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void testGetGamesNoGameFound() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/2/videogame")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("No game found for publisher with id [2]"));
    }

    @Test
    public void testGetGames() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/1/videogame")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.videoGameList", hasSize(1)))
                .andExpect(jsonPath("_embedded.videoGameList[0].name", is("game2")))
                .andExpect(jsonPath("_embedded.videoGameList[0].year", is(2012)))
                .andExpect(jsonPath("_embedded.videoGameList[0].criticScore", is("2")))
                .andExpect(jsonPath("_embedded.videoGameList[0].userScore", is("8")))
                .andExpect(jsonPath("_embedded.videoGameList[0].globalSales", is("6.12")))
                .andExpect(jsonPath("_embedded.videoGameList[0].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[0].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.self.href", is("http://localhost/api/v1.0/videogame/2")))
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.publisher.href", is("http://localhost/api/v1.0/videogame/2/publisher")))
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.platform.href", is("http://localhost/api/v1.0/videogame/2/platform")))
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/publisher/1/videogame")));
    }
}
