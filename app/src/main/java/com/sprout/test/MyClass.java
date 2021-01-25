package com.sprout.test;

public class MyClass {

    private void test(int index){
        if(index > 0){
            boolean bool = A(index);
            if(bool) return;
        }
    }

    private boolean A(int index){
        if(index == 1) return true;
        return false;
    }
}
