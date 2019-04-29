package unicen.tallerjava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParentAndChildren {
    public static class Parent {
        String nombre = "Alguien";

        List<Son> children = new ArrayList<>();

        private Son createSon() {
            Son son = new Son();
            this.children.add(son);
            son.parent = this;
            return son;
        }
    }

    public static class Son {
        Parent parent;
        String name = "Luke";
    }

    // Tiene un memory leak!
    public static void main(String[] args) throws IOException {
        Parent p = new Parent();
        Son son = p.createSon();
        System.out.println(son.name);
        for (int i = 0; i < 50000; i++)
            p.createSon();

        son = null;

        p = new Parent();
        for (int i = 0; i < 50000; i++)
            p.createSon();
        System.out.println(p.nombre);
        System.out.println(son.name);
    }
}