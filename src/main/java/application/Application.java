package application;

import window.MainWindow;

/**
 *
 */
public class Application {
    public void run(){
        try {
            new MainWindow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}