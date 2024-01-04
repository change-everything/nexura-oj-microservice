package cn.nexura.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 * @author PeiYP
 * @since 2023年12月28日 11:02
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制 毫秒
     */
    private Long timeLimit;

    /**
     * 内存限制 KB
     */
    private Long memoryLimit;

    /**
     * 堆栈限制 KB
     */
    private Long stackLimit;

}
