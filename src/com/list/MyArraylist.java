package com.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArraylist<AnyType> implements Iterable<AnyType> {

    private static final int DEAULT_CAPACITY=10;

    private int theSize;
    private AnyType[] theItems;

    public MyArraylist(){
        doclear();
    }
    public void clear(){
        doclear();
    }
    private void doclear(){
        theSize=0;

    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return theSize==0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public AnyType get(int idx){
        if(idx<0||idx>=size())
            throw new  ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal){
        if (idx<0 || idx>=size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old=get(idx);
        theItems[idx]=newVal;
        return old;
    }

    private void ensureCapacity(int newCapacity){
        if(newCapacity <DEAULT_CAPACITY)
            return;
        AnyType []old=theItems;
        theItems= (AnyType[]) new Object[newCapacity];
        for(int i=0;i<size();i++){
            theItems[i]=old[i];
        }
    }

    public boolean add(AnyType x){
        return true;
    }
    public void add(int idx,AnyType newVal){
        if(theItems.length==size())
            ensureCapacity(size()*2+1);
        for (int i=theSize;i>idx;i--)
            theItems[i]=theItems[i-1];
        theItems[idx]=newVal;
        theSize++;
    }
    public AnyType remove(int idx){
        AnyType removedItem=theItems[idx];
        for(int i=idx;i<size();i++)
            theItems[i]=theItems[i+1];
        theSize--;
        return removedItem;
    }


    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListerator();
    }

    private class ArrayListerator implements Iterator<AnyType> {
        private int current=0;
        @Override
        public boolean hasNext() {
            return current<size() ;
        }

        @Override
        public AnyType next() {
            if (!hasNext())
                throw  new NoSuchElementException();
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArraylist.this.remove(--current);

        }
    }
}
