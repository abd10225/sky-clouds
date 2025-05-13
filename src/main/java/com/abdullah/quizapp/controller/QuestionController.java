package com.abdullah.quizapp.controller;

import com.abdullah.quizapp.Question;
import com.abdullah.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

     @GetMapping("allQuestions")
     public ResponseEntity<List<Question>> getAllQuestions(){
         return questionService.getAllQuestion();
     }

     @GetMapping("{id}")
     public ResponseEntity<Question> getQuestionById(@PathVariable Integer id){
         return questionService.getQuestionById(id);

     }

     @GetMapping("category/{category}")
     public  List<Question> getQuestionsByCategory(@PathVariable String category){
         return questionService.getQuestionsByCategory(category);
     }

     @PostMapping("add")
     public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
     }

     @PostMapping("delete/{id}")
     public String deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
     }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question updatedQuestion) {
        return questionService.updateQuestion(id, updatedQuestion);

    }

}
