package com.abdullah.quizapp.service;

import com.abdullah.quizapp.dao.QuestionDao;
import com.abdullah.quizapp.dao.QuizDao;
import com.abdullah.quizapp.model.Question;
import com.abdullah.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizeService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new  ResponseEntity<>("success", HttpStatus.CREATED);

    }
}
