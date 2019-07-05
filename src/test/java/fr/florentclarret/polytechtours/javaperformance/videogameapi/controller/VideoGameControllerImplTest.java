package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideoGameControllerImplTest {

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
                .andExpect(jsonPath("_links.all.href", is("http://localhost/api/v1.0/videogame/")));
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
                .andExpect(jsonPath("_links.publisher.href", is("http://localhost/api/v1.0/publisher/1")))
                .andExpect(jsonPath("_links.platform.href", is("http://localhost/api/v1.0/platform/1")));
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
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.publisher").doesNotExist())
                .andExpect(jsonPath("_embedded.videoGameList[0]._links.publisher").doesNotExist())
                .andExpect(jsonPath("_embedded.videoGameList[1].name", is("game2")))
                .andExpect(jsonPath("_embedded.videoGameList[1].year", is(2012)))
                .andExpect(jsonPath("_embedded.videoGameList[1].criticScore", is("2")))
                .andExpect(jsonPath("_embedded.videoGameList[1].userScore", is("8")))
                .andExpect(jsonPath("_embedded.videoGameList[1].globalSales", is("6.12")))
                .andExpect(jsonPath("_embedded.videoGameList[1].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[1].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.self.href", is("http://localhost/api/v1.0/videogame/2")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.publisher.href", is("http://localhost/api/v1.0/publisher/1")))
                .andExpect(jsonPath("_embedded.videoGameList[1]._links.platform.href", is("http://localhost/api/v1.0/platform/1")))
                .andExpect(jsonPath("_embedded.videoGameList[2].name", is("game3")))
                .andExpect(jsonPath("_embedded.videoGameList[2].year", is(1950)))
                .andExpect(jsonPath("_embedded.videoGameList[2].criticScore", is("5")))
                .andExpect(jsonPath("_embedded.videoGameList[2].userScore", is("5")))
                .andExpect(jsonPath("_embedded.videoGameList[2].globalSales", is("4")))
                .andExpect(jsonPath("_embedded.videoGameList[2].updateDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[2].createDate").isNotEmpty())
                .andExpect(jsonPath("_embedded.videoGameList[2]._links.self.href", is("http://localhost/api/v1.0/videogame/3")))
                .andExpect(jsonPath("_embedded.videoGameList[2]._links.all.href", is("http://localhost/api/v1.0/videogame/")))
                .andExpect(jsonPath("_embedded.videoGameList[2]._links.platform.href", is("http://localhost/api/v1.0/platform/1")))
                .andExpect(jsonPath("_embedded.videoGameList[2]._links.publisher").doesNotExist())
                .andExpect(jsonPath("_links.self.href", is("http://localhost/api/v1.0/videogame/")));
    }
}
