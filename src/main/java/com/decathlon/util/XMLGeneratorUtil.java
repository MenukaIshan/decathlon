package com.decathlon.util;

import com.decathlon.domain.DecathlonPlayer;
import com.decathlon.exceptions.FileWriteException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class XMLGeneratorUtil {

    /*
    * This method will crate output file if it does not exist and flush content if it has content.
    *
    *  */
    public static void xmlFileWriter(String outputXmlPath, List<DecathlonPlayer> decathlonPlayers) throws FileWriteException {
        PrintWriter output = null;
        try {
            FileOutputStream file = new FileOutputStream(outputXmlPath);
            output = new PrintWriter(file, true);
            output.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            output.println("<decathlon>");
            output.println("    <players>");
            for (DecathlonPlayer player : decathlonPlayers) {
                output.println("        <player>");
                output.println("            <name>" + player.getName() + "</name>");
                output.println("            <rank>" + player.getPlayerRank() + "</rank>");
                output.println("            <total_points>" + player.getTotalPoints() + "</total_points>");
                output.println("            <hundred_metres>" + player.getHundredMetres() + "</hundred_metres>");
                output.println("            <long_jump>" + player.getLongJump() + "</long_jump>");
                output.println("            <shot_put>" + player.getShotPut() + "</shot_put>");
                output.println("            <high_jump>" + player.getHighJump() + "</high_jump>");
                output.println("            <four_hundred_metres>"
                        + player.getFourHundredMetres() + "</four_hundred_metres>");
                output.println("            <hundred_and_ten_metres_hurdles>"
                        + player.getHundredAndTenHurdles() + "</hundred_and_ten_metres_hurdles>");
                output.println("            <discus_throw>" + player.getDiscusThrow() + "</discus_throw>");
                output.println("            <pole_vault>" + player.getPoleVault() + "</pole_vault>");
                output.println("            <javelin_throw>" + player.getJavelinThrow() + "</javelin_throw>");
                output.println("            <fifteen_hundred_metres>"
                        + player.getFifteenHundredsMetres() + "</fifteen_hundred_metres>");
                output.println("        </player>");
            }
            output.println("    </players>");
            output.println("</decathlon>");

        } catch (IOException e) {
            System.out.println("File writing error");
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }


    }
}
