package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
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
public class PlatformControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ObjectReader OBJECT_READER = OBJECT_MAPPER.readerFor(Platform.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPlatform() throws Exception {
        Platform platform = OBJECT_READER.readValue(this.mockMvc.perform(get("/api/v1.0/platform/1")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Assert.assertEquals(1, platform.getId().intValue());
        Assert.assertEquals("platform1", platform.getName());
        Assert.assertNotNull(platform.getCreateDate());
        Assert.assertNotNull(platform.getUpdateDate());
        Assert.assertEquals(2, platform.getVideoGameList().size());
        Assert.assertEquals(2, platform.getVideoGameList().get(0).getId().intValue());
        Assert.assertEquals(3, platform.getVideoGameList().get(1).getId().intValue());
    }

    @Test
    public void testGetUnknownPlatform() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/platform/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Platform with id [666] not found"));
    }

    @Test
    public void testGetAllPlatforms() throws Exception {
        final List<Platform> platforms = OBJECT_MAPPER.readValue(this.mockMvc.perform(get("/api/v1.0/platform/")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<List<Platform>>() {
        });

        Assert.assertEquals(1, platforms.size());
        Assert.assertEquals(1, platforms.get(0).getId().intValue());
    }
}
