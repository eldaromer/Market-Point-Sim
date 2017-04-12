package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/***************************
 * STEM BEST Robotics 2015 *
 * Market Shift Simulation *
 * Created on 9/19/2015    *
 * Completed on 10/1/2015  *
 ***************************/


public class Main {

    //Start win and loss counters at 0
    static int won = 0;
    static int lost = 0;

    //Initializes a random
    static Random rand = new Random();

    //Material Values
    static float coalValue = 5;
    static float magnetiteValue = 7;
    static float bauxiteValue = 10;
    static float chalcopyriteValue = 15;
    static float spodumeneValue = 25;


    //Initializes collected ore
    static int coalCollected = 0;
    static int magnetiteCollected = 0;
    static int bauxiteCollected = 0;
    static int chalcopyriteCollected = 0;
    static int spodumeneCollected = 0;

    //Main Method
    public static void main(String[] args) throws IOException{

        CoolestGUI game = new CoolestGUI();

    }


    public static void gameDay(Team Best, Team wildBest, Team BestSemi, Team BestFinal) {
        coalValue = 5;
        magnetiteValue = 7;
        bauxiteValue = 10;
        chalcopyriteValue = 15;
        spodumeneValue = 25;
        //Creates an array for other teams
        Team [] seeders;
        //Creates teams
        seeders = teamGenerator(11);
        //Creates random team values
        seeders = generateRound(seeders, 6);
        //Check if we won
        boolean win = winCheck(Best, seeders, 7);
        //Declares variable to determine if we won the WildCard Round
        //Sends us to wildcard if we lost

        if (win){
        } else {
            //Defines new teams
            Team [] wild;
            //Defines new team for us

            //Defines the space of the wild array
            wild = teamGenerator(3);
            wild = generateRound(wild, 1);
            //Assigns the boolean wildWin to the result of the wildCard method
            win = winCheck(wildBest, wild, 1);
        }

        if (win) {
            shiftMarket(coalCollected, magnetiteCollected, bauxiteCollected, chalcopyriteCollected, spodumeneCollected);
            reset();
            Team [] semis;
            semis = teamGenerator(7);
            semis = generateRound(semis, 3);
            win = winCheck(BestSemi, semis, 4);

            if (win) {
                shiftMarket(coalCollected, magnetiteCollected, bauxiteCollected, chalcopyriteCollected, spodumeneCollected);
                reset();
                Team [] finals;
                finals = teamGenerator(3);
                finals = generateRound(finals, 3);
                win = winCheck(BestFinal, finals, 1);
            }
        }

        if (win) {
            won++;
        } else {
            lost++;
        }
    }

    //Resets collected values after market shift
    public static void reset() {
        coalCollected = 0;
        magnetiteCollected = 0;
        bauxiteCollected = 0;
        chalcopyriteCollected = 0;
        spodumeneCollected = 0;
    }

    //Returns an array full of the number of teams specified
    public static Team [] teamGenerator(int teamAmount) {
        Team [] temp = new Team [teamAmount];
        for (int i = 0; i < teamAmount; i++) {
            temp[i] = new Team();
        }
        return temp;
    }

    //Checks if we won the seeding round
    public static boolean winCheck(Team us, Team [] scrubs, int winningFactor) {
        int worseThan = 0;

        for (int i = 0; i < scrubs.length; i++) {
            if (scrubs[i].score > us.score) {
                worseThan++;
            }
        }
        if (worseThan >= winningFactor) {
            return false;
        } else {
            return true;
        }
    }

    //Function that returns a team given inputs
    public static Team BEST(int rounds, int lime, int coa, int mag, int bau, int cha, int spo, int csv, boolean air, boolean pip){
        Team best = new Team();
        int limestone = lime;
        int coal = coa;
        int magnetite = mag;
        int bauxite = bau;
        int chalcopyrite = cha;
        int spodumene = spo;
        int coreSampleValue = csv*50;
        int airFilter = 0;
        if (air) {
            airFilter = 100;
        }
        int pipe = 0;
        if (pip) {
            pipe = 100;
        }
        coalCollected += coal * rounds;
        magnetiteCollected += magnetite * rounds;
        bauxiteCollected += bauxite * rounds;
        chalcopyriteCollected += chalcopyrite * rounds;
        spodumeneCollected += spodumene * rounds;
        best.score += limestone * rounds * 2;
        best.score += coal * rounds * coalValue;
        best.score += magnetite * rounds * magnetiteValue;
        best.score += bauxite * rounds * bauxiteValue;
        best.score += chalcopyrite * rounds * chalcopyriteValue;
        best.score += spodumene * rounds * spodumeneValue;
        best.score += airFilter * rounds;
        best.score += pipe * rounds;
        best.score += coreSampleValue * rounds;
        return best;
    }

    //Randomizes rounds for each team
    public static Team [] generateRound(Team [] teams, int roundLength) {
        for (int teamNum = 0; teamNum < teams.length; teamNum++) {
            for (int round = 0; round < roundLength; round++) {
                int limestone = rand.nextInt(6);
                int coal = rand.nextInt(25);
                coalCollected += coal;
                int magnetite = rand.nextInt(21);
                magnetiteCollected += magnetite;
                int bauxite = rand.nextInt(17);
                bauxiteCollected += bauxite;
                int chalcopyrite = rand.nextInt(13);
                chalcopyriteCollected += chalcopyrite;
                int spodumene = rand.nextInt(3);
                spodumeneCollected += spodumene;
                int coreValue = rand.nextInt(4)*50;
                int filter = rand.nextInt(2)*100;
                int pipe = rand.nextInt(2)*100;
                teams[teamNum].score += coreValue;
                teams[teamNum].score += pipe;
                teams[teamNum].score += filter;
                teams[teamNum].score += limestone*2;
                teams[teamNum].score += coal*coalValue;
                teams[teamNum].score += magnetite*magnetiteValue;
                teams[teamNum].score += bauxite*bauxiteValue;
                teams[teamNum].score += chalcopyrite*chalcopyriteValue;
                teams[teamNum].score += spodumene*spodumeneValue;
            }
        }
        return teams;
    }

    //Calculates market shift and changes values accordingly
    public static void shiftMarket(int coa, int mag, int bau, int cha, int spu) {

        float avgPercent;

        float coalPercent = (coa/(12) * 24 * 6);
        float magnetitePercent = (mag/(12) * 20 * 6);
        float bauxitePercent = (bau/(12) * 16 * 6);
        float chalcopyritePercent = (cha/(12) * 12 * 6);
        float spodumenePercent = (spu/(12) * 8 * 6);

        avgPercent = (coalPercent +
                magnetitePercent +
                bauxitePercent +
                chalcopyritePercent +
                spodumenePercent)/5;

        float high = maxi(coalPercent, magnetitePercent, bauxitePercent, chalcopyritePercent, spodumenePercent);
        float low = mini(coalPercent, magnetitePercent, bauxitePercent, chalcopyritePercent, spodumenePercent);

        float coalShift;
        float magnetiteShift;
        float bauxiteShift;
        float chalcopyriteShift;
        float spodumeneShift;

        coalShift = (coalPercent - low)/(high-low);
        magnetiteShift = (magnetitePercent - low)/(high-low);
        bauxiteShift = (bauxitePercent - low)/(high-low);
        chalcopyriteShift = (chalcopyritePercent - low)/(high-low);
        spodumeneShift = (spodumenePercent - low)/(high-low);

        float expandedAvg = (avgPercent-low)/(high-low);

        coalShift = expandedAvg - coalShift;
        magnetiteShift = expandedAvg - magnetiteShift;
        bauxiteShift = expandedAvg - bauxiteShift;
        chalcopyriteShift = expandedAvg - chalcopyriteShift;
        spodumeneShift = expandedAvg - spodumeneShift;

        coalValue = coalValue + (coalShift*coalValue);
        magnetiteValue = magnetiteValue + (magnetiteShift*magnetiteValue);
        bauxiteValue = bauxiteValue + (bauxiteShift*bauxiteValue);
        chalcopyriteValue = chalcopyriteValue + (chalcopyriteShift*chalcopyriteValue);
        spodumeneValue = spodumeneValue + (spodumeneShift*spodumeneValue);
    }

    //A method that finds the maximum of a set of 5 numbers
    public static float maxi (float a, float b, float c, float d, float e) {
        float temp = 0;
        if (a > temp) {
            temp = a;
        }
        if (b > temp) {
            temp = b;
        }
        if (c > temp) {
            temp = c;
        }
        if (d > temp) {
            temp = d;
        }
        if (e > temp) {
            temp = e;
        }

        return temp;
    }

    //A method that finds the minimum of a set of 5 numbers
    public static float mini (float a, float b, float c, float d, float e) {
        float temp = 100;
        if (a < temp) {
            temp = a;
        }
        if (b < temp) {
            temp = b;
        }
        if (c < temp) {
            temp = c;
        }
        if (d < temp) {
            temp = d;
        }
        if (e < temp) {
            temp = e;
        }

        return temp;
    }
}