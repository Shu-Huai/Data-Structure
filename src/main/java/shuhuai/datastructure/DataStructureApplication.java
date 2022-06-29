package shuhuai.datastructure;

import shuhuai.datastructure.linearlist.linklist.LinkListTest;
import shuhuai.datastructure.linearlist.sequencelist.SequenceListTest;

import java.util.Scanner;

public class DataStructureApplication {
    public static void main(String[] args) {
        char functionSelect = 0;
        Scanner scanner = new Scanner(System.in);
        while (functionSelect != '0') {
            System.out.println("1. 测试顺序表；");
            System.out.println("2. 测试链表；");
            System.out.println("0. 退出。");
            System.out.print("请选择功能：");
            functionSelect = scanner.next().charAt(0);
            switch (functionSelect) {
                case '1' -> {
                    SequenceListTest sequenceListTest = new SequenceListTest();
                    sequenceListTest.run();
                }
                case '2' -> {
                    LinkListTest linkListTest = new LinkListTest();
                    linkListTest.run();
                }
            }
        }
    }
}