package com.club.data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jibo on 2017/4/26.
 */
@Entity
@Table(name = "forum")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "refer")
    private String refer;

    @Column(name = "url")
    private String url;

    @Column(name = "create_time")
    private Timestamp createTime;

    public Forum() {}

    public Forum(String title, String refer, String url) {
        this.title = title;
        this.refer = refer;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
