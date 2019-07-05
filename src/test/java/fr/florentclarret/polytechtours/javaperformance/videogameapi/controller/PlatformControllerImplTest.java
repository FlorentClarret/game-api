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
public class PlatformControllerImplTest {

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
                .andExpect(jsonPath("_links.videogame", hasSize(2)))
                .andExpect(jsonPath("_links.videogame[0].href", is("http://localhost/api/v1.0/videogame/2")))
                .andExpect(jsonPath("_links.videogame[1].href", is("http://localhost/api/v1.0/videogame/3")));
    }

    @Test
    public void testGetUnknownPlatform() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/platform/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id [666] not found"));
    }

    @Test
    public void testGetAllPlatforms() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/platform/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.platformList", hasSize(1)))
                .andExpect(jsonPath("_embedded.platformList[0].name", is("platform1")))
                .andExpect(jsonPath("_embedded.platformList[0].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.platformList[0].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.platformList[0]._links.self.href", is("http://localhost/api/v1.0/platform/1")))
                .andExpect(jsonPath("_embedded.platformList[0]._links.all.href", is("http://localhost/api/v1.0/platform/")))
                .andExpect(jsonPath("_embedded.platformList[0]._links.videogame", hasSize(2)))
                .andExpect(jsonPath("_embedded.platformList[0]._links.videogame[0].href", is("http://localhost/api/v1.0/videogame/2")))
                .andExpect(jsonPath("_embedded.platformList[0]._links.videogame[1].href", is("http://localhost/api/v1.0/videogame/3")));
    }
}
