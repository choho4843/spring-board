package com.example.board.service;

import com.example.board.BoardApplication;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public void writeBoard(Board board) throws Exception{
        boardRepository.save(board);
    }
//    public void writeBoard2(Board board) throws  Exception{
//        MultipartFile file = board.getFile();
//        String filename=null;
//        if(file!=null &&!file.isEmpty()){
//            String path = "C:/kg/";
//            filename = file.getOriginalFilename();
//            File dFile = new File(path+filename);
//            file.transferTo(dFile);
//            board.setFilename(filename);
//        }
//        boardRepository.save(board);
//    }
    public Board detailBoard(Integer id) throws Exception{
        Optional<Board> oboard = boardRepository.findById(id);
        if(oboard.isPresent()) return  oboard.get();
        throw new Exception("글번호 오류");
    }

    public List<Board> boardList() throws Exception{
        return  boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }


}
