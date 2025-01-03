package com.example.finalapp.exception.member;

public class MemberDuplicateException extends RuntimeException{
    // 오류가 났을 때 메세지를 띄우기 위해
    public MemberDuplicateException(String message) {
        super(message);
    }
}
