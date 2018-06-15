package com.agileengine.main;

public class Main {

    public static void main(String [] args)
    {
        String originalFilePath;
        String diffFilePath;
        String id;

        if(args.length == 2) {
            originalFilePath = args[0];
            diffFilePath = args[1];
            id = "make-everything-ok-button";
        } else if(args.length ==3) {
            originalFilePath = args[0];
            diffFilePath = args[1];
            id = args[3];
        } else {
            System.out.println("args should be original and diff file paths and optionally the id you are looking for.");
            return;
        }

        MatchingHelper matchingHelper = new MatchingHelper(originalFilePath,diffFilePath,id);

        matchingHelper.printBestMatchingPath();

    }



}
