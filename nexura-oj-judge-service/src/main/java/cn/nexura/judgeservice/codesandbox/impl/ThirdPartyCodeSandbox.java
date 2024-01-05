package cn.nexura.judgeservice.codesandbox.impl;


import cn.nexura.judgeservice.codesandbox.CodeSandbox;
import cn.nexura.model.codesandbox.ExecuteCodeRequest;
import cn.nexura.model.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱
 * @author peiYP
 * @create 2023-12-31 18:08
 **/
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse doExecute(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("ThirdPartyCodeSandboxImpl.doExecute");
        return null;
    }
}
