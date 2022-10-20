package com.example.nextstep;

import java.util.ArrayList;
import java.util.Scanner;

public class BaseBallGame {
    public static void main(String[] args) {
        int number = 123;

        while (true){
            Scanner sc = new Scanner(System.in);
            if(sc.nextInt() == number){
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }else {
                int ball = findStrike(sc.nextInt(), number);
                System.out.println(ball+" strike");
            }
        }

    }

    static int findStrike(int input, int number){
        ArrayList<Integer> arrInput = new ArrayList<>();
        ArrayList<Integer> arrNumber = new ArrayList<>();
        int strikeResult = 0;
        while (input>0){
            arrInput.add(input%10);
            input/=10;
        }
        while (number>0){
            arrNumber.add(number%10);
            number/=10;
        }

        if(arrInput.get(2) ==arrNumber.get(2)){
            strikeResult+=1;
        }if (arrInput.get(1)==arrNumber.get(1)) {
            strikeResult+=1;
        }if (arrInput.get(0)==arrNumber.get(0)) {
            strikeResult+=1;
        }
        return strikeResult;
    }
}
