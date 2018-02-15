package be.qnh.apps.sport.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
   
   private Calculator calculator;
   
   @Before
   public void setUp() {
      this.calculator = new Calculator();
   }
   
   @Test
   public void testAdd() {
      Assert.assertEquals(3, this.calculator.add(1, 2));
   }
   
   @Test
   public void testSubtract() {
      Assert.assertEquals(3, this.calculator.subtract(5,2));
   }
   
   @After
   public void tearDown() {
      // teardown stuff
      this.calculator = null;
   }

}
