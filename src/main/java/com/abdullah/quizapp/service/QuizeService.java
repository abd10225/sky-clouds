package com.abdullah.quizapp.service;

import com.abdullah.quizapp.dao.QuestionDao;
import com.abdullah.quizapp.dao.QuizDao;
import com.abdullah.quizapp.model.Question;
import com.abdullah.quizapp.model.QuestionWrapper;
import com.abdullah.quizapp.model.Quiz;
import com.abdullah.quizapp.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q : questionsFromDb) {
             QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
             questionsForUser.add(qw);
        }


        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<ResponseModel> responses) {
        // 1. Get quiz and validate existence
        Quiz quiz = quizDao.findById(quizId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found"));

        // 2. Create map of questions by ID for efficient lookup
        Map<Integer, Question> questionMap = quiz.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, Function.identity()));

        // 3. Count correct answers
        int correctCount = 0;

        for (ResponseModel response : responses) {
            Question question = questionMap.get(response.getId());

            if (question != null &&
                    response.getResponse() != null &&
                    response.getResponse().trim().equalsIgnoreCase(question.getRightAnswer().trim())) {
                correctCount++;
            }
        }

        return ResponseEntity.ok(correctCount);
    }
}
