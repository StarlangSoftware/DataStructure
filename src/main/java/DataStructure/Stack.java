package DataStructure;

import java.util.ArrayList;

public class Stack<T>{

    private final ArrayList<T> list = new ArrayList<>();

    public Stack(){

    }

    public void push(T item){
        list.add(item);
    }

    public T pop(){
        if (!list.isEmpty()){
            return list.remove(list.size() - 1);
        } else {
            return null;
        }
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}
