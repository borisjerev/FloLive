package com.borisjerev.floLive;

import com.borisjerev.floLive.entity.ColorEntity;
import com.borisjerev.floLive.repository.ColorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ColorRepositoryTest {

    @Autowired
    private ColorRepository colorRepository;

    @Test
    public void return_right_color() {
        int red = 0;
        int green = 0;
        int blue = 0;
        int timestamp = 0;

        ColorEntity colorEntity = new ColorEntity(red, green, blue, timestamp);

        colorRepository.save(colorEntity);

        List<ColorEntity> colors = colorRepository.findByRedAndGreenAndBlue(red, green, blue);

        assertEquals(colors.size(), 1);

        ColorEntity response = colors.get(0);

        assertEquals(colorEntity.getRed(), response.getRed());
        assertEquals(colorEntity.getGreen(), response.getGreen());
        assertEquals(colorEntity.getBlue(), response.getBlue());
        assertEquals(colorEntity.getTimeStamp(), response.getTimeStamp());
    }
}
