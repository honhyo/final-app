package com.example.finalapp.mapper.member;

import com.example.finalapp.dto.member.MemberJoinDTO;
import com.example.finalapp.dto.member.MemberLoginDTO;
import com.example.finalapp.dto.member.MemberSessionDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    MemberJoinDTO memberJoinDTO;

    @BeforeEach
    void setUp() {
        memberJoinDTO = new MemberJoinDTO();
        memberJoinDTO.setLoginId("test");
        memberJoinDTO.setPassword("1234");
        memberJoinDTO.setGender("M");
        memberJoinDTO.setEmail("test@gmail.com");
        memberJoinDTO.setName("test");
        memberJoinDTO.setPhone("01012341234");
        memberJoinDTO.setAddress("test");
        memberJoinDTO.setAddressDetail("test");
        memberJoinDTO.setZipCode("123456");
    }
    @Test
    @DisplayName("회원 정보 삽입, 로그인 정보 조회")
    void insertMember_And_selectLoginInfo() {
        // given
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO();
        memberLoginDTO.setLoginId("test");
        memberLoginDTO.setPassword("1234");
        // when
        memberMapper.insertMember(memberJoinDTO);
        MemberSessionDTO memberSessionDTO = memberMapper.selectLoginInfo(memberLoginDTO)
                .orElse(null);
        // then
        assertThat(memberSessionDTO)
                .isNotNull()
                .extracting("memberId", "loginId", "role")
                .containsExactly( memberJoinDTO.getMemberId(), "test", "ROLE_USER");

    }

    @Test
    void countByLoginId() {
        // given
        memberMapper.insertMember(memberJoinDTO);
        // when
        int count = memberMapper.countByLoginId(memberJoinDTO.getLoginId());
        // then
        assertThat(count).isEqualTo(1);
    }
}