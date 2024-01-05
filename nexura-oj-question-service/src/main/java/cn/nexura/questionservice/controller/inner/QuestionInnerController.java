package cn.nexura.questionservice.controller.inner;

import cn.nexura.model.entity.Question;
import cn.nexura.model.entity.QuestionSubmit;
import cn.nexura.questionservice.service.QuestionService;
import cn.nexura.questionservice.service.QuestionSubmitService;
import cn.nexura.serviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author PeiYP
 * @since 2024年01月05日 10:16
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;



    @GetMapping("/get/id")
    @Override
    public Question getQuestionById(@RequestParam("questionId") long questionId) {
        return questionService.getById(questionId);
    }


    @GetMapping("/get/submit/id")
    @Override
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }


    @PostMapping("/submit/update")
    @Override
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }

}
