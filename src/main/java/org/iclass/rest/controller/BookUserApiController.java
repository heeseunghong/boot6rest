package org.iclass.rest.controller;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.iclass.rest.dao.BookUserMapper;
import org.iclass.rest.dto.BookUser;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookUserApiController {

	private final BookUserMapper bookUserMapper;

	@GetMapping("/admin/bookusers")
	public List<BookUser> members() {
		List<BookUser> list = bookUserMapper.selectAll();
		
		return list;
	}
	@PostMapping("/bookuser")		//요청에는 헤더와 바디가 있습니다. @RequestBody 가 요청의 바디라고 알려줍니다.
										// -> 클라이언트가 보낸 json 문자열을 자바 객체로 자동 변환(역직열화)
	public Map<String, Integer> save(@RequestBody @Valid BookUser bookuser){
		log.info(">>>>>>>>>>> request body : {}",bookuser );
		int conut = bookUserMapper.insert(bookuser);
		Map<String, Integer> resultMap = new HashMap<>();		//처리 결과를 응답하기 위한 Map
		resultMap.put("count",conut);

		return resultMap;
	}

	@GetMapping("/bookuser/{id}")
	public BookUser selectOne(@PathVariable String id){			//@PathVariable : url 경로로 들어온 값을 저장하는 변수
		BookUser bookUser = bookUserMapper.selectOne(id);
		log.info(">>>>>>>>>>>> path variable id : {}",id);
		return bookUser;			//bookuser DTO 를 json 문자열로 변환시켜 전달합니다.(직렬화)
	}

	@DeleteMapping("/bookuser/{id}")
	public Map<String,Integer> delete(@PathVariable String id) {
		int conut = bookUserMapper.delete(id);
		Map<String,Integer> resultMap = new HashMap<>();
		resultMap.put("count",conut);

		return resultMap;
	}

	
	
}
