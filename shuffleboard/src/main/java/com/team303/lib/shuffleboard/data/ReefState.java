package com.team303.lib.shuffleboard.data;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.Map;

public class ReefState extends ComplexData<ReefState> {
   private long[] values;


   public ReefState(long L1, long L2, long L3, long L4) {
      this.values = new long[]{L1,L2,L3,L4};
   }

   public long[] getState(){
    return values;
   }


   public Map<String, Object> asMap() {
      return Map.of("State",values);
   }
}