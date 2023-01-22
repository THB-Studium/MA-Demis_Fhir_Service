package de.rki.demis.fhir.util.constant;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class Constants {
  /** Validation **/
  public static final String UUID_REGEX_STRING = "/^[0-9A-F]{8}-[0-9A-F]{4}-4[0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$/i";
  public static final Pattern UUID_REGEX = Pattern.compile(UUID_REGEX_STRING);


  /** Astra Cassandra Credential **/
  public static final String DB_KEY_SPACE    = "fhir";
  public static final String BUNDLE_PATH     = "/cassandra-db/secure-connect-demis-fhir-db.zip";
  public static final String CLIENT_ID_1     = "ZRkFpitfwxFaAITAEYTgOJLW";
  public static final String CLIENT_SECRET_1 = "b9MHWTK1-kWCT_l6OZFHzDxGlCAq2nrk-LbzhwfB0yZvZ0Bq+YA8jR5Ls8NyX0Q4w0mmOZ.NqAuJwYOERX3iwwYMYdpUYH8Orprt8_3E_gCEvP0pqlLccF0GvM9m-7sc";
  public static final String CLIENT_TOKEN_1  = "AstraCS:ZRkFpitfwxFaAITAEYTgOJLW:83a1c9023214066b93da951c2995c2c6b3f136d9f3d0aab8e8e8b2af0d98af17";

  public static final String CLIENT_ID_2     = "oCZZdgRYlhCfDEkhjvyJIvJw";
  public static final String CLIENT_SECRET_2 = "LI8MA1clL5.+ErIcCTe8J1JZzFRJoO2khRqcZbly17O6LwvdE5m4jHYh8,s6qTOQr8_iUn0.,fJ60SZbKao+9W5tJlWvizbvHFp29llc274OeiF-t0F3YMxCd_cFnOgs";
  public static final String CLIENT_TOKEN_2  = "AstraCS:oCZZdgRYlhCfDEkhjvyJIvJw:cccd0734a60e66b0d9d1526cb41591a8c1e0d058feb1c38c8b12cb9b8011c5e7";


  /** messages */
  public static final String UNSUPPORTED_PROFILE_TYPE = "Unsupported profile type";
  public static final String UNSUPPORTED_RESOURCE_BUNDLE = "Unsupported resource bundle";
  public static final String ALL_OK = "All OK";
  public static final String NOT_EMPTY_MSG = "must not be empty";
  public static final String NOT_NULL_MSG = "must not be null";


  /** APIs */
  public static final String SEARCH     = "search";
  public static final String BINARY_ID  = "binaryId";
  public static final String BUNDLE_ID  = "bundleId";
  public static final String PATIENT_ID = "patientId";


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


  /*** For CASSANDRA ***/

  public static final String KEY_SPACE_NAME   = "spring_cassandra";
  public static final String DELETE_TABLE   = "DROP TABLE IF EXISTS " + KEY_SPACE_NAME;
  public static final String DELETE_UDT     = "DROP TYPE IF EXISTS " + KEY_SPACE_NAME;

}
