package com.company;

import java.util.*;

/***************************
 * STEM BEST Robotics 2015 *
 * Market Shift Simulation *
 * Created on 9/19/2015    *
 * Completed on -NA-       *
 ***************************/

//Libraries Imported
public class Main {

    //Mode for input
    static String mode = "all";

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
    public static void main(String[] args) {
        //Creates an array for other teams
        Team [] seeders;
        //Gets user input and stores it in our team
        Team Best = BEST();
        //Creates teams
        seeders = teamGenerator(11);
        //Creates random team values
        seeders = seedRound(seeders, 6);
        //Check if we won
        boolean win = winCheck(Best, seeders, 7);
        //Declares variable to determine if we won the WildCard Round
        boolean wildWin;
        //Sends us to wildcard if we lost

        if (win){
            System.out.println("HELL YEAH!! WE MADE IT TO THE SEMIS");
        } else {
            System.out.println("Dang. Let's hope you get wildcard.");
            //Defines new teams
            Team [] wild;
            System.out.println("Enter new values for our team");
            //Defines new team for us
            Team wildBest = BEST();
            //Defines the space of the wild array
            wild = teamGenerator(3);
            wild = seedRound(wild, 1);
            //Assigns the boolean wildWin to the result of the wildCard method
            wildWin = winCheck(wildBest, wild, 1);
        }

    }

    //Function that gets user input and returns a team with those values
    public static Team BEST(){
        Scanner oreInput  = new Scanner(System.in);
        Team best = new Team();
        if (mode == "all") {

            System.out.println("Seeding Rounds:");

            System.out.println("Limestone collected: ");
            int limestone = oreInput.nextInt();

            System.out.println("Coal collected: ");
            int coal = oreInput.nextInt();

            System.out.println("Magnetite collected: ");
            int magnetite = oreInput.nextInt();

            System.out.println("Bauxite collected: ");
            int bauxite = oreInput.nextInt();

            System.out.println("Chalcopyrite collected: ");
            int chalcopyrite = oreInput.nextInt();

            System.out.println("Spodumene collected: ");
            int spodumene = oreInput.nextInt();

            System.out.println("Core sample value (0 for none, 3 for largest): ");
            int coreSampleValue = oreInput.nextInt()*50;

            System.out .println("Did you get the air filter?");
            int airFilter = 0;
            if (oreInput.nextBoolean()) {
                airFilter = 100;
            }
            System.out .println("Did you get the pipe?");
            int pipe = 0;
            if (oreInput.nextBoolean()) {
                pipe = 100;
            }
            coalCollected += coal;
            magnetiteCollected += magnetite;
            bauxiteCollected += bauxite;
            chalcopyriteCollected += chalcopyrite;
            spodumeneCollected += spodumene;

            best.score += limestone * 6 * 2;
            best.score += coal * 6 * coalValue;
            best.score += magnetite * 6 * magnetiteValue;
            best.score += bauxite * 6 * bauxiteValue;
            best.score += chalcopyrite * 6 * chalcopyriteValue;
            best.score += spodumene * 6 * spodumeneValue;
            best.score += airFilter;
            best.score += pipe;
            best.score += coreSampleValue;
        }
        return best;
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





    //Randomizes 6 seeding rounds for each team
    public static Team [] seedRound(Team [] teams, int roundLength) {
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
                teams[teamNum].score += coreValue + pipe + filter + limestone*2 + coal*coalValue + magnetite*magnetiteValue + bauxite*bauxiteValue + chalcopyrite*chalcopyriteValue + spodumene*spodumeneValue;
            }
        }
        return teams;
    }

    //To do
    public static void semifinalsRound(){

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

    /*
    //First Round Function
    public static void seeding () {

        /* limestone: lim
        *coal: coal
        * magnetite: mag
        * bauxite: bau
        * chalcopyrite: cha
        * ccoreSampleValue: csv
        * spodumene: spu
        * filter: fil
        * pipe: pipe
         */

//Randomly generates the amount of a material a random team collects and initializes them

        /*
        for (int i = 0; i < teams.length; i++) {
            int lim = rand.nextInt(6) * 2;
            int coal = rand.nextInt(25);
            int mag = rand.nextInt(21);
            int bau = rand.nextInt(17);
            int cha = rand.nextInt(13);
            int csv = rand.nextInt(4) * 50;
            int spu = rand.nextInt(9);
            boolean fil = rand.nextBoolean();
            boolean pipe = rand.nextBoolean();
            Team temp = new Team(lim, coal * coalValue, mag * magnetiteValue, bau * bauxiteValue, cha * chalcopyriteValue, csv, spu * spodumeneValue, fil, pipe);

            teams[i] = temp;
            }

        //Initializes variables
        int coalCollected = 0;
        int magnetiteCollected = 0;
        int bauxiteCollected = 0;
        int chalcopyriteCollected = 0;
        int spodumeneCollected = 0;

       //Determines the total amount of each material collected
        for (int i = 0; i < teams.length; i++) {
            coalCollected += (teams[i].coalSR/coalValue);
            magnetiteCollected += (teams[i].magnetiteSR/magnetiteValue);
            bauxiteCollected += (teams[i].bauxiteSR/bauxiteValue);
            chalcopyriteCollected += (teams[i].chalcopyriteSR/chalcopyriteValue);
            spodumeneCollected += (teams[i].spodumeneSR/spodumeneValue);
        }

        //Adds our teams values
        coalCollected += (BEST.coalSR/coalValue);
        magnetiteCollected += (BEST.magnetiteSR/magnetiteValue);
        bauxiteCollected += (BEST.bauxiteSR/bauxiteValue);
        chalcopyriteCollected += (BEST.chalcopyriteSR/chalcopyriteValue);
        spodumeneCollected += (BEST.spodumeneSR/spodumeneValue);

        shiftMarket(coalCollected, magnetiteCollected, bauxiteCollected, chalcopyriteCollected, spodumeneCollected);
    }

    public static void semiFinals() {
        for (int i = 0; i < teams.length; i++) {
            int lim = rand.nextInt(6) * 2;
            int coal = rand.nextInt(25);
            int mag = rand.nextInt(21);
            int bau = rand.nextInt(17);
            int cha = rand.nextInt(13);
            int csv = rand.nextInt(4) * 50;
            int spu = rand.nextInt(9);
            boolean fil = rand.nextBoolean();
            boolean pipe = rand.nextBoolean();

            teams[i].limestoneSF = lim*2;
            teams[i].coalSF = coal*coalValue;
            teams[i].magnetiteSF = mag*magnetiteValue;
            teams[i].bauxiteSF = bau*bauxiteValue;
            teams[i].chalcopyriteSF = cha*chalcopyriteValue;
            teams[i].coreSampleSF = csv;
            teams[i].spodumeneSF = spu*spodumeneValue;
            teams[i].airFilterSF = fil;
            teams[i].repairPipeSF = pipe;
            float tempAir = 0;
            float tempPipe = 0;
            if (pipe == true) {
                tempAir = 100;
            }
            if (fil == true) {
                tempPipe = 100;
            }
            teams[i].scoreSF += lim + coal +mag + bau + cha + csv + spu + tempAir + tempPipe;
        }

        //Initializes variables
        int coalCollected = 0;
        int magnetiteCollected = 0;
        int bauxiteCollected = 0;
        int chalcopyriteCollected = 0;
        int spodumeneCollected = 0;

        //Determines the total amount of each material collected
        for (int i = 0; i < teams.length; i++) {
            coalCollected += (teams[i].coalSF/coalValue);
            magnetiteCollected += (teams[i].magnetiteSF/magnetiteValue);
            bauxiteCollected += (teams[i].bauxiteSF/bauxiteValue);
            chalcopyriteCollected += (teams[i].chalcopyriteSF/chalcopyriteValue);
            spodumeneCollected += (teams[i].spodumeneSF/spodumeneValue);
        }

        //Adds our teams values
        coalCollected += (BEST.coalSF/coalValue);
        magnetiteCollected += (BEST.magnetiteSF/magnetiteValue);
        bauxiteCollected += (BEST.bauxiteSF/bauxiteValue);
        chalcopyriteCollected += (BEST.chalcopyriteSF/chalcopyriteValue);
        spodumeneCollected += (BEST.spodumeneSF/spodumeneValue);

        shiftMarket(coalCollected, magnetiteCollected, bauxiteCollected, chalcopyriteCollected, spodumeneCollected);
    }

    public static void shiftMarket(int coa, int mag, int bau, int cha, int spu) {

        float avgPercent;

        float coalPercent = (coa/(teams.length+1) * 24);
        float magnetitePercent = (mag/(teams.length+1) * 20);
        float bauxitePercent = (bau/(teams.length+1) * 16);
        float chalcopyritePercent = (cha/(teams.length) * 12);
        float spodumenePercent = (spu/(teams.length) * 8);

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
}
*/