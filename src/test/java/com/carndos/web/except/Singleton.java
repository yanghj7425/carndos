package com.carndos.web.except;

public class Singleton {
    private Singleton() {
        System.out.println("Singleton is created");
    }

    private static class SingletonHolder {
        private static Singleton singleton = new Singleton();
    }

    public static Singleton newInstance() {
        return SingletonHolder.singleton;
    }
}
