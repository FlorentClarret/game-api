package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums;

import org.junit.Assert;
import org.junit.Test;

public class RelTypeTest {

    @Test
    public void testGetName() {
        Assert.assertEquals("platform", RelType.PLATFORM.getName());
        Assert.assertEquals("publisher", RelType.PUBLISHER.getName());
        Assert.assertEquals("videogame", RelType.VIDEO_GAME.getName());
    }
}
