package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideoGameControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ObjectReader OBJECT_READER = OBJECT_MAPPER.readerFor(VideoGame.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetGameAlone() throws Exception {
        final VideoGame videoGame = OBJECT_READER.readValue(this.mockMvc.perform(get("/api/v1.0/videogame/1")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Assert.assertEquals(1, videoGame.getId().intValue());
        Assert.assertEquals("game1", videoGame.getName());
        Assert.assertEquals("9.3", videoGame.getCriticScore());
        Assert.assertEquals("2", videoGame.getUserScore());
        Assert.assertEquals("3.12", videoGame.getGlobalSales());
        Assert.assertNotNull(videoGame.getCreateDate());
        Assert.assertNotNull(videoGame.getUpdateDate());
        Assert.assertNull(videoGame.getPlatform());
        Assert.assertNull(videoGame.getPublisher());
    }

    @Test
    public void testGetGame() throws Exception {
        final VideoGame videoGame = OBJECT_READER.readValue(this.mockMvc.perform(get("/api/v1.0/videogame/2")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Assert.assertEquals(2, videoGame.getId().intValue());
        Assert.assertEquals("game2", videoGame.getName());
        Assert.assertEquals("2", videoGame.getCriticScore());
        Assert.assertEquals("8", videoGame.getUserScore());
        Assert.assertEquals("6.12", videoGame.getGlobalSales());
        Assert.assertNotNull(videoGame.getCreateDate());
        Assert.assertNotNull(videoGame.getUpdateDate());
        Assert.assertEquals(1, videoGame.getPublisher().getId().intValue());
        Assert.assertEquals(1, videoGame.getPlatform().getId().intValue());
    }

    @Test
    public void testGetUnknownGame() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/videogame/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Video game with id [666] not found"));
    }

    @Test
    public void testGetAllGames() throws Exception {
        final List<VideoGame> videoGames = OBJECT_MAPPER.readValue(this.mockMvc.perform(get("/api/v1.0/videogame/")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<List<VideoGame>>() {
        });

        Assert.assertEquals(3, videoGames.size());
        Assert.assertEquals(1, videoGames.get(0).getId().intValue());
        Assert.assertEquals(2, videoGames.get(1).getId().intValue());
        Assert.assertEquals(3, videoGames.get(2).getId().intValue());
    }
}
