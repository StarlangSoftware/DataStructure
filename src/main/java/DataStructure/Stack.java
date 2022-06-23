package DataStructure;

import java.util.ArrayList;

public class Stack<T>{

    private ArrayList<T> list = new ArrayList<>();

    public Stack(){

    }

    public void push(T item){
        list.add(item);
    }

    public T pop(){
        if (list.size() > 0){
            return list.remove(list.size() - 1);
        } else {
            return null;
        }
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}
