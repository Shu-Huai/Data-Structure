package shuhuai.datastructure.linearlist.sequencelist;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.RangeException;

import java.util.Scanner;

@SuppressWarnings({"unused"})
public class SequenceListTest {
    private SequenceList<Integer> sequenceList;

    public SequenceListTest() {
        sequenceList = new SequenceList<>();
        run();
    }

    public SequenceList<Integer> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(SequenceList<Integer> sequenceList) {
        this.sequenceList = sequenceList;
    }

    public void run() {
        char functionSelect = 0;
        Scanner scanner = new Scanner(System.in);
        while (functionSelect != '0') {
            System.out.println("1. 生成顺序表；");
            System.out.println("2. 遍历顺序表；");
            System.out.println("3. 取元素值；");
            System.out.println("4. 改变元素值；");
            System.out.println("5. 删除元素；");
            System.out.println("6. 插入元素；");
            System.out.println("7. 取元素下标；");
            System.out.println("8. 取顺序表长度；");
            System.out.println("9. 删除重复元素；");
            System.out.println("a. 逆置；");
            System.out.println("b. 删除范围内的元素；");
            System.out.println("c. 排序；");
            System.out.println("0. 退出。");
            System.out.print("请选择功能：");
            functionSelect = scanner.next().charAt(0);
            switch (functionSelect) {
                case '1' -> {
                    sequenceList.clear();
                    System.out.print("输入元素（输入0时停止）：");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) != ' ') {
                            try {
                                int elem = Integer.parseInt(String.valueOf(input.charAt(i)));
                                sequenceList.appendElem(elem);
                            } catch (OverFlowException e) {
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e) {
                                System.out.println("输入有误");
                            }
                        }
                    }
                }
                case '2' -> {
                    sequenceList.traverse();
                    System.out.println("成功");
                }
                case '3' -> {
                    System.out.print("请输入元素的下标：");
                    int index = scanner.nextInt();
                    try {
                        int elem = sequenceList.getElem(index);
                        System.out.println("元素值为：" + elem);
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '4' -> {
                    System.out.println("请输入元素的下标：");
                    int index = scanner.nextInt();
                    System.out.println("请输入元素值：");
                    int elem = scanner.nextInt();
                    try {
                        sequenceList.setElem(elem, index);
                        System.out.println("成功");
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '5' -> {
                    System.out.println("请输入元素的下标：");
                    int index = scanner.nextInt();
                    try {
                        sequenceList.deleteElem(index);
                        System.out.println("成功");
                    } catch (RangeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '6' -> {
                    System.out.print("请输入元素的下标：");
                    int index = scanner.nextInt();
                    System.out.print("请输入元素值：");
                    int elem = scanner.nextInt();
                    try {
                        sequenceList.insertElem(elem, index);
                        System.out.println("成功");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case '7' -> {
                    System.out.print("请输入元素值：");
                    int elem = scanner.nextInt();
                    int index = sequenceList.findElem(elem);
                    if (index == -1) {
                        System.out.println("没有找到");
                    } else {
                        System.out.println("下标为：" + index);
                    }
                }
                case '8' -> {
                    int length = sequenceList.getLength();
                    System.out.println("顺序表长度为：" + length);
                }
                case '9' -> {
                    sequenceList.deleteRepeat();
                    System.out.println("成功");
                }
                case 'a' -> {
                    sequenceList.reverse();
                    System.out.println("成功");
                }
                case 'b' -> {
                    System.out.print("请输入最小元素：");
                    int minElem = scanner.nextInt();
                    System.out.print("请输入最大元素：");
                    int maxElem = scanner.nextInt();
                    try {
                        sequenceList.deleteBetween(minElem, maxElem);
                        System.out.println("成功");
                    } catch (RangeException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 'c' -> {
                    sequenceList.sort();
                    System.out.println("成功");
                }
            }
        }
    }
}