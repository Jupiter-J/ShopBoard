package com.example.shopboard.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shop")
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //유저가 있어야 shop이 생긴다
    //유저는 shop을 하나씩만 만들 수 있다
    @OneToOne(
            fetch = FetchType.LAZY,
            targetEntity = UserEntity.class
    )
    @JoinColumn(name = "owner_id")
    private UserEntity owner; //user - fk


    //shop이 있어야 area가 생성된다
    //다수의 shop은 area에 속해있다
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = AreaEntity.class
    )
    @JoinColumn(name = "located_at")
    private AreaEntity location; //area - fk


    //shop이 있어야 category가 생긴다
    //다수의 shop은 category에 속해있다
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = CategoryEntity.class
    )
    @JoinColumn(name = "category")
    private CategoryEntity categoryEntity; //category - fk


    //shop - shopPost 양방향
    //shopPost를 작성할때 유저정보가 필요하다
    //shopPost에서 유저정보의 관계(fk)를 늘리지 않고,
    // 유저가 포함된 shop을 양방향으로 바꿈으로서 관계 설정을 줄임!
    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = ShopPostEntity.class
    )
    private List<ShopPostEntity> shopPostEntityList;

    public ShopEntity() {
    }

    public ShopEntity(Long id, String name, UserEntity owner, AreaEntity location, CategoryEntity categoryEntity, List<ShopPostEntity> shopPostEntityList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.location = location;
        this.categoryEntity = categoryEntity;
        this.shopPostEntityList = shopPostEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public AreaEntity getLocation() {
        return location;
    }

    public void setLocation(AreaEntity location) {
        this.location = location;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public List<ShopPostEntity> getShopPostEntityList() {
        return shopPostEntityList;
    }

    public void setShopPostEntityList(List<ShopPostEntity> shopPostEntityList) {
        this.shopPostEntityList = shopPostEntityList;
    }
}
