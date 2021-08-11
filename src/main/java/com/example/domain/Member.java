package com.example.domain;

import com.example.dto.MemberCreateRequest;
import com.example.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@RequiredArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Builder
    public Member(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static Member createMember(MemberCreateRequest request) {
        return Member.builder()
                .name(request.getName())
                .age(request.getAge())
                .build();
    }
}
