package cn.nexura.judgeservice.codesandbox;


import cn.nexura.judgeservice.codesandbox.impl.ExampleCodeSandbox;
import cn.nexura.judgeservice.codesandbox.impl.RemoteCodeSandbox;
import cn.nexura.judgeservice.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂
 * @author PeiYP
 * @since 2024年01月02日 9:49
 */
public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            case "example":
            default:
                return new ExampleCodeSandbox();
        }
    }

}
