package com.example.finalapp.dto.board.free;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class FreeBoardListDTO {
   private Long FreeBoardId;
   private String title;
   private int viewCount;
   private LocalDate regDate;
   private LocalDate modDate;
   private Long memberId;
   private String loginId;
   private int likeCount;
   private int commentCount;

    //파일관련 필드는 나중에 처리
   private Long freeFileId;
   private String freeFilename;
   private String uuid;
   private String filePath;
   private String extension;


}
