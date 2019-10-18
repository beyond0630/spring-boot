package com.sweet.generator;

/**
 * flash-correct
 *
 * @author Gent Liu
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
