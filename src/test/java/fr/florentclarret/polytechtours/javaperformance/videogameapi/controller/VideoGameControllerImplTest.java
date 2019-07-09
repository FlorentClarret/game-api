package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
public class VideoGameControllerImplTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetGameAlone() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("game1")))
                .andExpect(jsonPath("year", is(2019)))
                .andExpect(jsonPath("criticScore", is("9.3")))
                .andExpect(jsonPath("userScore", is("2")))
                .andExpect(jsonPath("globalSales", is("3.12")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/1")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_links.publisher.href", is("http://localhost/api/v1.0/videogame/1/publisher")))
                .andExpect(jsonPath("_links.platform.href", is("http://localhost/api/v1.0/videogame/1/platform")));
    }

    @Test
    public void testGetGame() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("game2")))
                .andExpect(jsonPath("year", is(2012)))
                .andExpect(jsonPath("criticScore", is("2")))
                .andExpect(jsonPath("userScore", is("8")))
                .andExpect(jsonPath("globalSales", is("6.12")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/2")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_links.publisher.href", is("http://localhost/api/v1.0/videogame/2/publisher")))
                .andExpect(jsonPath("_links.platform.href", is("http://localhost/api/v1.0/videogame/2/platform")));
    }

    @Test
    public void testGetUnknownGame() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    public void testGetAllGames() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.videoGameList", hasSize(3)))
                .andExpect(jsonPath("_embedded.videoGameList[0].name", is("game1")))
                .andExpect(jsonPath("_embedded.videoGameList[0].year", is(2019)))
                .andExpect(jsonPath("_embedded.videoGameList[0].criticScore", is("9.3")))
                .andExpect(jsonPath("_embedded.videoGameList[0].userScore", is("2")))
                .andExpect(jsonPath("_embedded.videoGameList[0].globalSales", is("3.12")))
                .andExpect(jsonPath("_embedded.videoGameList[0].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[0].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.self.href", is("http://localhost/api/v1.0/videogame/1")))
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.publisher.href", is("http://localhost/api/v1.0/videogame/1/publisher")))
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.platform.href", is("http://localhost/api/v1.0/videogame/1/platform")))
                .andExpect(jsonPath("_embedded.videoGameList[1].name", is("game2")))
                .andExpect(jsonPath("_embedded.videoGameList[1].year", is(2012)))
                .andExpect(jsonPath("_embedded.videoGameList[1].criticScore", is("2")))
                .andExpect(jsonPath("_embedded.videoGameList[1].userScore", is("8")))
                .andExpect(jsonPath("_embedded.videoGameList[1].globalSales", is("6.12")))
                .andExpect(jsonPath("_embedded.videoGameList[1].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[1].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.self.href", is("http://localhost/api/v1.0/videogame/2")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.publisher.href", is("http://localhost/api/v1.0/videogame/2/publisher")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.platform.href", is("http://localhost/api/v1.0/videogame/2/platform")))
                .andExpect(jsonPath("_embedded.videoGameList[2].name", is("game3")))
                .andExpect(jsonPath("_embedded.videoGameList[2].year", is(1950)))
                .andExpect(jsonPath("_embedded.videoGameList[2].criticScore", is("5")))
                .andExpect(jsonPath("_embedded.videoGameList[2].userScore", is("5")))
                .andExpect(jsonPath("_embedded.videoGameList[2].globalSales", is("4")))
                .andExpect(jsonPath("_embedded.videoGameList[2].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[2].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[2]._links.self.href", is("http://localhost/api/v1.0/videogame/3")))
                .andExpect(jsonPath("_embedded.videoGameList[2]._links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_embedded.videoGameList[2]._links.platform.href", is("http://localhost/api/v1.0/videogame/3/platform")))
                .andExpect(jsonPath("_embedded.videoGameList[2]._links.publisher.href", is("http://localhost/api/v1.0/videogame/3/publisher")))
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/")));
    }

    @Test
    public void testPostEntityAlreadyExists() throws Exception {
        final VideoGame videoGame = new VideoGame();
        videoGame.setName("game1");
        this.mockMvc.perform(post("/api/v1.0/videogame/").content(OBJECT_MAPPER.writeValueAsString(videoGame)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isConflict())
                .andExpect(content().string("The entity with name [game1] already exists"));
    }

    @Test
    @DirtiesContext
    public void testPost() throws Exception {
        final VideoGame videoGame = new VideoGame();
        videoGame.setName("game666");
        videoGame.setYear(2012);
        videoGame.setCriticScore("12");
        videoGame.setUserScore("13");
        videoGame.setGlobalSales("14");
        final MvcResult mvcResult = this.mockMvc.perform(post("/api/v1.0/videogame/").content(OBJECT_MAPPER.writeValueAsString(videoGame)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("name", is("game666")))
                .andExpect(jsonPath("year", is(2012)))
                .andExpect(jsonPath("criticScore", is("12")))
                .andExpect(jsonPath("userScore", is("13")))
                .andExpect(jsonPath("globalSales", is("14")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/4")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_links.publisher.href", is("http://localhost/api/v1.0/videogame/4/publisher")))
                .andExpect(jsonPath("_links.platform.href", is("http://localhost/api/v1.0/videogame/4/platform"))).andReturn();
        Assert.assertEquals("http://localhost/api/v1.0/videogame/4", mvcResult.getResponse().getHeader("Location"));
        Assert.assertEquals("http://localhost/api/v1.0/videogame/4", mvcResult.getResponse().getRedirectedUrl());
    }

    @Test
    public void testPutEntityNotFound() throws Exception {
        final VideoGame videoGame = new VideoGame();
        videoGame.setName("game1");
        this.mockMvc.perform(put("/api/v1.0/platform/666").content(OBJECT_MAPPER.writeValueAsString(videoGame)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testPut() throws Exception {
        final VideoGame videoGame = new VideoGame();
        videoGame.setName("game666");
        videoGame.setYear(2012);
        videoGame.setCriticScore("12");
        videoGame.setUserScore("13");
        videoGame.setGlobalSales("14");
        final MvcResult mvcResult = this.mockMvc.perform(put("/api/v1.0/videogame/1").content(OBJECT_MAPPER.writeValueAsString(videoGame)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("name", is("game666")))
                .andExpect(jsonPath("year", is(2012)))
                .andExpect(jsonPath("criticScore", is("12")))
                .andExpect(jsonPath("userScore", is("13")))
                .andExpect(jsonPath("globalSales", is("14")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/1")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_links.publisher.href", is("http://localhost/api/v1.0/videogame/1/publisher")))
                .andExpect(jsonPath("_links.platform.href", is("http://localhost/api/v1.0/videogame/1/platform"))).andReturn();
        Assert.assertEquals("http://localhost/api/v1.0/videogame/1", mvcResult.getResponse().getHeader("Location"));
        Assert.assertEquals("http://localhost/api/v1.0/videogame/1", mvcResult.getResponse().getRedirectedUrl());
    }

    @Test
    public void testDeleteEntityNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/videogame/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/videogame/3")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void testGetPublisherNotFound() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/1/publisher")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("No publisher found for game with id [1]"));
    }

    @Test
    public void testGetPublisher() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/2/publisher")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("publisher1")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/2/publisher")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/publisher/1/videogame")));
    }

    @Test
    public void testGetPlatformNotFound() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/1/platform")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("No platform found for game with id [1]"));
    }

    @Test
    public void testGetPlatform() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/2/platform")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("platform1")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/2/platform")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/platform/1/videogame")));
    }

    @Test
    public void testRemovePlatformGameNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/videogame/666/platform")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testRemovePlatform() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/videogame/2/platform")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void testRemovePublisherGameNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/videogame/666/publisher")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testRemovePublisher() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/videogame/2/publisher")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void testSetPlatformGameNotFound() throws Exception {
        this.mockMvc.perform(post("/api/v1.0/videogame/666/platform").content(OBJECT_MAPPER.writeValueAsString(new Platform())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testSetNewPlatform() throws Exception {
        final Platform platform = new Platform();
        platform.setName("newPlatform");
        this.mockMvc.perform(post("/api/v1.0/videogame/1/platform").content(OBJECT_MAPPER.writeValueAsString(platform)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("newPlatform")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/1/platform")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/platform/3/videogame")));
    }

    @Test
    @DirtiesContext
    public void testSetExistingPlatform() throws Exception {
        final Platform platform = new Platform();
        platform.setName("platform1");
        this.mockMvc.perform(post("/api/v1.0/videogame/1/platform").content(OBJECT_MAPPER.writeValueAsString(platform)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("platform1")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/1/platform")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/platform/1/videogame")));
    }

    @Test
    public void testSetPublisherGameNotFound() throws Exception {
        this.mockMvc.perform(post("/api/v1.0/videogame/666/publisher").content(OBJECT_MAPPER.writeValueAsString(new Publisher())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testSetNewPublisher() throws Exception {
        final Publisher publisher = new Publisher();
        publisher.setName("newPublisher");
        this.mockMvc.perform(post("/api/v1.0/videogame/1/publisher").content(OBJECT_MAPPER.writeValueAsString(publisher)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("newPublisher")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/1/publisher")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/publisher/3/videogame")));
    }

    @Test
    @DirtiesContext
    public void testSetExistingPublisher() throws Exception {
        final Publisher publisher = new Publisher();
        publisher.setName("publisher1");
        this.mockMvc.perform(post("/api/v1.0/videogame/1/publisher").content(OBJECT_MAPPER.writeValueAsString(publisher)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("publisher1")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/1/publisher")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/publisher/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/publisher/1/videogame")));
    }
}
