package com.example.finalapp.mapper.board.free;

import com.example.finalapp.dto.board.free.FreeBoardWriteDTO;
import com.example.finalapp.dto.member.MemberJoinDTO;
import com.example.finalapp.mapper.member.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FreeBoardMapperTest {
    @Autowired
    FreeBoardMapper freeBoardMapper;

    FreeBoardWriteDTO writeDTO;

    MemberJoinDTO joinDTO;
    @Autowired
    private MemberMapper memberMapper;


    @BeforeEach
    void setUp() {
        joinDTO = new MemberJoinDTO();
        joinDTO.setLoginId("test");
        joinDTO.setPassword("1234");
        joinDTO.setGender("M");
        joinDTO.setEmail("test@gmail.com");
        joinDTO.setName("test");
        joinDTO.setPhone("01012341234");
        joinDTO.setAddress("test");
        joinDTO.setAddressDetail("test");
        joinDTO.setZipCode("123456");

        memberMapper.insertMember(joinDTO);

        writeDTO = new FreeBoardWriteDTO();
        writeDTO.setTitle("test title");
        writeDTO.setContent("test content");
        writeDTO.setMemberId(joinDTO.getMemberId());

    }

    @Test
    void insertFreeBoard() {
    }
}