package de.rki.demis.fhir.util.constant;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class Constants {
  /** Validation **/
  public static final String UUID_REGEX_STRING = "/^[0-9A-F]{8}-[0-9A-F]{4}-4[0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$/i";
  public static final Pattern UUID_REGEX       = Pattern.compile(UUID_REGEX_STRING);


  /** messages */
  public static final String UNSUPPORTED_PROFILE_TYPE    = "Unsupported profile type";
  public static final String UNSUPPORTED_RESOURCE_BUNDLE = "Unsupported resource bundle";
  public static final String ALL_OK                      = "All OK";
  public static final String NOT_EMPTY_MSG               = "must not be empty";
  public static final String NOT_NULL_MSG                = "must not be null";

  public static final String NOT_EXIST_MSG               = "::: A %s with 'id = %s' does not exist :::";
  public static final String ALREADY_EXIST_MSG           = "::: A %s with 'id = %s' already exists :::";


  /** APIs */
  public static final String SEARCH         = "search";
  public static final String BINARY_ID      = "binaryId";
  public static final String BUNDLE_ID      = "bundleId";
  public static final String PATIENT_ID     = "patientId";


  /*** API Search parameters ***/

  public static final String CONTENT        = "_content";
  public static final String HAS            = "_has";
  public static final String ID             = "_id";
  public static final String LAST_UPDATED   = "_lastUpdated";
  public static final String PROFILE        = "_profile";
  public static final String SECURITY       = "_security";
  public static final String SOURCE         = "_source";
  public static final String TAG            = "_tag";
  public static final String TEXT           = "_text";
  public static final String TYPE           = "_type";


  /*** API Search Result parameters ***/

  public static final String CONTAINED      = "_contained";
  public static final String CONTAINED_TYPE = "_containedType";
  public static final String COUNT          = "_count";
  public static final String ELEMENTS       = "_elements";
  public static final String INCLUDE        = "_include";
  public static final String REV_INCLUDE    = "_revinclude";
  public static final String SORT           = "_sort";
  public static final String SUMMARY        = "_summary";
  public static final String TOTAL          = "_total";

}
