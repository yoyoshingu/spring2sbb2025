package com.sg.ai.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	
	private final QuestionService questionService;

	@GetMapping("/question/list")
	public String list(Model model) {
		
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList );
		return "question_list";
	}
	
	
	@GetMapping("/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/question/create")
	public String questionCreate() {
		return "question_form";
	}
	
	@PostMapping("/question/create")
	public String questionCreatePost( @RequestParam(value="subject") String subject, 
										@RequestParam(value="content") String content ) {
		
		// TODO : 질문을 저장한다
		this.questionService.create(subject, content);
		
		return "redirect:/question/list";
	}
	
	
	// 자바의 메소드 접근제어
	// public, private, protected, default
	
	public void pubmethod() {}
	private void primethod() {}
	protected void promethod() {}
	void defmethod() {}
}