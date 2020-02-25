package com.teja.foodtruckfinder.dto;

import java.time.LocalDateTime;

/**
 *
 * Used to store the location of each food truck.
 *
 */
public class Location implements Comparable<Location> {

  /**
   * Unique Location Id for this Location.
   */
  private Long locationId;

  /**
   * Street address for the Location
   */
  private String streetAddress;

  /**
   * City of the Location
   */
  private String city;

  /**
   * State of the Location
   */
  private String state;

  /**
   * Zip code for the Location
   */
  private String zipCode;

  /**
   * Country of the Location
   */
  private String country;

  /**
   * Detailed address description
   */
  private String addressDescription;

  /**
   * Block details for this Location
   */
  private String block;

  /**
   * Lot information for this Location
   */
  private String lot;

  /**
   * Date when this Location is created
   */
  private LocalDateTime createdDate;

  /**
   * Date when this Location is modified
   */
  private LocalDateTime modifiedDate;

  /**
   * Latitude information for this Location
   */
  private double latitude;

  /**
   * Longitude information for this Location
   */
  private double longitude;

  /**
   * Getter method to return the Latitude of the Location
   *
   * @return Returns the Latitude of the Location
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Setter method to set the Latitude of the Location
   *
   * @param latitude Latitude of the Location
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * Getter method to return the Longitude of the Location
   *
   * @return Returns the Longitude of the Location
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Setter method to set the Longitude of the Location
   *
   * @param longitude Longitude of the Location
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * Setter method to set the street address of the Location
   *
   * @param streetAddress Street Address of the Location
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  /**
   * Getter method to return the street address of the Location
   *
   * @return Returns the street address of the location
   */
  public String getStreetAddress() {
    return streetAddress;
  }

  /**
   * Getter method to get the City of the Location
   *
   * @return Returns the city of the Location
   */
  public String getCity() {
    return city;
  }

  /**
   * Setter method to set the city of the Location
   *
   * @param city City of the Location
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Getter method to get the state of the Location
   *
   * @return Returns the state of the Location
   */
  public String getState() {
    return state;
  }

  /**
   * Setter method to set the state of the Location
   *
   * @param state State of the Location
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Getter method to get the zip code for the Location
   *
   * @return Returns the zip code of the Location
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Setter method to set the zip code for the Location
   *
   * @param zipCode Zip code of the Location
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Getter method to get the country of the Location
   *
   * @return Returns the country of the Location
   */
  public String getCountry() {
    return country;
  }

  /**
   * Setter method to set the country of the Location
   *
   * @param country Country of the Location
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Getter method to get detailed address description
   *
   * @return Returns detailed address description
   */
  public String getAddressDescription() {
    return addressDescription;
  }

  /**
   * Setter method to set detailed address description
   *
   * @param addressDescription Detailed address description
   */
  public void setAddressDescription(String addressDescription) {
    this.addressDescription = addressDescription;
  }

  /**
   * Getter method to get the block information
   *
   * @return Returns the block information
   */
  public String getBlock() {
    return block;
  }

  /**
   * Setter method to set the block information
   *
   * @param block Block information for the Location
   */
  public void setBlock(String block) {
    this.block = block;
  }

  /**
   * Getter method to return the Lot information
   *
   * @return Returns the Lot information
   */
  public String getLot() {
    return lot;
  }

  /**
   * Setter method to set the Lot information
   *
   * @param lot Lot information of the Location
   */
  public void setLot(String lot) {
    this.lot = lot;
  }

  /**
   * Getter method to retrieve the unique Location Id
   *
   * @return Returns the unique Id associated with the Location.
   */
  public Long getLocationId() {
    return locationId;
  }

  /**
   * Setter method to update the unique Location Id
   *
   * @param locationId Unique Id associated with the Location
   */
  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

  /**
   * Getter method to return the date this Location is created
   *
   * @return Returns the date when this Location is created
   */
  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  /**
   * Setter method to set the creation date time for this Location
   *
   * @param createdDate Date when this Location is created
   */
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * Getter method to return when this Location is modified
   *
   * @return Returns when the current Location is modified
   */
  public LocalDateTime getModifiedDate() {
    return modifiedDate;
  }

  /**
   * Setter method to set when this Location is modified
   *
   * @param modifiedDate Date when this Location is modified
   */
  public void setModifiedDate(LocalDateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  @Override
  public int compareTo(Location location) {
    return (int) (locationId - location.getLocationId());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "Location [locationId=" + locationId + ", streetAddress=" + streetAddress + ", block="
        + block + ", lot=" + lot + "]";
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
    Location other = (Location) obj;
    if (locationId == null) {
      if (other.locationId != null) {
        return false;
      }
    } else if (!locationId.equals(other.locationId)) {
      return false;
    }
    return true;
  }

}
