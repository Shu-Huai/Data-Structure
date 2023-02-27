package shuhuai.datastructure.sort;

import java.util.Random;

@SuppressWarnings({"unused", "unchecked"})
public class Sort<ElemType extends Comparable<ElemType>> {
    public Sort() {
    }

    public void bubbleSort(ElemType[] sequenceList) {
        for (int i = 0; i < sequenceList.length; i++) {
            for (int j = 0; j < sequenceList.length - i - 1; j++) {
                if (sequenceList[j].compareTo(sequenceList[j + 1]) > 0) {
                    ElemType temp = sequenceList[j];
                    sequenceList[j] = sequenceList[j + 1];
                    sequenceList[j + 1] = temp;
                }
            }
        }
    }

    public void quickSort(ElemType[] sequenceList) {
        quickSort(sequenceList, 0, sequenceList.length - 1);
    }

    public void quickSort(ElemType[] sequenceList, int low, int high) {
        if (low < high) {
            ElemType referenceValue = sequenceList[low];
            int i = low;
            int j = high;
            while (i < j) {
                while (i < j && sequenceList[j].compareTo(referenceValue) >= 0) {
                    j--;
                }
                if (i < j) {
                    sequenceList[i] = sequenceList[j];
                    i++;
                }
                while (i < j && sequenceList[i].compareTo(referenceValue) <= 0) {
                    i++;
                }
                if (i < j) {
                    sequenceList[j] = sequenceList[i];
                    j--;
                }
            }
            sequenceList[i] = referenceValue;
            quickSort(sequenceList, low, i - 1);
            quickSort(sequenceList, i + 1, high);
        }
    }

    public void straightInsertSort(ElemType[] sequenceList) {
        for (int i = 1; i < sequenceList.length; i++) {
            int j;
            ElemType temp = sequenceList[i];
            for (j = i - 1; j >= 0; j--) {
                if (sequenceList[j].compareTo(temp) <= 0) {
                    break;
                }
                sequenceList[j + 1] = sequenceList[j];
            }
            sequenceList[j + 1] = temp;
        }
    }

    public void binaryInsertSort(ElemType[] sequenceList) {
        for (int i = 1; i < sequenceList.length; i++) {
            int low = 0;
            int high = i - 1;
            ElemType key = sequenceList[i];
            while (low <= high) {
                int middle = (low + high) / 2;
                if (key.compareTo(sequenceList[middle]) < 0) {
                    high = middle - 1;
                } else if (key.compareTo(sequenceList[middle]) > 0) {
                    low = middle + 1;
                }
            }
            for (int j = i - 1; j >= low; j--) {
                sequenceList[j + 1] = sequenceList[j];
            }
            sequenceList[low] = key;
        }
    }

    public void TwoWayInsertSort(ElemType[] sequenceList) {
        ElemType[] result = (ElemType[]) new Comparable[sequenceList.length];
        int head = 0;
        int tail = 0;
        result[0] = sequenceList[0];
        for (int i = 1; i < sequenceList.length; i++) {
            ElemType temp = sequenceList[i];
            if (temp.compareTo(result[0]) > 0) {
                int j;
                for (j = tail; j > 0; j--) {
                    if (result[j].compareTo(temp) <= 0) {
                        break;
                    }
                    result[j + 1] = result[j];
                }
                result[j + 1] = temp;
                tail++;
            } else {
                if (head == 0) {
                    result[sequenceList.length - 1] = temp;
                    head = sequenceList.length - 1;
                } else {
                    int j;
                    for (j = head; j < sequenceList.length; j++) {
                        if (result[j].compareTo(temp) >= 0) {
                            break;
                        }
                        result[j - 1] = result[j];
                    }
                    result[j - 1] = temp;
                    head--;
                }
            }
        }
        int count = 0;
        for (int i = head; i != tail; i = (i + 1) % sequenceList.length) {
            sequenceList[count] = result[i];
            count++;
        }
        sequenceList[0] = result[head];
    }

    public void shellSort(ElemType[] sequenceList) {
        int distance = sequenceList.length / 2;
        while (distance > 0) {
            for (int i = distance; i < sequenceList.length; i++) {
                int j;
                ElemType temp = sequenceList[i];
                for (j = i - distance; j >= 0; j -= distance) {
                    if (sequenceList[j].compareTo(temp) <= 0) {
                        break;
                    }
                    sequenceList[j + distance] = sequenceList[j];
                }
                sequenceList[j + distance] = temp;
            }
            distance /= 2;
        }
    }

    public void merge(ElemType[] sequenceList, int low, int middle, int high) {
        ElemType[] result = (ElemType[]) new Comparable[high + 1];
        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (sequenceList[i].compareTo(sequenceList[j]) <= 0) {
                result[k] = sequenceList[i];
                i++;
            } else {
                result[k] = sequenceList[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            result[k] = sequenceList[i];
            k++;
            i++;
        }
        while (j <= high) {
            result[k] = sequenceList[j];
            k++;
            j++;
        }
        for (k = low; k <= high; k++) {
            sequenceList[k] = result[k];
        }
    }

    public void mergeSort(ElemType[] sequenceList) {
        int intervalLength = 1;
        while (intervalLength < sequenceList.length) {
            int index = 0;
            while (index + 2 * intervalLength <= sequenceList.length) {
                merge(sequenceList, index, index + intervalLength - 1, index + 2 * intervalLength - 1);
                index += 2 * intervalLength;
            }
            if (index + intervalLength < sequenceList.length) {
                merge(sequenceList, index, index + intervalLength - 1, sequenceList.length - 1);
            }
            intervalLength *= 2;
        }
    }

    public void mergeSortWithRecursion(ElemType[] sequenceList) {
        mergeSortWithRecursion(sequenceList, 0, sequenceList.length - 1);
    }

    public void mergeSortWithRecursion(ElemType[] sequenceList, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = (low + high) / 2;
        mergeSortWithRecursion(sequenceList, low, middle);
        mergeSortWithRecursion(sequenceList, middle + 1, high);
        merge(sequenceList, low, middle, high);
    }

    public void MonkeySort(ElemType[] sequenceList) {
        boolean isSorted = false;
        ElemType[] result = (ElemType[]) new Comparable[sequenceList.length];
        while (!isSorted) {
            boolean[] needIndex = new boolean[sequenceList.length];
            for (int i = 0; i < sequenceList.length; i++) {
                needIndex[i] = true;
            }
            for (int i = 0; i < sequenceList.length; i++) {
                Random rand = new Random();
                int index = rand.nextInt(sequenceList.length);
                if (needIndex[index]) {
                    result[i] = sequenceList[index];
                    needIndex[index] = false;
                } else {
                    i--;
                }
            }
            isSorted = true;
            for (int i = 0; i < sequenceList.length - 1; i++) {
                if (result[i].compareTo(result[i + 1]) > 0) {
                    isSorted = false;
                    break;
                }
            }
        }
        System.arraycopy(result, 0, sequenceList, 0, sequenceList.length);
    }

    public void countSort(ElemType[] sequenceList) {
        int[] indexes = new int[sequenceList.length];
        for (int i = 0; i < sequenceList.length; i++) {
            for (ElemType elemType : sequenceList) {
                if (elemType.compareTo(sequenceList[i]) < 0) {
                    indexes[i]++;
                }
            }
        }
        ElemType[] result = (ElemType[]) new Comparable[sequenceList.length];
        for (int i = 0; i < sequenceList.length; i++) {
            result[indexes[i]] = sequenceList[i];
        }
        System.arraycopy(result, 0, sequenceList, 0, sequenceList.length);
    }
}