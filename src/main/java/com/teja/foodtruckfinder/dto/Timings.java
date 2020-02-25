package com.teja.foodtruckfinder.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 *
 * Class to store the timing details such as Day of week, Open from and Open until.
 *
 */
public class Timings {

  /**
   * Representing {@link DayOfWeek}
   */
  private DayOfWeek dayOfWeek;

  /**
   * Representing {@link LocalTime} open from
   */
  private LocalTime openFrom;

  /**
   * Representing {@link LocalTime} open until
   */
  private LocalTime openUntil;

  /**
   * Getter method to return the day of the week
   *
   * @return Returns the day of the week
   */
  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Setter method to set the day of the week
   *
   * @param dayOfWeek Day of the week
   */
  public void setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Getter method to return open from time
   *
   * @return Returns the open from time
   */
  public LocalTime getOpenFrom() {
    return openFrom;
  }

  /**
   * Setter method to set open from time
   *
   * @param openFrom Open from time
   */
  public void setOpenFrom(LocalTime openFrom) {
    this.openFrom = openFrom;
  }

  /**
   * Getter method to return open until time
   *
   * @return Returns Open Until time
   */
  public LocalTime getOpenUntil() {
    return openUntil;
  }

  /**
   * Setter method to set the open until time
   *
   * @param openUntil Open until time
   */
  public void setOpenUntil(LocalTime openUntil) {
    this.openUntil = openUntil;
  }

  @Override
  public String toString() {
    return "Timings [dayOfWeek=" + dayOfWeek + ", openFrom=" + openFrom + ", openUntil=" + openUntil
        + "]";
  }

}
