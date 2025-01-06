package com.example.finalapp.mapper.board.free.file;

import com.example.finalapp.dto.board.free.FreeBoardWriteDTO;
import com.example.finalapp.dto.board.free.file.FreeFileDTO;
import com.example.finalapp.dto.member.MemberJoinDTO;
import com.example.finalapp.mapper.board.free.FreeBoardMapper;
import com.example.finalapp.mapper.member.MemberMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class FreeFileMapperTest {
    @Autowired
    FreeFileMapper freeFileMapper;
    @Autowired
    FreeBoardMapper freeBoardMapper;
    @Autowired
    MemberMapper memberMapper;

    FreeFileDTO fileDTO;

    @BeforeEach
    void setUp() {
        MemberJoinDTO memberJoinDTO = new MemberJoinDTO();
        memberJoinDTO.setLoginId("test");
        memberMapper.insertMember(memberJoinDTO);

        FreeBoardWriteDTO freeBoardWriteDTO = new FreeBoardWriteDTO();
        freeBoardWriteDTO.setMemberId(memberJoinDTO.getMemberId());
        freeBoardWriteDTO.setTitle("test title");
        freeBoardMapper.insertFreeBoard(freeBoardWriteDTO);

        fileDTO = new FreeFileDTO();
        fileDTO.setFreeBoardId(freeBoardWriteDTO.getFreeBoardId());
        fileDTO.setUuid("test");
    }

    @Test
    void insert_And_selectFile() {
        // given
        freeFileMapper.insertFile(fileDTO);
        // when
        FreeFileDTO foundFile = freeFileMapper.selectByBoardId(fileDTO.getFreeBoardId())
                .orElse(null);
        // then
        assertThat(foundFile)
                .isNotNull()
                .extracting("uuid")
                .isEqualTo("test");
    }

}