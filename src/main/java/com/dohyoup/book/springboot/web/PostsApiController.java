package com.dohyoup.book.springboot.web;

import com.dohyoup.book.springboot.service.posts.PostsService;
import com.dohyoup.book.springboot.web.dto.PostsResponseDto;
import com.dohyoup.book.springboot.web.dto.PostsSaveRequestDto;
import com.dohyoup.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    //메모리에서 실행되는 h2 RDBMS 를 사용했기 때문에 어플리케이션을 재시작할 때마다 저장 내용이 초기화된다.
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        // 컨트롤러에서 HTTP 요청을 처리하는 메서드에 DTO 클래스를 매개변수로 사용할 경우
        // Spring Boot 는 요청 본문을 해당 DTO 객체로 자동변환합니다. @RequestBody 사용
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
