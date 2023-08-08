package com.sweepai.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerRepository.save(answer);
    }

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Answer updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        Answer existingAnswer = answerRepository.findById(id).orElse(null);
        if (existingAnswer != null) {
            existingAnswer.setName(answer.getName());
            existingAnswer.setText(answer.getText());
            existingAnswer.setNumber(answer.getNumber());
            return answerRepository.save(existingAnswer);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerRepository.deleteById(id);
    }
}