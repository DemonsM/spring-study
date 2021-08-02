package com.ink.springframework.beans;

/**
 * @author MT
 * @date 2021/8/2 17:28
 */
public class BeansException extends RuntimeException {
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable t) {
        super(msg, t);
    }
}
