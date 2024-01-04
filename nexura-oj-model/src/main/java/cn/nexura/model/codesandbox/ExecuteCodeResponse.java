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
public class ExecuteCodeResponse {

    /**
     * 接口信息
     */
    private String message;

    /**
     * 输出用例
     */
    private List<String> output;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态
     */
    private Integer status;

}
