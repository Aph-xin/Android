package com.example.lab4;

public class MyService extends Thread {
    private final Object lock = new Object();
    public boolean c_stop=false;

    public void countstop(){
        c_stop=true;
    }
    public void resumecount() {
        c_stop=false;
        synchronized (lock){
            lock.notifyAll();
        }
    }

    void onPause() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        super.run();
    }
}


