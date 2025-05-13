package com.abdullah.quizapp.service;

import com.abdullah.quizapp.dao.QuestionDao;
import com.abdullah.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService  {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>( "successfully added question", HttpStatus.CREATED);
    }

    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "successfully deleted";
    }



    public ResponseEntity<Question> getQuestionById(Integer id) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if(optionalQuestion.isPresent()) {
            return ResponseEntity.ok(optionalQuestion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionDao.findById(id);

        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();

            // Update only the fields you care about
//            existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
            if (updatedQuestion.getQuestionTitle() == null || updatedQuestion.getQuestionTitle().isBlank()) {
                return ResponseEntity.badRequest().body("Question title cannot be null or blank");
            }
            existingQuestion.setOption1(updatedQuestion.getOption1());
            existingQuestion.setOption2(updatedQuestion.getOption2());
            existingQuestion.setOption3(updatedQuestion.getOption3());
            existingQuestion.setOption4(updatedQuestion.getOption4());
            existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
            existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            existingQuestion.setCategory(updatedQuestion.getCategory());

            questionDao.save(existingQuestion);
            return ResponseEntity.ok("Question updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
    }
}
