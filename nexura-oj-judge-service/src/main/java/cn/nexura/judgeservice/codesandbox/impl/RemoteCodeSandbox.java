package cn.nexura.judgeservice.codesandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.nexura.common.ErrorCode;
import cn.nexura.exception.BusinessException;
import cn.nexura.judgeservice.codesandbox.CodeSandbox;
import cn.nexura.model.codesandbox.ExecuteCodeRequest;
import cn.nexura.model.codesandbox.ExecuteCodeResponse;

/**
 * 远程代码沙箱
 * @author peiYP
 * @create 2023-12-31 18:08
 **/
public class RemoteCodeSandbox implements CodeSandbox {

    // 定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Override
    public ExecuteCodeResponse doExecute(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("RemoteCodeSandboxImpl.doExecute");
        String url = "http://localhost:34895/executeCode";
        String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(jsonStr)
                .execute()
                .body();

        if (StrUtil.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "message=" + responseStr);
        }

        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
