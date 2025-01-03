package com.example.finalapp.dto.board.free;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class FreeBoardDetailDTO {
    private Long freeBoardId;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long memberId;
    private String loginId;

    // 나중에 추가
    // 좋아요 수
    // 이미지 파일 정보

}
