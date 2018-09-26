package com.cartoon.tinytips.util;

/**
 * 使用方法
 * 1.通过静态方法getConstant获取对象
 * 2.通过对象调用公有方法获取私有变量的值
 */
public class FragmentConstant {

    private int defaultFragment;
    private int homePage;
    private int message;
    private int addNote;
    private int discover;
    private int personal;

    private static volatile FragmentConstant constant;

    public int getDefaultFragment() {
        return defaultFragment;
    }

    public int getHomePage() {
        return homePage;
    }

    public int getMessage() {
        return message;
    }

    public int getAddNote() {
        return addNote;
    }

    public int getDiscover() {
        return discover;
    }

    public int getPersonal() {
        return personal;
    }

    public static FragmentConstant getConstant(){

        if(JudgeEmpty.isEmpty(constant)){
            synchronized (FragmentConstant.class){
                if(JudgeEmpty.isEmpty(constant)){
                    constant=new FragmentConstant();
                }
            }
        }
        return constant;
    }

    private FragmentConstant(){
        defaultFragment=-1;
        homePage=0;
        message=1;
        addNote=2;
        discover=3;
        personal=4;
    }
}
