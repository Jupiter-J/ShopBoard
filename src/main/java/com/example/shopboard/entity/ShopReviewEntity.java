package com.example.shopboard.entity;

import javax.persistence.*;

@Table
@Entity(name = "shopReview")
public class ShopReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    //shop이 있어야 ShopReivew가 생긴다
    //shop에는 다수의 reivew가 있다
    @ManyToOne(
            targetEntity = ShopEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;    //shop - fk


    //유저가 있어야 리뷰를 작성할 수 있다
    //유저는 다수의 리뷰를 작성할 수 있다
    @ManyToOne(
            targetEntity = UserEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "writer")
    private UserEntity writer;    //user - fk


    private Long grade;

    public ShopReviewEntity() {
    }

    public ShopReviewEntity(Long id, String title, String content, ShopEntity shop, UserEntity writer, Long grade) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shop = shop;
        this.writer = writer;
        this.grade = grade;
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

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    public UserEntity getWriter() {
        return writer;
    }

    public void setWriter(UserEntity writer) {
        this.writer = writer;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }
}
