package com.countryLocator.countrylocator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.countryLocator.CountryLocator;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PerformanceTest {
    private CountryLocator locator = new CountryLocator();

    @Benchmark
    public void testGetCountryCode() {
        for (int i = 0; i < 100; i++) {
            locator.getCountryCode(20.5937, 73.1947);
        }
    }
}
