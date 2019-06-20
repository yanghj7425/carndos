package com.carndos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {

        Long[] arr = new Long[]{2L, 3L, 4L};
        List<Long> longs = Arrays.asList(arr);
        List<Object> list = new ArrayList<>();

        list.add(longs);



        List<List<Integer>> collect = list.stream()
                .map(v -> ((Collection<Integer>) v).stream().map(k -> Integer.parseInt(k.toString()) * 2
                ).collect(Collectors.toList()))
                .collect(Collectors.toList());


//        Random random = new Random();
//
//        List<Integer> collect = random.ints().limit(100).boxed().filter(v -> v > 0).filter(v -> v % 2 == 0).collect(Collectors.toList());
//
//        for (int a : collect) {
//            System.out.println(a);
//        }

//        stream();
    }

    public static void stream() {
        List<String> list = Arrays.asList("hello", "nice", "to", "meet", "you", "");
        List<String> collect = list.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        System.out.println(collect.size());

        for (String str : collect) {
            System.out.println(str);
        }
    }


    public void lambda() {
        MathOperation add = (int a, int b) -> a + b;
        System.out.println(add.operation(1, 3));

        Greeting greeting = message -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(message);
            }
        };
        greeting.saySomeThing("Nice to meet you ~");
    }

}

interface MathOperation {
    int operation(int a, int b);
}


interface Greeting {
    void saySomeThing(String message);
}
