package com.example.api;

import com.example.dto.MemberCreateRequest;
import com.example.dto.MemberCreateResponse;
import com.example.dto.MemberDTO;
import com.example.service.MemberApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberApiController {

    private MemberApiService memberApiService;

    public MemberApiController(MemberApiService memberApiService) {
        this.memberApiService = memberApiService;
    }

    @GetMapping("/api/member/{id}")
    public MemberDTO getMember(@PathVariable Long id) {
        MemberDTO memberDTO = MemberDTO.of(id, "test");
        return memberDTO;
    }

    @PostMapping("/api/member")
    public MemberCreateResponse saveMember(@RequestBody MemberCreateRequest request) {
        return memberApiService.saveMember(request);
    }
}
