package com.example.finalapp.service.member;

import com.example.finalapp.dto.member.MemberJoinDTO;
import com.example.finalapp.exception.member.MemberDuplicateException;
import com.example.finalapp.mapper.member.MemberMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    MemberMapper memberMapper;

    @InjectMocks
    MemberService memberService;

    @Test
    void addMember() {
        // given
        when(memberMapper.countByLoginId(any())).thenReturn(0);

        // when
        memberService.addMember(new MemberJoinDTO());

        // then
        verify(memberMapper).countByLoginId(any());
        verify(memberMapper).insertMember(any());
    }

    @Test
    void addMember_exception() {
        // given
        when(memberMapper.countByLoginId(any())).thenReturn(1);

        // exception 테스트는 when, then 한번에 잊지 말기!
        // when
        // then
        assertThatThrownBy(() -> memberService.addMember(new MemberJoinDTO()))
                .isInstanceOf(MemberDuplicateException.class)
                .hasMessageContaining("이미 사용중인");
    }
}