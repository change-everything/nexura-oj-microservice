package cn.nexura.model.codesandbox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author peiYP
 * @create 2023-12-31 17:56
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {

    /**
     * 要执行的代码
     */
    private String code;

    /**
     * 代码语言
     */
    private String language;

    /**
     * 输入用例
     */
    private List<String> input;

}
