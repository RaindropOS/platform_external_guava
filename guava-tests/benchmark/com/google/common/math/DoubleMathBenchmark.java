/*
 * Copyright (C) 2011 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.math;

import static com.google.common.math.MathBenchmarking.ARRAY_MASK;
import static com.google.common.math.MathBenchmarking.ARRAY_SIZE;
import static com.google.common.math.MathBenchmarking.RANDOM_SOURCE;
import static com.google.common.math.MathBenchmarking.randomDouble;
import static com.google.common.math.MathBenchmarking.randomPositiveDouble;

import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

/**
 * Tests for the non-rounding methods of {@code DoubleMath}.
 *
 * @author Louis Wasserman
 */
public class DoubleMathBenchmark extends SimpleBenchmark {
  private static final double[] positiveDoubles = new double[ARRAY_SIZE];
  private static final int[] factorials = new int[ARRAY_SIZE];
  private static final double [] doubles = new double[ARRAY_SIZE];

  @Override
  protected void setUp() {
    for (int i = 0; i < ARRAY_SIZE; i++) {
      positiveDoubles[i] = randomPositiveDouble();
      doubles[i] = randomDouble(Long.SIZE);
      factorials[i] = RANDOM_SOURCE.nextInt(100);
    }
  }

  public long timeLog2(int reps) {
    long tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += Double.doubleToRawLongBits(DoubleMath.log2(positiveDoubles[j]));
    }
    return tmp;
  }

  public long timeFactorial(int reps) {
    long tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += Double.doubleToRawLongBits(DoubleMath.factorial(factorials[j]));
    }
    return tmp;
  }

  public int timeIsMathematicalInteger(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      if (DoubleMath.isMathematicalInteger(doubles[j])) {
        tmp++;
      }
    }
    return tmp;
  }

  public int timeIsPowerOfTwo(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      if (DoubleMath.isPowerOfTwo(doubles[j])) {
        tmp++;
      }
    }
    return tmp;
  }

  public static void main(String[] args) {
    Runner.main(DoubleMathBenchmark.class, args);
  }
}
