package com.sg.ai;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sg.ai.answer.Answer;
import com.sg.ai.answer.AnswerRepository;
import com.sg.ai.question.Question;
import com.sg.ai.question.QuestionRepository;
import com.sg.ai.question.QuestionService;

@SpringBootTest
class SbbApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;

//	@Test
	void testJpa() {
		Question q1 = new Question();
		
//		q1.content = "오늘이 며칠인가요";
//		이렇게 하지 말고
		
		q1.setContent("오늘이 며칠인가요");
		q1.setSubject("또또  질문합니다");
		q1.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("스프링부트 질문입니다");
		q2.setContent("id는 자동으로 만들어지나요");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
		
	}
	
	@Autowired
	private AnswerRepository answerRepository;
	
//	@Test
	void testJpaa() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("오늘은 5월12일입니다");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		
	}
	
	
	// 페이지 기능을 구현하기위해, 300개의 게시물을 만듬
	@Autowired
	private QuestionService questionService;
	
//	@Test
	void testPage() {
		for(int i=1; i<=300; i++) {
			String subject = String.format("테스트용으로 만들었습니다:[%03d]", i);
			String content = " 별 내용 없습니다. 2025년6월9일 마지막수업니다";
			this.questionService.create(subject, content);
		}
	}

}
