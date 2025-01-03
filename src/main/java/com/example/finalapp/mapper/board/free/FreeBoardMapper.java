package com.example.finalapp.mapper.board.free;

import com.example.finalapp.dto.board.free.FreeBoardDetailDTO;
import com.example.finalapp.dto.board.free.FreeBoardWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface FreeBoardMapper {
    void insertFreeBoard(FreeBoardWriteDTO boardWriteDTO);
    Optional<FreeBoardDetailDTO> selectById(Long freeBoardId);
}
