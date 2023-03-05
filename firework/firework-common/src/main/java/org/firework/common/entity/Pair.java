package org.firework.common.entity;

/**
 * 常用类=一对
 */
public class Pair<L,R> {

    private final L left;

    private final R right;

    public Pair(L left, R right){
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
}
