package shuhuai.datastructure.linearlist.linklistwithouthead;

import shuhuai.datastructure.exceptions.RangeException;

import java.util.Scanner;

public class LinkListWithoutHeadTest {
    LinkListWithoutHead<Integer> linkListWithoutHead;

    public LinkListWithoutHeadTest() {
        linkListWithoutHead = new LinkListWithoutHead<>();
    }

    public LinkListWithoutHead<Integer> getLinkListWithoutHead() {
        return linkListWithoutHead;
    }

    public void setLinkListWithoutHead(LinkListWithoutHead<Integer> linkListWithoutHead) {
        this.linkListWithoutHead = linkListWithoutHead;
    }

    public void run() {
        char functionSelect = 0;
        Scanner scanner = new Scanner(System.in);
        while (functionSelect != '0') {
            System.out.println("1. 生成链表；");
            System.out.println("2. 遍历链表；");
            System.out.println("3. 取元素；");
            System.out.println("4. 改变元素；");
            System.out.println("5. 删除元素；");
            System.out.println("6. 插入元素；");
            System.out.println("7. 取元素下标；");
            System.out.println("8. 取链表长度；");
            System.out.println("9. 逆置；");
            System.out.println("a. 将两个链表合并为一个；");
            System.out.println("0. 退出。");
            System.out.print("请选择功能：");
            functionSelect = scanner.next().charAt(0);
            switch (functionSelect) {
                case '1' -> {
                    linkListWithoutHead.clear();
                    System.out.print("输入元素（输入0时停止）：");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) != ' ') {
                            try {
                                Integer elem = Integer.parseInt(String.valueOf(input.charAt(i)));
                                linkListWithoutHead.appendElem(elem);
                            } catch (NumberFormatException e) {
                                System.out.println("输入有误");
                            }
                        }
                    }
                }
                case '2' -> {
                    linkListWithoutHead.traverse();
                    System.out.println("成功。");
                }
                case '3' -> {
                    System.out.print("输入下标：");
                    Integer index = scanner.nextInt();
                    try {
                        Integer elem = linkListWithoutHead.getElem(index);
                        System.out.println("元素是：" + elem);
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '4' -> {
                    System.out.print("输入下标：");
                    Integer index = scanner.nextInt();
                    System.out.print("输入元素：");
                    Integer elem = scanner.nextInt();
                    try {
                        linkListWithoutHead.setElem(index, elem);
                        System.out.println("成功。");
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '5' -> {
                    System.out.print("输入下标：");
                    Integer index = scanner.nextInt();
                    try {
                        linkListWithoutHead.deleteElem(index);
                        System.out.println("成功。");
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '6' -> {
                    System.out.print("输入下标：");
                    Integer index = scanner.nextInt();
                    System.out.print("输入元素：");
                    Integer elem = scanner.nextInt();
                    try {
                        linkListWithoutHead.insertElem(index, elem);
                        System.out.println("成功。");
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '7' -> {
                    System.out.print("输入元素：");
                    Integer elem = scanner.nextInt();
                    Integer index = linkListWithoutHead.getIndex(elem);
                    System.out.println("下标是：" + index);
                }
                case '8' -> System.out.println("链表长度是：" + linkListWithoutHead.getLength());
                case '9' -> {
                    linkListWithoutHead.reverse();
                    System.out.println("成功。");
                }
                case 'a' -> {
                    LinkListWithoutHead<Integer> subList = new LinkListWithoutHead<>();
                    System.out.print("输入元素（输入0时停止）：");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) != ' ') {
                            try {
                                Integer elem = Integer.parseInt(String.valueOf(input.charAt(i)));
                                subList.appendElem(elem);
                            } catch (NumberFormatException e) {
                                System.out.println("输入有误");
                            }
                        }
                    }
                    linkListWithoutHead.extend(subList);
                    System.out.println("成功。");
                }
            }
        }
    }
}