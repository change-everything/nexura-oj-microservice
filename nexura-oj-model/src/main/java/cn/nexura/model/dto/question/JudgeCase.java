package cn.nexura.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 * @author PeiYP
 * @since 2023年12月28日 11:02
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
