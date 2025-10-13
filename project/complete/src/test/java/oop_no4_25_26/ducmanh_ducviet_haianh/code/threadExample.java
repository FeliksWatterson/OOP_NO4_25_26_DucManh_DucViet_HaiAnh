package oop_no4_25_26.ducmanh_ducviet_haianh.code;

public class threadExample extends Thread{
public static int count = 0 ;
    public static void main(String[] args) {
        threadExample threadExp = new threadExample();
        threadExp.start();
        System.out.println("count "+ count); //0
        count ++; //2
        System.out.println("In gia tri count: "+count); //2
        System.out.println("In gia tri count dong so 11: "+count);
    }

        public void run(){//overridden run method of thread
        count ++; //1
        }
} 