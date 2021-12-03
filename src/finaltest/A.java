package finaltest;

import java.io.IOException;

public class A {
    public static void main(String[] args) throws IOException {

        MyFrame mf = new MyFrame();
        MessengerA a = new MessengerA();
        a.process();
    }
}