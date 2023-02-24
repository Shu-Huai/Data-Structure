package shuhuai.datastructure.stack.sharesequencestack;

import shuhuai.datastructure.exceptions.NotExistException;
import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;

import java.util.function.Function;

@SuppressWarnings({"unused", "unchecked"})
public class ShareSequenceStack<ElemType> {
    protected int firstTop_;
    protected int secondTop_;
    protected ElemType[] elems_;

    public ShareSequenceStack() {
        firstTop_ = -1;
        secondTop_ = 1000;
        elems_ = (ElemType[]) new Object[1000];
    }

    public ShareSequenceStack(int maxSize) {
        firstTop_ = -1;
        secondTop_ = maxSize;
        elems_ = (ElemType[]) new Object[maxSize];
    }

    public ShareSequenceStack(ShareSequenceStack<ElemType> stack) {
        copy(stack);
    }

    public void clear(int number) throws NotExistException {
        if (number != 0 && number != 1) {
            throw new NotExistException("栈不存在");
        }
        if (number == 0) {
            firstTop_ = -1;
        } else {
            secondTop_ = elems_.length;
        }
    }

    public boolean isEmpty(int number) throws NotExistException {
        if (number != 0 && number != 1) {
            throw new NotExistException("栈不存在");
        }
        if (number == 0) {
            return firstTop_ == -1;
        }
        return secondTop_ == elems_.length;
    }

    public void traverse(int number) throws NotExistException {
        if (number != 0 && number != 1) {
            throw new NotExistException("栈不存在");
        }
        traverse(number, value -> {
            System.out.print(value);
            return null;
        });
    }

    public void traverse(int number, Function<String, Void> output) throws NotExistException {
        if (number != 0 && number != 1) {
            throw new NotExistException("栈不存在");
        }
    }

    public void push(int number, ElemType elem) throws NotExistException, OverFlowException {
        if (number != 0 && number != 1) {
            throw new NotExistException("栈不存在");
        }
        if (firstTop_ == secondTop_ - 1) {
            throw new OverFlowException("栈满");
        }
        if (number == 0) {
            elems_[++firstTop_] = elem;
        } else {
            elems_[--secondTop_] = elem;
        }
    }

    public ElemType pop(int number) throws NotExistException, UnderFlowException {
        if (number != 0 && number != 1) {
            throw new NotExistException("栈不存在");
        }
        if (isEmpty(number)) {
            throw new UnderFlowException("栈空");
        }
        if (number == 0) {
            return elems_[firstTop_--];
        }
        return elems_[secondTop_++];

    }

    public int getLength(int number) throws NotExistException {
        if (number != 0 && number != 1) {
            throw new NotExistException("栈不存在");
        }
        if (number == 0) {
            return firstTop_ + 1;
        }
        return elems_.length - secondTop_;
    }

    public ElemType getTop(int number) throws UnderFlowException, NotExistException {
        if (number != 0 && number != 1) {
            throw new NotExistException("栈不存在");
        }
        if (isEmpty(number)) {
            throw new UnderFlowException("栈空");
        }
        if (number == 0) {
            return elems_[firstTop_];
        }
        return elems_[secondTop_];
    }

    public void copy(ShareSequenceStack<ElemType> stack) {
        if (stack == null) {
            return;
        }
        firstTop_ = stack.firstTop_;
        secondTop_ = stack.secondTop_;
        elems_ = (ElemType[]) new Object[stack.elems_.length];
        if (firstTop_ + 1 >= 0) {
            System.arraycopy(stack.elems_, 0, elems_, 0, firstTop_ + 1);
        }
        if (elems_.length - secondTop_ >= 0) {
            System.arraycopy(stack.elems_, secondTop_, elems_, secondTop_, elems_.length - secondTop_);
        }
    }
}