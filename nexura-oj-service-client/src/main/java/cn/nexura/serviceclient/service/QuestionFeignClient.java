package cn.nexura.serviceclient.service;

import cn.nexura.model.entity.Question;
import cn.nexura.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
* @author peiYP
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2023-12-28 10:35:52
*/
@FeignClient(name = "nexura-oj-question-service", path = "/api/question/inner")
public interface QuestionFeignClient {

    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") long questionId);


    @GetMapping("/get/submit/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId);


    @PostMapping("/submit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);
    
}
