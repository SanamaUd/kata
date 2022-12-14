package romanToNumeral;

import java.util.Arrays;
import java.util.List;

/**
 * This solution reads each digit of the given arabic number from left to right,
 * and convert each digit into Roman numeral with a simple logic.
 *
 * Pros: simple logic
 * Cons: Can only support value from 0 to 9999. Requires many hardcoded Roman Values.
 */
public class Solution1 implements Solution{

  enum MapperDigit{
    ONE("1", "I", "X", "C", "M"),
    TWO("2", "II", "XX", "CC", "MM"),
    THREE("3", "III", "XXX", "CCC", "MMM"),
    FOUR("4", "IV", "XL", "CD", "MMMM"),
    FIVE("5", "V", "L", "D", "MMMMM"),
    SIX("6", "VI", "LX", "DC", "MMMMMM"),
    SEVEN("7", "VII", "LXX", "DCC", "MMMMMMM"),
    EIGHT("8", "VIII", "LXXX", "DCCC", "MMMMMMMM"),
    NINE("9", "IX", "XC", "CM", "MMMMMMMMM");

    private String arabicSymbol;
    private List<String> romanSymbols;

    MapperDigit(String arabicSymbol, String romanSymbolUnit, String romanSymbolDec, String romanSymbolCen){
      this(arabicSymbol, romanSymbolUnit, romanSymbolDec, romanSymbolCen, null);
    }
    MapperDigit(String arabicSymbol, String romanSymbolUnit, String romanSymbolDec, String romanSymbolCen, String romanSymbolMil){
      this.arabicSymbol = arabicSymbol;
      this.romanSymbols = Arrays.asList(romanSymbolUnit, romanSymbolDec, romanSymbolCen, romanSymbolMil);
    }

    public String findRomanSymbol(int orderMagnitude){
      try{
        return romanSymbols.get(orderMagnitude);
      } catch (RuntimeException e){
        throw new RuntimeException(String.format("Order of Magnitude %d not found", orderMagnitude), e);
      }
    }
  }

  public String convert(int value){
    String arabicNumeral = Integer.toString(value);
    StringBuilder result = new StringBuilder();
    for(int orderMagnitude = 0; orderMagnitude <arabicNumeral.length(); orderMagnitude++){
      String arabicSymbol = String.valueOf(arabicNumeral.charAt(orderMagnitude));
      result.append(convertToRoman(arabicSymbol, arabicNumeral.length() -1 - orderMagnitude));
    }
    return result.toString();
  }

  private String convertToRoman(String arabicSymbol, int orderMagnitude){
    return Arrays.stream(MapperDigit.values())
        .filter(mapper -> arabicSymbol.equals(mapper.arabicSymbol))
        .map(mapper -> mapper.findRomanSymbol(orderMagnitude))
        .findFirst()
        .orElse(""); // zero

  }
}
