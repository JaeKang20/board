package com.portfolio.domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long boardId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotBlank
    @Size(max = 30)
    private String title;
    private String content;
    private String registerDate;
    private String updateDate;
    private String deleteDate;
    private int boardViewCount;
    private int boardLike;


@Builder
    public Board(Long boardId, Member member, String title, String content, String registerDate, String updateDate, String deleteDate, int boardLike
,int boardViewCount) {
        this.boardId = boardId;
        this.member = member;
        this.title = title;
        this.content = content;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
        this.boardLike = boardLike;
        this.boardViewCount = boardViewCount;
    }
}