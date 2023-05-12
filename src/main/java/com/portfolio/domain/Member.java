package com.portfolio.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)가 @Id 어노테이션과 함께 사용되면,
    //데이터베이스에서 자동으로 생성되는 primary key 값을 사용하여 엔티티 객체를 생성
    private  Long id;

    @Column
    private String name;
}
