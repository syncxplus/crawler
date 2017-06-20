package com.club.data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jibo on 2017/4/26.
 */
@Entity
@Table(name = "club_index")
public class ClubIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "refer")
    private String refer;

    @Column(name = "club")
    private String club;

    @Column(name = "url")
    private String url;

    @Column(name = "chief")
    private String chief;

    @Column(name = "chief_url")
    private String chiefUrl;

    @Column(name = "assistant")
    private String assistant;

    @Column(name = "create_time")
    private Timestamp createTime;

    public ClubIndex() {}

    public ClubIndex(String title, String refer, String club, String url, String chief, String chiefUrl, String assistant) {
        this.title = title;
        this.refer = refer;
        this.club = club;
        this.url = url;
        this.chief = chief;
        this.chiefUrl = chiefUrl;
        this.assistant = assistant;
        this.createTime = new Timestamp(System.currentTimeMillis());
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

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public String getChiefUrl() {
        return chiefUrl;
    }

    public void setChiefUrl(String chiefUrl) {
        this.chiefUrl = chiefUrl;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
