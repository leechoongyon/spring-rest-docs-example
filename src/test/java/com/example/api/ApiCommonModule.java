package com.example.api;

import com.example.service.MemberApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 *  1. ApiCommonModule 을 만든 이유는
 *      테스트 수행 시, spring context 가 지속적으로 생성되고 소멸되기에
 *      test annotation 이 많아질수록 느려질 수 밖에 없는 구조입니다.
 *      그걸 방지하기 위해 상속클래스에서 한 번만 spring context 를 로딩하고,
 *      테스트들은 abstact 을 상속받아 해당 spring context 를 공유해서 사용하는 것입니다.
 *      이렇게 할 경우 성능 향상이 있습니다.
 */
// WebMvc 관련 가벼운 테스트를 위해 설정합니다. SpringBootTest 는 IocContainer 띄우기에 느립니다.
@RunWith(SpringRunner.class)
// spring rest docs 에 대한 auto-configuration 을 가능하게 해주는 annotation 입니다.
// 자동 구성은 MockMvc 기반, WebTestClient, Http 를 통한 웹 애플리케이션의 RestAssured 기반 테스트를 설정합니다.
@WebMvcTest(controllers = {
        MemberApiController.class,
})
@AutoConfigureRestDocs
public abstract class ApiCommonModule {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MemberApiService memberApiService; // controller api 테스트이기에 service 는 mocking 처리 합니다.
}
