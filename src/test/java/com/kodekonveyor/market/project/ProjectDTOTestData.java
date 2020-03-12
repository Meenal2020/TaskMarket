package com.kodekonveyor.market.project;

public class ProjectDTOTestData {

  public final static Long ID = (long) 442;
  public final static String INVALID_PROJECT_NAME =
      "kode-konveyor/Tas%$#@kMarket";
  public final static String NAME = "kode-konveyor/TaskMarket";
  private static final Long NON_POSITIVE_ID = (long) -442;
  private static final Long ONE_ID = (long) 1;
  private static final Long ZERO_ID = (long) 0;

  public static final ProjectDTO get() {
    final ProjectDTO dto = new ProjectDTO();
    dto.setId(ID);
    dto.setName(NAME);
    return dto;
  }

  public static ProjectDTO getInvalidName() {
    final ProjectDTO dto = get();
    dto.setName(INVALID_PROJECT_NAME);
    return dto;
  }

  public static final ProjectDTO getNonPositiveId() {
    final ProjectDTO dto = get();
    dto.setId(NON_POSITIVE_ID);
    return dto;
  }

  public static final ProjectDTO getNullName() {
    final ProjectDTO dto = get();
    dto.setName(null);
    return dto;
  }

  public static ProjectDTO getPositiveId() {
    final ProjectDTO dto = get();
    dto.setId(ONE_ID);
    return dto;
  }

  public static ProjectDTO getZeroId() {
    final ProjectDTO dto = get();
    dto.setId(ZERO_ID);
    return dto;
  }

}
