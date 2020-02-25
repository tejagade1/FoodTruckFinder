package com.teja.foodtruckfinder.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teja.foodtruckfinder.dto.FoodTruck;
import com.teja.foodtruckfinder.dto.Location;
import com.teja.foodtruckfinder.dto.Timings;
import com.teja.foodtruckfinder.util.JsonUtils;
import com.teja.foodtruckfinder.util.Verifier;
import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.util.stream.Collectors.toMap;
import static java.util.Map.Entry.comparingByKey;

/**
 *
 * This class is used to implement the methods related to a food truck such as to return the list of
 * all food trucks or to identify the list of food trucks that are open at a certain point of a day.
 *
 */
public class FoodTruckService {

  /**
   * Map to store the list of Food trucks that are parsed from the Socrata API
   */
  private Map<String, FoodTruck> foodTrucks;

  /**
   * Maximum results to be displayed per page.
   */
  private static final int MAX_RESULTS_PER_PAGE = 10;

  /**
   * Logger instantiation for this class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(FoodTruckService.class);

  /**
   * Parses the input Json that is provided and maps all the necessary details of a food truck out
   * such as Food truck Location, Food truck timings and any other food truck information.
   *
   * @param inputJson Details containing the food truck details in Json format.
   * @throws IllegalArgumentException Throws an exception if the input string is not a valid Json.
   */
  public void mapInputJson(final String inputJson) throws IllegalArgumentException {

    Verifier.verifyNotNullOrEmpty(inputJson, "Input Json cannot be null, empty, or blank");

    JsonUtils jsonUtils = new JsonUtils();
    if (!jsonUtils.isValidJson(inputJson)) {
      final String errorMsg = "Input Json is not valid: \"" + inputJson + "\"";
      LOGGER.error(errorMsg);
      throw new IllegalArgumentException(errorMsg);
    }

    JsonArray jsonArray = JsonParser.parseString(inputJson).getAsJsonArray();

    foodTrucks = new HashMap<>();

    for (int i = 0; i < jsonArray.size(); i++) {
      JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
      FoodTruck foodTruck;
      Map<Location, Timings> locationAndHours;
      String foodTruckName = jsonObject.get("applicant").getAsString();

      // Verify if the food truck is already existing in the foodTrucks map. If it is not existing
      // in the map, then populate all the details related to a food truck. Else, only update the
      // location and timings of the food truck.
      if (!foodTrucks.containsKey(foodTruckName)) {
        foodTruck = new FoodTruck(foodTruckName);
        foodTrucks.put(foodTruckName, foodTruck);
        foodTruck.setOptionalText(jsonObject.get("dayofweekstr").getAsString());
        foodTruck.setPermit(jsonObject.get("permit").getAsString());
        if (jsonObject.get("coldtruck").getAsString().equalsIgnoreCase("Y")) {
          foodTruck.setColdTruck(true);
        } else {
          foodTruck.setColdTruck(false);
        }
        locationAndHours = new HashMap<>();
      } else {
        foodTruck = foodTrucks.get(foodTruckName);
        locationAndHours = foodTruck.getLocationAndHours();
      }

      Location foodTruckLocation = populateFoodTruckLocationDetails(foodTruck, jsonObject);
      Timings foodTruckTimings = populateFoodTruckTimings(foodTruck, jsonObject);
      locationAndHours.put(foodTruckLocation, foodTruckTimings);
      foodTruck.setLocationAndHours(locationAndHours);
    }
  }

  /**
   * Parses the Json Object and populates the food truck timings.
   *
   * @param foodTruck {@link FoodTruck} timings to populate for.
   * @param jsonObj {@link JsonObject} which stores the food truck timing details.
   * @return {@link Timings} of {@link FoodTruck}
   */
  private Timings populateFoodTruckTimings(final FoodTruck foodTruck, final JsonObject jsonObj) {

    Verifier.verifyNotNull(foodTruck, "Food Truck cannot be null");
    Verifier.verifyNotNull(jsonObj, "Json object cannot be null");

    Timings foodTruckTimings = new Timings();

    foodTruckTimings
        .setDayOfWeek(DayOfWeek.valueOf(jsonObj.get("dayofweekstr").getAsString().toUpperCase()));
    foodTruckTimings.setOpenFrom(LocalTime.parse(jsonObj.get("start24").getAsString()));
    if (jsonObj.get("end24").getAsString().equals("24:00")) {
      foodTruckTimings.setOpenUntil(LocalTime.parse("00:00"));
    } else {
      foodTruckTimings.setOpenUntil(LocalTime.parse(jsonObj.get("end24").getAsString()));
    }

    return foodTruckTimings;
  }

  /**
   * Parses the Json Object and populates the food truck Location details.
   *
   * @param foodTruck {@link FoodTruck} timings to populate for.
   * @param jsonObj {@link JsonObject} which stores the food truck timing details.
   * @return {@link Location} of {@link FoodTruck}
   */
  private Location populateFoodTruckLocationDetails(final FoodTruck foodTruck,
      final JsonObject jsonObj) {

    Verifier.verifyNotNull(foodTruck, "Food Truck cannot be null");
    Verifier.verifyNotNull(jsonObj, "Json object cannot be null");

    Location foodTruckAddress = new Location();

    foodTruckAddress.setLocationId(jsonObj.get("locationid").getAsLong());
    foodTruckAddress.setStreetAddress(jsonObj.get("location").getAsString());
    try {
      foodTruckAddress.setAddressDescription(jsonObj.get("locationdesc").getAsString());
    } catch (NullPointerException e) {
      if (LOGGER.isInfoEnabled()) {
        final String errorMsg = "Location description is not available.";
        LOGGER.info(errorMsg);
      }
    }
    foodTruckAddress.setBlock(jsonObj.get("block").getAsString());
    foodTruckAddress.setLot(jsonObj.get("lot").getAsString());
    foodTruckAddress.setLatitude(jsonObj.get("latitude").getAsDouble());
    foodTruckAddress.setLongitude(jsonObj.get("longitude").getAsDouble());
    foodTruckAddress
        .setCreatedDate(LocalDateTime.parse(jsonObj.get("addr_date_create").getAsString()));
    try {
      foodTruckAddress
          .setModifiedDate(LocalDateTime.parse(jsonObj.get("addr_date_modified").getAsString()));
    } catch (NullPointerException e) {
      if (LOGGER.isInfoEnabled()) {
        final String errorMsg = "Address date modified is not available";
        LOGGER.info(errorMsg);
      }
    }

    return foodTruckAddress;
  }

  /**
   * Getter method to return all the food trucks
   *
   * @return Returns a Map of all the Food trucks
   */
  public Map<String, FoodTruck> getAllFoodTrucks() {
    return foodTrucks;
  }

  /**
   * Prints the list of {@link FoodTruck}'s that are currently open. If there are more than 10 food
   * trucks open, the first 10 food trucks will be displayed and then it will wait for input from
   * the user before displaying the next 10 (or fewer if there are fewer than 10 remaining), and so
   * on until there are no more food trucks to display.
   */
  public void printFoodtrucksCurrentlyOpen() {

    LocalTime now = LocalTime.now();
    LocalDate date = LocalDate.now();
    DayOfWeek currentDayOfWeek = date.getDayOfWeek();
    Map<String, String> openFoodTrucks = new ConcurrentHashMap<>();

    if (foodTrucks == null) {
      if (LOGGER.isWarnEnabled()) {
        LOGGER.warn("No Food Trucks Open at this point. Please try again Later.");
      }
    } else {

      for (Entry<String, FoodTruck> foodTruckEntry : foodTrucks.entrySet()) {
        Map<Location, Timings> locationAndTimings =
            foodTrucks.get(foodTruckEntry.getKey()).getLocationAndHours();
        for (Entry<Location, Timings> locationAndTimeEntry : locationAndTimings.entrySet()) {

          Timings time = locationAndTimings.get(locationAndTimeEntry.getKey());

          if (time.getDayOfWeek().equals(currentDayOfWeek)
              && isOpen(time.getOpenFrom(), time.getOpenUntil(), now)) {
            openFoodTrucks.putIfAbsent(foodTrucks.get(foodTruckEntry.getKey()).getFoodTruckName(),
                locationAndTimeEntry.getKey().getStreetAddress());
          }
        }
      }

      Map<String, String> sortedOpenFoodTrucks =
          openFoodTrucks.entrySet().stream().sorted(comparingByKey()).collect(
              toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));


      System.out.println(
          "------------------------------------------------------------------------------------------------------------");
      System.out.printf("%50s %50s", "Food Truck Name", "Address");
      System.out.println();
      System.out.println(
          "------------------------------------------------------------------------------------------------------------");

      int resultCount = 0;
      for (Entry<String, String> openFoodTruckEntry : sortedOpenFoodTrucks.entrySet()) {

        if (resultCount >= MAX_RESULTS_PER_PAGE) {
          System.out.println("More results exists. Do you want to continue[y/n]:");
          Scanner scanIn = new Scanner(System.in);
          String inputString = scanIn.nextLine();
          scanIn.close();
          if (inputString.equalsIgnoreCase("Y")) {
            resultCount = 0;
          } else {
            break;
          }
        }
        System.out.format("%50s %50s", openFoodTruckEntry.getKey(), openFoodTruckEntry.getValue());
        System.out.println();
        resultCount++;

      }
      System.out.println(
          "------------------------------------------------------------------------------------------------------------");
    }


  }

  /**
   * Utility method to determine if a the current time falls in between the Food truck's openfrom
   * and openUntil time
   *
   * @param openFrom Represents Food truck's open from time
   * @param openUntil Represents Food truck's open until time
   * @param now Represents the current {@link LocalTime}
   * @return True if the Food truck is open. False otherwise
   */
  private boolean isOpen(final LocalTime openFrom, final LocalTime openUntil, final LocalTime now) {

    Verifier.verifyNotNull(openFrom, "Food Truck Open From");
    Verifier.verifyNotNull(openUntil, "Food Truck Open Until");
    Verifier.verifyNotNull(now, "Local time");

    if (openFrom.isAfter(openUntil)) {
      return !now.isBefore(openFrom) || !now.isAfter(openUntil);
    } else {
      return !now.isBefore(openFrom) && !now.isAfter(openUntil);
    }
  }


}
