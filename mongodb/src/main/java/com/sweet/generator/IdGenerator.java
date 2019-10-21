package com.sweet.generator;

/**
 *  mongodb
 *
 * @author lucifer
 * @date 2019/6/5 18:23
 */
public interface IdGenerator {

    /**
     * 生成一个唯一的ID
     *
     * @return 生成的ID
     */
    long nextId();

}
