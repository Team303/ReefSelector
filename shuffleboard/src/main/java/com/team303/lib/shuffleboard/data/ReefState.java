package com.team303.lib.shuffleboard.data;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.Map;

public class ReefState extends ComplexData<ReefState> {
   private long L1;
   private long L2;
   private long L3;
   private long L4;


   public ReefState(long L1, long L2, long L3, long L4) {
      this.L1 = L1;
      this.L2=L2;
      this.L3=L3;
      this.L4=L4;
   }

   public long getL1(){
    return L1;
   }
   public long getL2(){
    return L2;
   }
   public long getL3(){
    return L3;
   }
   public long getL4(){
    return L4;
   }


   public Map<String, Object> asMap() {
      return Map.of("L1", L1, "L2", L2,"L3",L3,"L4",L4);
   }
}