package com.example.finalapp.service.board.free;

import com.example.finalapp.dto.board.free.FreeBoardDetailDTO;
import com.example.finalapp.mapper.board.free.FreeBoardMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FreeBoardServiceTest {
    @Mock
    FreeBoardMapper freeBoardMapper;
    @InjectMocks
    FreeBoardService freeBoardService;

    @Test
    void getFreeBoardById() {
        // given
        when(freeBoardMapper.selectById(any())).thenReturn(Optional.of(new FreeBoardDetailDTO()));
        // when
        FreeBoardDetailDTO foundBoard = freeBoardService.getFreeBoardById(1L);
        // then
        verify(freeBoardMapper).selectById(any());
        verify(freeBoardMapper).updateViewCount(any());
        assertThat(foundBoard).isNotNull();
    }
}











