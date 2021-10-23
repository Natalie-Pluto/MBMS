package MTBMS;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Timer {
    // Timer for user's input
    // Types are 'c' for customer, 's' for staff, 'm' for manager and 'g' for guest
    // Types are for identifying which interface should this user be taken to after input time out
    public static String timer(String type) throws InterruptedException {
        BlockingDeque<String> deque = new LinkedBlockingDeque<>();

        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String input;
            try {
                do {
                    if (System.in.available() > 0) {
                        input = scanner.nextLine();
                        deque.add(input);
                    } else
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            break;
                        }
                } while (true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        thread.start();
        int i = 0;
        String str;
        do {
            str = deque.poll(120, TimeUnit.SECONDS);
            i++;
        } while (i < 1);

        if(str == null) {
            if (type.equals("g")) {
                System.out.println(RED_BOLD + "Time out! Returning..." + ANSI_RESET);
                BookingSystem.getGreeting();
                BookingSystem.options();
            } else if (type.equals("c")) {
                System.out.println(RED_BOLD + "Time out! Returning..." + ANSI_RESET);
                // TODO return to customer main page
            } else if (type.equals("s")) {
                System.out.println(RED_BOLD + "Time out! Returning..." + ANSI_RESET);
                // TODO return to staff main page
            } else if (type.equals("m")) {
                System.out.println(RED_BOLD + "Time out! Returning..." + ANSI_RESET);
                // TODO return to manager main page
            }

        }

        thread.interrupt();
        return str;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;31m";    // RED
}