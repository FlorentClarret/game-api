package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
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
public class PublisherControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ObjectReader OBJECT_READER = OBJECT_MAPPER.readerFor(Publisher.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPublisherWithoutGames() throws Exception {
        Publisher publisher = OBJECT_READER.readValue(this.mockMvc.perform(get("/api/v1.0/publisher/2")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Assert.assertEquals(2, publisher.getId().intValue());
        Assert.assertEquals("publisher2", publisher.getName());
        Assert.assertNotNull(publisher.getCreateDate());
        Assert.assertNotNull(publisher.getUpdateDate());
        Assert.assertTrue(publisher.getVideoGameList().isEmpty());
    }

    @Test
    public void testGetPublisherWithGames() throws Exception {
        Publisher publisher = OBJECT_READER.readValue(this.mockMvc.perform(get("/api/v1.0/publisher/1")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Assert.assertEquals(1, publisher.getId().intValue());
        Assert.assertEquals("publisher1", publisher.getName());
        Assert.assertNotNull(publisher.getCreateDate());
        Assert.assertNotNull(publisher.getUpdateDate());
        Assert.assertEquals(1, publisher.getVideoGameList().size());
        Assert.assertEquals(2, publisher.getVideoGameList().get(0).getId().intValue());
    }

    @Test
    public void testGetUnknownPublisher() throws Exception {
        this.mockMvc.perform(get("/api/v1.0/publisher/666")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string("Publisher with id [666] not found"));
    }

    @Test
    public void testGetAllPublishers() throws Exception {
        final List<Publisher> publishers = OBJECT_MAPPER.readValue(this.mockMvc.perform(get("/api/v1.0/publisher/")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<List<Publisher>>() {
        });

        Assert.assertEquals(2, publishers.size());
        Assert.assertEquals(1, publishers.get(0).getId().intValue());
        Assert.assertEquals(2, publishers.get(1).getId().intValue());
    }
}
