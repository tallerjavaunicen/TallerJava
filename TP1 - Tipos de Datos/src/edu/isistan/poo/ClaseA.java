package edu.isistan.poo;

public abstract class ClaseA {

       public String metodo1(){
              return "ClaseA.metodo1()";
       }
       
       public String metodo2(){
              return "ClaseA.metodo2() llama a: \n\t" + this.metodo3();
       }
       
       public abstract String metodo3();
       
}