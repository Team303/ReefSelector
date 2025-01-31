package com.team303.lib.shuffleboard.data.type;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;
import edu.wpi.first.shuffleboard.api.util.Maps;

import com.team303.lib.shuffleboard.data.ReefState;

import java.util.Map;
import java.util.function.Function;

/**
 * Represents data of the {@link ReefStateType} type.
 */
public final class ReefStateType extends ComplexDataType<ReefState> {

  /**
   * The name of data of this type as it would appear in a WPILib sendable's {@code .type} entry; a differential drive
   * base a {@code .type} of "DifferentialDrive", a sendable chooser has it set to "String Chooser"; a hypothetical
   * 2D point would have it set to "Point2D".
   */
  private static final String TYPE_NAME = "ReefState";

  /**
   * The single instance of the point type. By convention, this is a {@code public static final}
   * field and the constructor is private to ensure only a single instance of the data type exists.
   */
  public static final ReefStateType Instance = new ReefStateType();

  private ReefStateType() {
    super(TYPE_NAME, ReefState.class);
  }

  @Override
  public Function<Map<String, Object>, ReefState> fromMap() {
    return map -> new ReefState(
        Maps.getOrDefault(map, "L1", (Long)0l),
        Maps.getOrDefault(map, "L2", (Long)0l),
        Maps.getOrDefault(map,"L3", (Long)0l),
        Maps.getOrDefault(map,"L4",(Long)0l)
    );
  }

  @Override
  public ReefState getDefaultValue() {
    return new ReefState(0l, 0l,0l,0l);
  }
}