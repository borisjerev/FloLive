package com.borisjerev.floLive.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="COLORS")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;
    @Column(name="RED")
    private Integer red;
    @Column(name="GREEN")
    private Integer green;
    @Column(name="BLUE")
    private Integer blue;
    @Column(name="TIMESTAMP")
    private Long timeStamp;

    public ColorEntity() {}

    public ColorEntity(int red, int  green, int blue, long timeStamp) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public Integer getGreen() {
        return green;
    }

    public void setGreen(Integer green) {
        this.green = green;
    }

    public Integer getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
