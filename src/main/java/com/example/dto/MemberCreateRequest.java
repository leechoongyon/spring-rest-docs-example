package com.example.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateRequest {
    private String name;
    private int age;

    @Builder
    public MemberCreateRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
