package ch.bfh.cssem.lambdaexpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CollectionsClass {

  public static void main (String []args) {

    List<Integer> myColl = new ArrayList<>(Arrays.asList(1, 3, 52, 47, 8, 2, 15, 38, 15, 7, 42, 31, 43, 5));
    myColl.removeIf(number -> number > 20);

    for (Integer number : myColl) {
      System.out.println("By for loop: " + number);
    }

    myColl.forEach((number) -> System.out.println("By lambda expression: " + number));
  }

}
