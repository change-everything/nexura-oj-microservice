package cn.nexura.judgeservice.codesandbox;


import cn.nexura.model.codesandbox.ExecuteCodeRequest;
import cn.nexura.model.codesandbox.ExecuteCodeResponse;

/**
 * @author peiYP
 * @create 2023-12-31 17:54
 **/
public interface CodeSandbox {

    ExecuteCodeResponse doExecute(ExecuteCodeRequest executeCodeRequest);


}

