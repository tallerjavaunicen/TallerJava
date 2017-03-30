package edu.isistan.producerconsumer;

public interface IBuffer<T> {

    T next();

    void put(T i);

}