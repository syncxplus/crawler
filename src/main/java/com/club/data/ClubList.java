package com.club.data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jibo on 2017/4/26.
 */
@Entity
@Table(name = "club_list")
public class ClubList {
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

    @Column(name = "refer_url")
    private String referUrl;

    @Column(name = "logo")
    private String logo;

    @Column(name = "total")
    private String total;

    @Column(name = "create_time")
    private Timestamp createTime;

    public ClubList() {}

    public ClubList(String title, String refer, String club, String url, String referUrl, String logo, String total) {
        this.title = title;
        this.refer = refer;
        this.club = club;
        this.url = url;
        this.referUrl = referUrl;
        this.logo = logo;
        this.total = total;
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

    public String getReferUrl() {
        return referUrl;
    }

    public void setReferUrl(String referUrl) {
        this.referUrl = referUrl;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
