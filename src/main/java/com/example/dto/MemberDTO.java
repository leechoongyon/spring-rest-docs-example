package com.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberDTO {

    private Long id;
    private String name;


    @Builder
    public MemberDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MemberDTO of(Long id, String name) {
        return MemberDTO.builder()
                .id(id)
                .name(name)
                .build();
    }
}