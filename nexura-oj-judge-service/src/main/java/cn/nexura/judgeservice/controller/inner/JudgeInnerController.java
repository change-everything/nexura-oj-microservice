package cn.nexura.judgeservice.controller.inner;

import cn.nexura.judgeservice.JudgeService;
import cn.nexura.model.entity.QuestionSubmit;
import cn.nexura.serviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author PeiYP
 * @since 2024年01月05日 10:21
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;


    @PostMapping("/do")
    @Override
    public QuestionSubmit doJudge(long questionSubmitId){
        return judgeService.doJudge(questionSubmitId);
    }

}
