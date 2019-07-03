package fr.florentclarret.polytechtours.javaperformance.videogameapi;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.PlatformController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.PublisherController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.VideoGameController;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private PlatformController platformController;

    @Autowired
    private VideoGameController videoGameController;

    @Autowired
    private PublisherController publisherController;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(platformController);
        Assert.assertNotNull(videoGameController);
        Assert.assertNotNull(publisherController);
    }
}
