package com.poly.megagame.utils;

public class GameUtils {

    public static int getPuzzleSwipeImage(int spacePosition,int currentPosition){

        // Swipe = 0 : ko scroll
        // Swipe = 1 : trái
        // Swipe = 2 : phải
        // Swipe = 3 : trên
        // Swipe = 4 : dưới

        switch (spacePosition){
            case 0:
                switch (currentPosition){
                    case 1:
                        return 1;
                    case 3:
                        return 3;
                }
                break;
            case 1:
                switch (currentPosition){
                    case 0:
                        return 2;
                    case 2:
                        return 1;
                    case 4:
                        return 3;
                }
                break;
            case 2:
                switch (currentPosition){
                    case 1:
                        return 2;
                    case 5:
                        return 3;
                }
                break;
            case 3:
                switch (currentPosition){
                    case 0:
                        return 4;
                    case 4:
                        return 1;
                    case 6:
                        return 3;
                }
                break;
            case 4:
                switch (currentPosition){
                    case 1:
                        return 4;
                    case 3:
                        return 2;
                    case 5:
                        return 1;
                    case 7:
                        return 3;
                }
                break;
            case 5:
                switch (currentPosition){
                    case 2:
                        return 4;
                    case 4:
                        return 2;
                    case 8:
                        return 3;
                }
                break;
            case 6:
                switch (currentPosition){
                    case 3:
                        return 4;
                    case 7:
                        return 1;
                }
                break;
            case 7:
                switch (currentPosition){
                    case 4:
                        return 4;
                    case 6:
                        return 2;
                    case 8:
                        return 1;
                }
                break;
            case 8:
                switch (currentPosition){
                    case 5:
                        return 4;
                    case 7:
                        return 2;
                }
                break;
        }
        return 0;
    }
}
