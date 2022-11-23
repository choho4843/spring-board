package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("/writeboard")
    public ResponseEntity<String> writeboard(@RequestParam("writer") String writer,
                                             @RequestParam("password") String password,
                                             @RequestParam("subject") String subject,
                                             @RequestParam("content") String content,
                                             @RequestParam(value = "file", required = false) MultipartFile file) {
        ResponseEntity<String> res = null;
        try {
            String filename = null;
            System.out.println(file);
            if (!file.isEmpty() && file!= null) {
                String path = "C:/kg/";
                filename = file.getOriginalFilename();
                File dFile = new File(path+filename);
                file.transferTo(dFile);
            }
                boardService.writeBoard(
                        new Board(null,writer, password, subject, content,filename));

            res = new ResponseEntity<String>("게시글 저장 성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<String>("게시글 저장 실패", HttpStatus.BAD_REQUEST);
        }
        return res;
    }
}
