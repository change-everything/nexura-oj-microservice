package cn.nexura.serviceclient.service;


import cn.nexura.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author PeiYP
 * @since 2024年01月02日 10:35
 */
@FeignClient(value = "nexura-oj-judge-service", path = "/api/judge/inner")
public interface JudgeFeignClient {

    /**
     * 判题服务
     *
     * @param questionSubmitId
     * @return QuestionSubmitVO
     */
    @PostMapping("/do")
    QuestionSubmit doJudge(long questionSubmitId);

}
