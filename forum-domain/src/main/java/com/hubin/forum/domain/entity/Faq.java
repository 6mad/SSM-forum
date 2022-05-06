package com.hubin.forum.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author Hubin
 * @create 2021/11/1
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Faq extends BasePosts {

    /**
     * 最佳答案 ID
     */
    private Long solutionId;

    public Faq copy() {
        Faq faq = new Faq();

        BeanUtils.copyProperties(this, faq);

        return faq;
    }

}
