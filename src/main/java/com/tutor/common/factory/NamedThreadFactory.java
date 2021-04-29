package com.tutor.common.factory;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
    public NamedThreadFactory(String n){

    }

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
