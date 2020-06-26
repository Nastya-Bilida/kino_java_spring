package com.kinob.WebKino.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KinoPost {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String genre;
    private String director;
    private String full_text;
    private String startTime;
    private String shareLink;
    private int yearKino;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String time) {
        this.startTime = time;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getYearKino() {
        return yearKino;
    }

    public void setYearKino(int year) {
        this.yearKino = year;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public KinoPost() {
    }

    public KinoPost(String title, String genre, String director, String full_text, String startTime, String shareLink, int yearKino) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.full_text = full_text;
        this.startTime = startTime;
        this.shareLink = shareLink;
        this.yearKino = yearKino;
    }
}

