package com.abdullah.quizapp.service;

import com.abdullah.quizapp.Question;
import com.abdullah.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService  {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestion() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }
}
