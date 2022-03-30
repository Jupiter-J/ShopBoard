# 가게 리뷰 사이트
### 유저(User), 지역(Area), 가게(Shop), 카테고리(Category), 가게 리뷰(ShopReview), 가게 포스팅(Shop Posting)

### User
1. 유저는 회원가입시 ShopOwner을 할것인지 선택 가능하다
2. 유저는 지역에 속해있다
   1. 유저와 지역은 단방향 관계 (ManyToOne)이다
   2. 유저는 지역의 fk인 area_id를 가지고 있다

### Shop
1. 가게는 유저중 ShopOwner을 체크한 사람만 생성 가능하다 
   1. 유저
      * 가게와 유저는 단방향 관계 (OneToOne)이다
      * 유저의 fk인 owner_id를 가지고 있다
2. 가게는 지역과 카테고리에 속해있다.
   1. 지역
      * 가게와 지역은 단방향 관계 (ManyToOne)이다
      * 가게는 지역의 fk인 located_at을 가지고 있다
   2. 카테고리
      * 가게와 카테고리는 단방향 관계 (ManyToOne)이다
      * 가게는 카테고리의 fk인 category를 가지고 있다

### Shop Review & Shop Post   
1. 가게 리뷰와 가게 포스팅은 가게 안에 속해있다
   1. 가게 리뷰 
      * 모든 유저가 작성 가능하다
      * 가게 리뷰와 유저는 단방향 관계 (ManyToOne)이다
      * 유저의 fk인 writer을 가지고 있다
      * 가게 리뷰와 가게는 단방향 관계 (ManyToOne)이다
        > 유저의 writer키가 가게 리뷰에 있음으로 가게와 단방향 관계를 만든다 
      * 가게의 fk인 shop_id를 가지고 있다
  
   2. 가게 포스팅
      * ShopOwner을 선택한 유저만 작성이 가능하다.
      * 가게 포스팅과 가게는 양방향 관계 (ManyToOne)이다
        > 특정 유저를 통해 사용하기 때문에 가게 안에 있는 유저의 owner_id를 사용할 수 있음으로 양방향 관계를 사용한다  
      * 가게의 fk인 shop_id를 가지고 있다
   
