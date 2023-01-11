package org.example;


import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class MySketch extends PApplet {

    private ArrayList<Bola> balls = new ArrayList<>();
    int[] randomIntsArray = IntStream.generate(() -> new Random().nextInt(10)).limit(10).toArray();

    Bola[] bolas;

    public void setup() {
        size(1200, 1000);
        for (int i = 0; i < randomIntsArray.length; i++) {
            Integer integer = Integer.valueOf(i);
            balls.add(new Bola(this, (integer.floatValue() * 100) + 140, height / 2, randomIntsArray[i]));
        }
    }

    public void draw() {
        background(641);
        int i = 0;
        for (Bola b : balls) {
            b.updatePosition(i);
            b.render();
            i++;
        }
    }

    public void mouseClicked() {
        try {
            startQuickStart(0, balls.size() - 1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n\n---------Processed sorted Array---------");
        for (Bola b : balls) {
            System.out.print(b.getValue() + " ");
        }
    }

    public static void main(String[] args) {
        String[] processingArgs = {"MySketch"};
        MySketch mySketch = new MySketch();
        PApplet.runSketch(processingArgs, mySketch);
    }

    public void startQuickStart(int start, int end) throws InterruptedException {
        int q;
        if (start < end) {
            q = partition(start, end);
            startQuickStart(start, q);
            startQuickStart(q + 1, end);
        }
    }

    int partition(int start, int end) throws InterruptedException {
        System.out.println("\n---------Iteration Starts----------");
        System.out.println("\nSorting Window from index number:" + start + " to " + end);

        int init = start;
        int length = end;

        Random r = new Random();
        int pivotIndex = nextIntInRange(start, end, r);
        int pivot = balls.get(pivotIndex).getValue();
        balls.get(pivotIndex).setPivot(true);

        System.out.println("Pivot Element " + pivot + " at index:" + pivotIndex);

        while (true) {
            while (balls.get(length).getValue() > pivot && length > start) {
                length--;
            }

            while (balls.get(init).getValue() < pivot && init < end) {
                init++;
            }

            if (init < length) {
                Bola inicio = balls.get(init);
                Bola finalB = balls.get(length);
                Bola tempInicio = balls.get(init);

                balls.set(init, finalB);
                balls.get(init).updatePosition(init);
                balls.set(length, inicio);
                balls.get(length).updatePosition(length);

                length--;
                init++;

//                System.out.println("\nAfter Swapping");
//                for (int i = start; i <= end; i++) {
//                    System.out.print(balls.get(i).getValue() + " ");
//                }
                balls.get(pivotIndex).setPivot(false);
            } else {
                System.out.println("\n---------Iteration Ends---------");
                return length;
            }
        }
    }

    // Below method is to just find random integer from given range
    static int nextIntInRange(int min, int max, Random rng) {
        if (min > max) {
            throw new IllegalArgumentException("Cannot draw random int from invalid range [" + min + ", " + max + "].");
        }
        int diff = max - min;
        if (diff >= 0 && diff != Integer.MAX_VALUE) {
            return (min + rng.nextInt(diff + 1));
        }
        int i;
        do {
            i = rng.nextInt();
        } while (i < min || i > max);
        return i;
    }


}