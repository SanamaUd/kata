package romanToNumeral;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import romanToNumeral.Solution1;

public class Solution1Test {

  private Solution1 systemUnderTest = new Solution1();

  @Test
  public void testCommonCase(){
    assertEquals("I", systemUnderTest.convert(1));
    assertEquals("II", systemUnderTest.convert(2));
    assertEquals("III", systemUnderTest.convert(3));
    assertEquals("IV", systemUnderTest.convert(4));
    assertEquals("V", systemUnderTest.convert(5));
    assertEquals("VI", systemUnderTest.convert(6));
    assertEquals("VII", systemUnderTest.convert(7));
    assertEquals("VIII", systemUnderTest.convert(8));
    assertEquals("IX", systemUnderTest.convert(9));
    assertEquals("X", systemUnderTest.convert(10));
    assertEquals("XIII", systemUnderTest.convert(13));
    assertEquals("C", systemUnderTest.convert(100));
    assertEquals("CCLIII", systemUnderTest.convert(253));
    assertEquals("MMXXIII", systemUnderTest.convert(2023));
    assertEquals("MCMXCII", systemUnderTest.convert(1992));
    assertEquals("MMMMDCXCIX", systemUnderTest.convert(4699));

  }
}