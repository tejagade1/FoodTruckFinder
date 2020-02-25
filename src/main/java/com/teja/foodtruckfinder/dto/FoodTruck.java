package com.teja.foodtruckfinder.dto;

import java.util.Map;
import com.teja.foodtruckfinder.util.Verifier;

/**
 *
 * Food truck DTO which stores the details related to a food truck such as it's name, permit details
 * and the location/timings of it.
 *
 */
public class FoodTruck implements Comparable<FoodTruck> {

  /**
   * Name of the Food truck
   */
  private String foodTruckName;

  /**
   * Map storing the Food truck's location and hour details
   */
  private Map<Location, Timings> locationAndHours;

  /**
   * Food truck permit information
   */
  private String permit;

  /**
   * Optional text information for this Food truck
   */
  private String optionalText;

  /**
   * Boolean specifying if this is a cold food truck.
   */
  private boolean isColdTruck;

  /**
   * Constructor for FoodTruck
   *
   * @param foodTruckName Name of the food truck
   */
  public FoodTruck(String foodTruckName) {
    Verifier.verifyNotNullOrEmpty(foodTruckName, "Food truck name cannot be null, empty, or blank");
    this.foodTruckName = foodTruckName;
  }

  /**
   * Getter method to return the name of the food truck
   *
   * @return Returns the name of the food truck
   */
  public String getFoodTruckName() {
    return foodTruckName;
  }

  /**
   * Setter method to set the name of the food truck
   *
   * @param foodTruckName Name of the food truck
   */
  public void setFoodTruckName(String foodTruckName) {
    Verifier.verifyNotNullOrEmpty(foodTruckName, "Food truck name cannot be null, empty, or blank");
    this.foodTruckName = foodTruckName;
  }

  /**
   * Getter method to return the food truck location and timings
   *
   * @return Returns food truck location and timings
   */
  public Map<Location, Timings> getLocationAndHours() {
    return locationAndHours;
  }

  /**
   * Setter method to set the food truck location and timings
   *
   * @param locationAndHours Food truck location and timings
   */
  public void setLocationAndHours(Map<Location, Timings> locationAndHours) {
    this.locationAndHours = locationAndHours;
  }

  /**
   * Getter method to return food truck permit
   *
   * @return Returns food truck permit
   */
  public String getPermit() {
    return permit;
  }

  /**
   * Setter method to set food truck permit
   *
   * @param permit Food truck permit
   */
  public void setPermit(String permit) {
    this.permit = permit;
  }

  /**
   * Getter method to return optional text related to food truck
   *
   * @return Returns optional text related to food truck
   */
  public String getOptionalText() {
    return optionalText;
  }

  /**
   * Setter method to set option text for food truck
   *
   * @param optionalText Optional text for food truck
   */
  public void setOptionalText(String optionalText) {
    this.optionalText = optionalText;
  }

  /**
   * Getter method to specify if a food truck is a cold truck or not
   *
   * @return Returns TRUE if cold truck. FALSE otherwise
   */
  public boolean isColdTruck() {
    return isColdTruck;
  }

  /**
   * Setter method to specify if a food truck is a cold truck or not
   *
   * @param isColdTruck Boolean to specify if a food truck is a cold truck or not
   */
  public void setColdTruck(boolean isColdTruck) {
    this.isColdTruck = isColdTruck;
  }

  @Override
  public int compareTo(FoodTruck foodTruck) {
    return foodTruckName.toUpperCase().equals(foodTruck.getFoodTruckName().toUpperCase()) ? 1 : -1;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((foodTruckName == null) ? 0 : foodTruckName.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "FoodTruck [foodTruckName=" + foodTruckName + ", permit=" + permit + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    FoodTruck other = (FoodTruck) obj;
    if (foodTruckName == null) {
      if (other.foodTruckName != null) {
        return false;
      }
    } else if (!foodTruckName.toUpperCase().equals(other.foodTruckName.toUpperCase())) {
      return false;
    }
    return true;
  }

}
