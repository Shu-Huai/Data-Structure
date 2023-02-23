package shuhuai.datastructure.linearlist.linklist;

import shuhuai.datastructure.exceptions.RangeException;

import java.util.Scanner;

@SuppressWarnings({"unused"})
public class LinkListTest {
    private LinkList<Integer> linkList;

    public LinkListTest() {
        linkList = new LinkList<>();
    }

    public LinkList<Integer> getLinkList() {
        return linkList;
    }

    public void setLinkList(LinkList<Integer> linkList) {
        this.linkList = linkList;
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
            System.out.println("a. 删除范围内的元素；");
            System.out.println("b. 将两个链表合并为一个；");
            System.out.println("0. 退出。");
            System.out.print("请选择功能：");
            functionSelect = scanner.next().charAt(0);
            switch (functionSelect) {
                case '1' -> {
                    linkList.clear();
                    System.out.print("输入元素（输入0时停止）：");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) != ' ') {
                            try {
                                int elem = Integer.parseInt(String.valueOf(input.charAt(i)));
                                linkList.appendElem(elem);
                            } catch (NumberFormatException e) {
                                System.out.println("输入有误");
                            }
                        }
                    }
                }
                case '2' -> {
                    linkList.traverse();
                    System.out.println("成功。");
                }
                case '3' -> {
                    System.out.print("输入下标：");
                    int index = scanner.nextInt();
                    try {
                        int elem = linkList.getElem(index);
                        System.out.println("元素是：" + elem);
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '4' -> {
                    System.out.print("输入下标：");
                    int index = scanner.nextInt();
                    System.out.print("输入元素：");
                    int elem = scanner.nextInt();
                    try {
                        linkList.setElem(index, elem);
                        System.out.println("成功。");
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '5' -> {
                    System.out.print("输入下标：");
                    int index = scanner.nextInt();
                    try {
                        linkList.deleteElem(index);
                        System.out.println("成功。");
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '6' -> {
                    System.out.print("输入下标：");
                    int index = scanner.nextInt();
                    System.out.print("输入元素：");
                    int elem = scanner.nextInt();
                    try {
                        linkList.insertElem(index, elem);
                        System.out.println("成功。");
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '7' -> {
                    System.out.print("输入元素：");
                    int elem = scanner.nextInt();
                    int index = linkList.findElem(elem);
                    System.out.println("下标是：" + index);
                }
                case '8' -> System.out.println("链表长度是：" + linkList.getLength());
                case '9' -> {
                    linkList.reverse();
                    System.out.println("成功。");
                }
                case 'a' -> {
                    System.out.print("输入最大值：");
                    int max = scanner.nextInt();
                    System.out.print("输入最小值：");
                    int min = scanner.nextInt();
                    try {
                        linkList.deleteBetween(max, min);
                        System.out.println("成功。");
                    } catch (RangeException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 'b' -> {
                    LinkList<Integer> subList = new LinkList<>();
                    System.out.print("输入元素（输入0时停止）：");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) != ' ') {
                            try {
                                int elem = Integer.parseInt(String.valueOf(input.charAt(i)));
                                subList.appendElem(elem);
                            } catch (NumberFormatException e) {
                                System.out.println("输入有误");
                            }
                        }
                    }
                    linkList.extend(subList);
                    System.out.println("成功。");
                }
            }
        }
    }
}