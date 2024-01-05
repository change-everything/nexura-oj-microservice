package cn.nexura.model.codesandbox;

import lombok.Data;

/**
 * 题目判题信息
 * @author PeiYP
 * @since 2023年12月28日 11:02
 */
@Data
public class JudgeInfo {

    /**
     *  程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间
     */
    private Long time;

}
