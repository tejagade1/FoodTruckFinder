package com.teja.foodtruckfinder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Utility class providing different functionality to perform an input Json.
 *
 *
 */
public class JsonUtils {

  /**
   * Logger instantiation for this class
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

  /**
   * Validates whether provided input string is a valid Json or not
   *
   * @param inputJson Input String to validate
   * @return Returns TRUE if input string is a valid Json. FALSE otherwise.
   */
  public boolean isValidJson(final String inputJson) {
    Verifier.verifyNotNullOrEmpty(inputJson, "Input Json cannot be null, empty, or blank");
    try {
      JsonParser.parseString(inputJson);
      return true;

    } catch (JsonSyntaxException jse) {
      if (LOGGER.isErrorEnabled()) {
        final String errorMsg = "Input Json is not valid: \"" + inputJson + "\"";
        LOGGER.error(errorMsg + jse.getMessage());
      }
      return false;
    }

  }


}
