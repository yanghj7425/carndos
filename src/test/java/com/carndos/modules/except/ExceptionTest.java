package com.carndos.modules.except;

import org.junit.Test;

public class ExceptionTest {

    @Test
    public void except() {
        System.out.println(t());
    }

    private int t() {
        int a = 0;
        try {
            a = 10;
            return a;
        } finally {
            a = 12;
            return a;
        }


    }

}
