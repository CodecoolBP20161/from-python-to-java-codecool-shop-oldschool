package com.codecool.shop.model;


import java.util.HashMap;


public class IdFactory {

    public static IdFactory instance;
    private HashMap<Class, Integer> nextid = new HashMap<Class, Integer>();

    private IdFactory(){

    }

    public static synchronized IdFactory getInstance(){
        if(instance == null) {
            instance = new IdFactory();
        }
        return instance;
    }

    public int id(Class key){
        int result;
        if (this.nextid.containsKey(key))
            result = getNewId(key);
        else
            result = createNewId(key);
        return result;
    }

    private int createNewId(Class key){
        this.nextid.put(key, 1);
        return this.nextid.get(key);
    }

    private int getNewId(Class key){
        this.nextid.put(key, this.nextid.get(key) + 1);
        return this.nextid.get(key);
    }
}
//    public ProductCategory(){
//        this.id = IdFactory.getInstance().id(this.getClass());
//
//    }