package com.team303.lib.shuffleboard.data;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.Map;

public class ReefState extends ComplexData<ReefState> {
   private final int L1;
   private final int L2;
   private final int L3;
   private final int L4;


   public ReefState(int L1, int L2, int L3, int L4) {
      this.L1 = L1;
      this.L2=L2;
      this.L3=L3;
      this.L4=L4;
   }

   public double getL1(){
    return L1;
   }
   public double getL2(){
    return L2;
   }
   public double getL3(){
    return L3;
   }
   public double getL4(){
    return L4;
   }


   public Map<String, Object> asMap() {
      return Map.of("L1", L1, "L2", L2,"L3",L3,"L4",L4);
   }
}