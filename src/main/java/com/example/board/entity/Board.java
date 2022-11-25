package com.example.board.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String writer;
    @Column
    private String password;
    @Column
    private String subject;
    @Column
    private String content;
    @Column
    private String filename;

}
