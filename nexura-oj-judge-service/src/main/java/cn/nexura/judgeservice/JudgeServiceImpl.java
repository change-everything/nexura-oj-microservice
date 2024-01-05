package cn.nexura.judgeservice;

import cn.hutool.json.JSONUtil;
import cn.nexura.common.ErrorCode;
import cn.nexura.exception.BusinessException;
import cn.nexura.judgeservice.codesandbox.CodeSandbox;
import cn.nexura.judgeservice.codesandbox.CodeSandboxFactory;
import cn.nexura.judgeservice.codesandbox.CodeSandboxProxy;
import cn.nexura.judgeservice.strategy.JudgeContext;
import cn.nexura.judgeservice.strategy.JudgeManager;
import cn.nexura.model.codesandbox.ExecuteCodeRequest;
import cn.nexura.model.codesandbox.ExecuteCodeResponse;
import cn.nexura.model.codesandbox.JudgeInfo;
import cn.nexura.model.dto.question.JudgeCase;
import cn.nexura.model.entity.Question;
import cn.nexura.model.entity.QuestionSubmit;
import cn.nexura.model.enums.QuestionSubmitStatusEnum;
import cn.nexura.serviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PeiYP
 * @since 2024年01月02日 10:39
 */
@Service
public class JudgeServiceImpl implements JudgeService {
    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {

        // 查询题目是否提交成功
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (null == questionSubmit) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }

        //  查询是否有此题目
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (null == question) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        // 查看题目状态是否正常
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "正在判题中");
        }

        // 更新题目状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }


        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        String judgeCaseStr = question.getJudgeCase();
        // 获取测试用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);

        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);

        codeSandbox = new CodeSandboxProxy(codeSandbox);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .input(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.doExecute(executeCodeRequest);

        // 根据执行结果，判断题目是否正确
        List<String> outputList = executeCodeResponse.getOutput();

        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);

        // 调用判题策略
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        // 修改数据库的判题结果
        // 更新题目状态
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        return questionSubmitResult;
    }
}
