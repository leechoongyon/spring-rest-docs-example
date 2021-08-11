package com.example.dto;


import com.example.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateResponse {
    private Long id;
    private String name;
    private int age;

    @Builder
    public MemberCreateResponse(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static MemberCreateResponse of(Member member) {
            return MemberCreateResponse.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .age(member.getAge())
                    .build();
    }
}
