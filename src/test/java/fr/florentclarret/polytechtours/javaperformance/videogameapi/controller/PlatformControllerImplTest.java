package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
public class PlatformControllerImplTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPlatform() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/platform/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("platform1")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/platform/1")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/platform/1/videogame")));
    }

    @Test
    public void testGetUnknownPlatform() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/platform/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    public void testGetAllPlatforms() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/platform/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.platformList", hasSize(2)))
                .andExpect(jsonPath("_embedded.platformList[0].name", is("platform1")))
                .andExpect(jsonPath("_embedded.platformList[0].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.platformList[0].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.platformList[0]._links.self.href", is("http://localhost/api/v1.0/platform/1")))
                .andExpect(jsonPath("_embedded.platformList[0]._links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_embedded.platformList[0]._links.videogame.href", is("http://localhost/api/v1.0/platform/1/videogame")))
                .andExpect(jsonPath("_embedded.platformList[1].name", is("platform2")))
                .andExpect(jsonPath("_embedded.platformList[1].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.platformList[1].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.platformList[1]._links.self.href", is("http://localhost/api/v1.0/platform/2")))
                .andExpect(jsonPath("_embedded.platformList[1]._links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_embedded.platformList[1]._links.videogame.href", is("http://localhost/api/v1.0/platform/2/videogame")))
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/platform/")));
    }

    @Test
    public void testPostEntityAlreadyExists() throws Exception {
        final Platform platform = new Platform();
        platform.setName("platform1");
        this.mockMvc.perform(post("/api/v1.0/platform/").content(OBJECT_MAPPER.writeValueAsString(platform)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isConflict())
                .andExpect(content().string("The entity with name [platform1] already exists"));
    }

    @Test
    @DirtiesContext
    public void testPostEntity() throws Exception {
        final Platform platform = new Platform();
        platform.setName("platform666");
        this.mockMvc.perform(post("/api/v1.0/platform/").content(OBJECT_MAPPER.writeValueAsString(platform)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("platform666")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/platform/3")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/platform/3/videogame")));
    }

    @Test
    public void testPutEntityNotFound() throws Exception {
        final Platform platform = new Platform();
        platform.setName("platform666");
        this.mockMvc.perform(put("/api/v1.0/platform/666").content(OBJECT_MAPPER.writeValueAsString(platform)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testPut() throws Exception {
        final Platform platform = new Platform();
        platform.setName("platform666");
        this.mockMvc.perform(put("/api/v1.0/platform/1").content(OBJECT_MAPPER.writeValueAsString(platform)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("name", is("platform666")))
                .andExpect(jsonPath("updateDate").isNotEmpty())
                .andExpect(jsonPath("createDate").isNotEmpty())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/platform/1")))
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_links.videogame.href", is("http://localhost/api/v1.0/platform/1/videogame")));
    }

    @Test
    public void testDeleteEntityNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/platform/666")).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    @DirtiesContext
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete("/api/v1.0/platform/2")).andExpect(status().isNoContent());
    }

    @Test
    public void testGetGamesNoGameFound() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/platform/2/videogame")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("No game found for platform with id [2]"));
    }

    @Test
    public void testGetGames() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/platform/1/videogame")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.videoGameList", hasSize(2)))
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
                .andExpect(jsonPath("_embedded.videoGameList[1].name", is("game3")))
                .andExpect(jsonPath("_embedded.videoGameList[1].year", is(1950)))
                .andExpect(jsonPath("_embedded.videoGameList[1].criticScore", is("5")))
                .andExpect(jsonPath("_embedded.videoGameList[1].userScore", is("5")))
                .andExpect(jsonPath("_embedded.videoGameList[1].globalSales", is("4")))
                .andExpect(jsonPath("_embedded.videoGameList[1].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[1].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.self.href", is("http://localhost/api/v1.0/videogame/3")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.platform.href", is("http://localhost/api/v1.0/videogame/3/platform")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.publisher.href", is("http://localhost/api/v1.0/videogame/3/publisher")))
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/platform/1/videogame")));
    }
}
