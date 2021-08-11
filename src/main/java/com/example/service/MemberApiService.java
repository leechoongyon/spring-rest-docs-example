package com.example.service;

import com.example.domain.Member;
import com.example.dto.MemberCreateRequest;
import com.example.dto.MemberCreateResponse;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberApiService {

    private final MemberRepository memberRepository;

    public MemberCreateResponse saveMember(MemberCreateRequest request) {
        Member member = memberRepository.save(Member.createMember(request));
        return MemberCreateResponse.of(member);
    }
}
