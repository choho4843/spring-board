package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

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
            if (!file.isEmpty() && file != null) {
                String path = "C:/kg/";
                filename = file.getOriginalFilename();
                File dFile = new File(path + filename);
                file.transferTo(dFile);
            }
            boardService.writeBoard(
                    new Board(null, writer, password, subject, content, filename, null));

            res = new ResponseEntity<String>("게시글 저장 성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<String>("게시글 저장 실패", HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    //    @PostMapping("/writeboard2")
//    public ResponseEntity<String> writeboard(@ModelAttribute Board board){
//        ResponseEntity<String > res = null;
//        try{
//            boardService.writeBoard(board);
//            res=new ResponseEntity<String >("게시글 저장 성공",HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            res = new ResponseEntity<String >("게시글 저장 실패", HttpStatus.BAD_REQUEST);
//        }
//        return res;
//    }
    @GetMapping("/boarddetail/{id}")
    public ResponseEntity<Board> boarddetail(@PathVariable Integer id) {
        ResponseEntity<Board> res = null;
        try {
            Board board = boardService.detailBoard(id);
            res = new ResponseEntity<Board> (board, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Board> (HttpStatus.BAD_REQUEST);
        }
        return res;
    }
    @GetMapping("/img/{filename}")
    public void imageView(@PathVariable String filename, HttpServletResponse response){
        try{
            String path ="C:/kg/";
            FileInputStream fis =new FileInputStream(path+filename);
            OutputStream out = response.getOutputStream();
            FileCopyUtils.copy(fis, out);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
