package com.borisjerev.floLive.repository;

import com.borisjerev.floLive.entity.ColorEntity;
import com.borisjerev.floLive.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    List<ColorEntity> findByRedAndGreenAndBlue(int red, int green, int blue);
}
