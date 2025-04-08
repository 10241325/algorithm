package com.cy.validator;

import com.cy.constant.Const;

/**
 * @author changyuan
 */
public class ArrayValidator {
    /**
     * 校验数组是否可排序
     *
     * @param arr 待校验数组
     * @return 是否可排序
     */
    public static boolean validateSortArray(int[] arr) {
        return arr != null && arr.length >= Const.TWO;
    }
}
