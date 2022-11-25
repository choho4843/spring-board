package com.example.board.vo;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    private  Integer allPage;
    private  Integer curPage;
    private  Integer startPage;
    private  Integer endPage;

}