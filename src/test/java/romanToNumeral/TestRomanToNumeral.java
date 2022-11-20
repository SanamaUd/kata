package romanToNumeral;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRomanToNumeral {

  private Solution systemUnderTest;

  public TestRomanToNumeral(Solution solution){
    this.systemUnderTest = solution;
  }

  @Test
  public void convertTest(){
    assertEquals("", systemUnderTest.convert(0));
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
    assertEquals("XXX", systemUnderTest.convert(30));
    assertEquals("XL", systemUnderTest.convert(40));
    assertEquals("XCV", systemUnderTest.convert(95));
    assertEquals("C", systemUnderTest.convert(100));
    assertEquals("CXI", systemUnderTest.convert(111));
    assertEquals("CCLIII", systemUnderTest.convert(253));
    assertEquals("CDXLVIII", systemUnderTest.convert(448));
    assertEquals("MMXXIII", systemUnderTest.convert(2023));
    assertEquals("MCMXCII", systemUnderTest.convert(1992));
    assertEquals("MCCCLXXII", systemUnderTest.convert(1372));
    assertEquals("MDCCLXXIX", systemUnderTest.convert(1779));
    assertEquals("MCMXCIV", systemUnderTest.convert(1994));
    assertEquals("MMMMDCXCIX", systemUnderTest.convert(4699));
    assertEquals("MMMMMDCIII", systemUnderTest.convert(5603));
  }
}
