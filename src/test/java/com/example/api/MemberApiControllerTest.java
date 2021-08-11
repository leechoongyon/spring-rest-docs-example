package com.example.api;

import com.example.dto.MemberCreateRequest;
import com.example.dto.MemberCreateResponse;
import com.example.utils.DocumentUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MemberApiControllerTest extends ApiCommonModule {
    @Test
    public void getMemberTest() throws Exception {
        this.mockMvc.perform(get("/api/member/{id}",5)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("get-member",   // get-member 라는 이름으로 asciidoc 문서가 만들어집니다.
                        DocumentUtils.getDocumentRequest(), // 문서를 이쁘게 출력합니다.
                        DocumentUtils.getDocumentResponse(),    // 문서를 이쁘게 출력합니다.
                        pathParameters( // id 가 url 뒤에 붙는 변수이기에 PathParameters 로 설정해줍니다.
                                parameterWithName("id").description("member id")
                        ),
                        responseFields( // responseField 를 써줍니다.
                                fieldWithPath("id").description("member id"),
                                fieldWithPath("name").description("member name")
                        )
                ))
                .andExpect(jsonPath("$.id", is(notNullValue())))    // id 가 notNull 이 아닌 것을 확인합니다.
                .andExpect(jsonPath("$.name", is(notNullValue())))  // name 이 notNull 이 아닌 것을 확인합니다.
                ;
    }

    @Test
    public void saveMemberTest() throws Exception {
        // given
        MemberCreateRequest request = MemberCreateRequest.builder()
                .name("test")
                .age(20)
                .build();
        MemberCreateResponse response = MemberCreateResponse.builder()
                .name("test")
                .id(1L)
                .age(20)
                .build();

        when(memberApiService.saveMember(any())).thenReturn(response);  // service 에 대한 mocking 입니다.

        String content = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post("/api/member")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.age").value(20))
                .andDo(document("save-member",
                        requestFields(
                                fieldWithPath("name").description("member name"),
                                fieldWithPath("age").description("member age").optional()   // optional 을 주면 필수 값이 아닙니다.
                        ),
                        responseFields(
                                fieldWithPath("id").description("member id"),
                                fieldWithPath("name").description("member name"),
                                fieldWithPath("age").description("member age")
                        )
                ))
        ;
    }
}