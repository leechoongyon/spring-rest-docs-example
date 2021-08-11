package com.example.service;

import com.example.domain.Member;
import com.example.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class) // 단위 테스트에 필요한 빈만 빠르게 로딩
public class MemberApiServiceTest {

    @InjectMocks
    private MemberApiService memberApiService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void saveMemberTest() throws Exception {
        // given
        Member member = Member.builder()
                .name("service test")
                .build();

        Member expectedResult = Member.builder()
                .name("service test")
                .id(1L)
                .build();

        // mocking
        given(memberRepository.save(member)).willReturn(expectedResult);
        given(memberRepository.findById(1L)).willReturn(Optional.ofNullable(expectedResult));

        // when
        Member result = memberRepository.save(member);
        Member findMember = memberRepository.findById(result.getId()).get();

        // then
        assertThat(findMember.getId()).isEqualTo(1L);
        assertThat(findMember.getName()).isEqualTo("service test");
    }
}