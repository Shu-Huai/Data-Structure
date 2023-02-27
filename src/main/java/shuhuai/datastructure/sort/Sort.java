package shuhuai.datastructure.sort;

import shuhuai.datastructure.array.Array;

import java.util.Random;

@SuppressWarnings({"unused", "unchecked"})
public class Sort<ElemType extends Comparable<ElemType>> {
    public Sort() {
    }

    public void bubbleSort(Array<ElemType> sequenceList) {
        for (int i = 0; i < sequenceList.getLength(); i++) {
            for (int j = 0; j < sequenceList.getLength() - i - 1; j++) {
                if (sequenceList.get(j).compareTo(sequenceList.get(j + 1)) > 0) {
                    ElemType temp = sequenceList.get(j);
                    sequenceList.set(j, sequenceList.get(j + 1));
                    sequenceList.set(j + 1, temp);
                }
            }
        }
    }

    public void quickSort(Array<ElemType> sequenceList) {
        quickSort(sequenceList, 0, sequenceList.getLength() - 1);
    }

    public void quickSort(Array<ElemType> sequenceList, int low, int high) {
        if (low < high) {
            ElemType referenceValue = sequenceList.get(low);
            int i = low;
            int j = high;
            while (i < j) {
                while (i < j && sequenceList.get(j).compareTo(referenceValue) >= 0) {
                    j--;
                }
                if (i < j) {
                    sequenceList.set(i, sequenceList.get(j));
                    i++;
                }
                while (i < j && sequenceList.get(i).compareTo(referenceValue) <= 0) {
                    i++;
                }
                if (i < j) {
                    sequenceList.set(j, sequenceList.get(i));
                    j--;
                }
            }
            sequenceList.set(i, referenceValue);
            quickSort(sequenceList, low, i - 1);
            quickSort(sequenceList, i + 1, high);
        }
    }

    public void straightInsertSort(Array<ElemType> sequenceList) {
        for (int i = 1; i < sequenceList.getLength(); i++) {
            int j;
            ElemType temp = sequenceList.get(i);
            for (j = i - 1; j >= 0; j--) {
                if (sequenceList.get(j).compareTo(temp) <= 0) {
                    break;
                }
                sequenceList.set(j + 1, sequenceList.get(j));
            }
            sequenceList.set(j + 1, temp);
        }
    }

    public void binaryInsertSort(Array<ElemType> sequenceList) {
        for (int i = 1; i < sequenceList.getLength(); i++) {
            int low = 0;
            int high = i - 1;
            ElemType key = sequenceList.get(i);
            while (low <= high) {
                int middle = (low + high) / 2;
                if (key.compareTo(sequenceList.get(middle)) < 0) {
                    high = middle - 1;
                } else if (key.compareTo(sequenceList.get(middle)) > 0) {
                    low = middle + 1;
                }
            }
            for (int j = i - 1; j >= low; j--) {
                sequenceList.set(j + 1, sequenceList.get(j));
            }
            sequenceList.set(low, key);
        }
    }

    public void TwoWayInsertSort(Array<ElemType> sequenceList) {
        ElemType[] result = (ElemType[]) new Comparable[sequenceList.getLength()];
        int head = 0;
        int tail = 0;
        result[0] = sequenceList.get(0);
        for (int i = 1; i < sequenceList.getLength(); i++) {
            ElemType temp = sequenceList.get(i);
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
                    result[sequenceList.getLength() - 1] = temp;
                    head = sequenceList.getLength() - 1;
                } else {
                    int j;
                    for (j = head; j < sequenceList.getLength(); j++) {
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
        for (int i = head; i != tail; i = (i + 1) % sequenceList.getLength()) {
            sequenceList.set(count, result[i]);
            count++;
        }
        sequenceList.set(0, result[head]);
    }

    public void shellSort(Array<ElemType> sequenceList) {
        int distance = sequenceList.getLength() / 2;
        while (distance > 0) {
            for (int i = distance; i < sequenceList.getLength(); i++) {
                int j;
                ElemType temp = sequenceList.get(i);
                for (j = i - distance; j >= 0; j -= distance) {
                    if (sequenceList.get(j).compareTo(temp) <= 0) {
                        break;
                    }
                    sequenceList.set(j + distance, sequenceList.get(j));
                }
                sequenceList.set(j + distance, temp);
            }
            distance /= 2;
        }
    }

    public void merge(Array<ElemType> sequenceList, int low, int middle, int high) {
        ElemType[] result = (ElemType[]) new Comparable[high + 1];
        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (sequenceList.get(i).compareTo(sequenceList.get(j)) <= 0) {
                result[k] = sequenceList.get(i);
                i++;
            } else {
                result[k] = sequenceList.get(j);
                j++;
            }
            k++;
        }
        while (i <= middle) {
            result[k] = sequenceList.get(i);
            k++;
            i++;
        }
        while (j <= high) {
            result[k] = sequenceList.get(j);
            k++;
            j++;
        }
        for (k = low; k <= high; k++) {
            sequenceList.set(k, result[k]);
        }
    }

    public void mergeSort(Array<ElemType> sequenceList) {
        int intervalLength = 1;
        while (intervalLength < sequenceList.getLength()) {
            int index = 0;
            while (index + 2 * intervalLength <= sequenceList.getLength()) {
                merge(sequenceList, index, index + intervalLength - 1, index + 2 * intervalLength - 1);
                index += 2 * intervalLength;
            }
            if (index + intervalLength < sequenceList.getLength()) {
                merge(sequenceList, index, index + intervalLength - 1, sequenceList.getLength() - 1);
            }
            intervalLength *= 2;
        }
    }

    public void mergeSortWithRecursion(Array<ElemType> sequenceList) {
        mergeSortWithRecursion(sequenceList, 0, sequenceList.getLength() - 1);
    }

    public void mergeSortWithRecursion(Array<ElemType> sequenceList, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = (low + high) / 2;
        mergeSortWithRecursion(sequenceList, low, middle);
        mergeSortWithRecursion(sequenceList, middle + 1, high);
        merge(sequenceList, low, middle, high);
    }

    public void MonkeySort(Array<ElemType> sequenceList) {
        boolean isSorted = false;
        ElemType[] result = (ElemType[]) new Comparable[sequenceList.getLength()];
        while (!isSorted) {
            boolean[] needIndex = new boolean[sequenceList.getLength()];
            for (int i = 0; i < sequenceList.getLength(); i++) {
                needIndex[i] = true;
            }
            for (int i = 0; i < sequenceList.getLength(); i++) {
                Random rand = new Random();
                int index = rand.nextInt(sequenceList.getLength());
                if (needIndex[index]) {
                    result[i] = sequenceList.get(index);
                    needIndex[index] = false;
                } else {
                    i--;
                }
            }
            isSorted = true;
            for (int i = 0; i < sequenceList.getLength() - 1; i++) {
                if (result[i].compareTo(result[i + 1]) > 0) {
                    isSorted = false;
                    break;
                }
            }
        }
        for (int i = 0; i < sequenceList.getLength(); i++) {
            sequenceList.set(i, result[i]);
        }
    }

    public void countSort(Array<ElemType> sequenceList) {
        int[] indexes = new int[sequenceList.getLength()];
        for (int i = 0; i < sequenceList.getLength(); i++) {
            for (int j = 0; j < sequenceList.getLength(); j++) {
                if (sequenceList.get(j).compareTo(sequenceList.get(i)) < 0) {
                    indexes[i]++;
                }
            }
        }
        Array<ElemType> result = new Array<>(sequenceList.getLength());
        for (int i = 0; i < sequenceList.getLength(); i++) {
            result.set(indexes[i], sequenceList.get(i));
        }
        sequenceList.copy(result);
    }
}