package Object;

import java.io.Serializable;

public class WineInfo implements Serializable {
    private double like_rate;
    private String id;
    private String name;
    private String year;
    private String raw_material;
    private String country;
    private String winery;
    private String temperature;
    private String time;
    private String volume;
    private String jiujd;
    private String colour;
    private String xiangqi;
    private String kougan;
    private String type;
    private byte[] picture1;
    private byte[] picture2;
    private byte[] picture3;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getLike_rate() {
        return like_rate;
    }

    public void setLike_rate(double like_rate) {
        this.like_rate = like_rate;
    }
    public String getId() {
        return id;
    }

    public void setId(String key) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRaw_material() {
        return raw_material;
    }

    public void setRaw_material(String raw_material) {
        this.raw_material = raw_material;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getJiujd() {
        return jiujd;
    }

    public void setJiujd(String jiujd) {
        this.jiujd = jiujd;
    }

    public String getXiangqi() {
        return xiangqi;
    }

    public void setXiangqi(String xiangqi) {
        this.xiangqi = xiangqi;
    }

    public String getKougan() {
        return kougan;
    }

    public void setKougan(String kougan) {
        this.kougan = kougan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicture1() {
        return picture1;
    }

    public void setPicture1(byte[] picture1) {
        this.picture1 = picture1;
    }

    public byte[] getPicture2() {
        return picture2;
    }

    public void setPicture2(byte[] picture2) {
        this.picture2 = picture2;
    }

    public byte[] getPicture3() {
        return picture3;
    }

    public void setPicture3(byte[] picture3) {
        this.picture3 = picture3;
    }


}
