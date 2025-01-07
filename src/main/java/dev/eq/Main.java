package dev.eq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Main {


    public static void main(String[] args) {
       // SpringApplication.run(Main.class, args);
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        String date = "2025-01-04";
        LocalDate localDate = LocalDate.parse(date);
        System.out.println("Locate date is:"+localDate.toString());
        System.out.println("Year is:"+localDate.getYear());
        System.out.println("Month is:"+localDate.getMonth());
        System.out.println("Day is:"+localDate.getDayOfMonth());



        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}