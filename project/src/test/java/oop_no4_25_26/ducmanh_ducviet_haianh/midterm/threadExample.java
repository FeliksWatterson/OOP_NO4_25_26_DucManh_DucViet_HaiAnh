package oop_no4_25_26.ducmanh_ducviet_haianh.midterm;

public class threadExample extends Thread{
public static int count = 0 ;
public static void main(String[] args) {
threadExample threadExp = new threadExample();
threadExp.start();
while (threadExp.isAlive()){
System.out.println("running...");
}
System.out.println("count "+ count); //1
count ++; //2
System.out.println("count after ++"+count); //2
}
public void run(){//overridden run method of thread
count ++; //1
}
}