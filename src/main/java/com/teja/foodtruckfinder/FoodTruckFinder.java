package com.teja.foodtruckfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.teja.foodtruckfinder.service.FoodTruckService;

/**
 * Main program to find the list of {@link FoodTruck}s. This program gets the source of food truck
 * data from the San Francisco government’s API. The San Francisco government’s website has a public
 * data source of food trucks
 * (https://data.sfgov.org/Economy-and-Community/Mobile-Food-Schedule/jjew-r69b). For the purpose of
 * this program, the data is accessed in JSON format.
 *
 *
 */
public class FoodTruckFinder {

  /**
   * Logger instantiation for this class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(FoodTruckFinder.class);

  /**
   * Main method which is the entry point for this service.
   *
   * @param args Input arguments if any.
   */
  public static void main(String[] args) {
    try {
      BasicConfigurator.configure();
      StringBuilder result = new StringBuilder();
      URL url = new URL("http://data.sfgov.org/resource/bbb8-hzi6.json");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      rd.close();

      FoodTruckService foodTruckService = new FoodTruckService();
      foodTruckService.mapInputJson(result.toString());
      foodTruckService.printFoodtrucksCurrentlyOpen();

    } catch (IOException ioException) {
      if (LOGGER.isErrorEnabled()) {
        String errorMsg = "Unable to read the Food Truck Json from Socrata API : ";
        LOGGER.error(errorMsg + ioException.getMessage());
      }
    } catch (IllegalArgumentException illegalArgumentException) {
      if (LOGGER.isErrorEnabled()) {
        String errorMsg = "Unable to retrieve Open Food Trucks : ";
        LOGGER.error(errorMsg + illegalArgumentException.getMessage());
      }
    }
  }
}
