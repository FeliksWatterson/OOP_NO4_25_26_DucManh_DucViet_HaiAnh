package oop_no4_25_26.ducmanh_ducviet_haianh.code.component;

import java.io.File;
import java.io.FileInputStream;
public class fileReader {
public static void main(String[] args) {
File f = new File("final/src/test.txt");
try {
FileInputStream fis = new FileInputStream(f);
int c;
while ((c = fis.read()) != -1) {
System.out.println((char) c);
}
} catch (Exception e) {
e.printStackTrace();
}} }