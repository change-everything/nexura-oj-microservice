package cn.nexura.judgeservice.judege.codesandbox.impl;


import cn.nexura.judgeservice.judege.codesandbox.CodeSandbox;
import cn.nexura.model.codesandbox.ExecuteCodeRequest;
import cn.nexura.model.codesandbox.ExecuteCodeResponse;
import cn.nexura.model.codesandbox.JudgeInfo;
import cn.nexura.model.enums.JudgeInfoMessageEnum;
import cn.nexura.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**、
 * 实例代码沙箱
 * @author peiYP
 * @create 2023-12-31 18:08
 **/
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse doExecute(ExecuteCodeRequest executeCodeRequest) {
        List<String> input = executeCodeRequest.getInput();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setMessage("执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        executeCodeResponse.setOutput(input);
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);

        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
