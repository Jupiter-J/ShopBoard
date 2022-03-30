package com.example.shopboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "shopPost")
public class ShopPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    //shop이 있어야 shopPost가 생긴다
    //shop에는 다수의 Post가 존재한다
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = ShopEntity.class
    )
    @JoinColumn(name = "shop_id")
    private ShopEntity shopId;    //shop - fk


    //굳이 관계를 늘리지 않고 양방향 관계로 변경
    private Long writer;

    public ShopPostEntity() {
    }

    public ShopPostEntity(Long id, String title, String content, ShopEntity shopId, Long writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shopId = shopId;
        this.writer = writer;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ShopEntity getShopId() {
        return shopId;
    }

    public void setShopId(ShopEntity shopId) {
        this.shopId = shopId;
    }

    public Long getWriter() {
        return writer;
    }

    public void setWriter(Long writer) {
        this.writer = writer;
    }
}
