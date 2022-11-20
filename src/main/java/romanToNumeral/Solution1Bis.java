package romanToNumeral;

import java.util.Arrays;
import java.util.List;

public class Solution1Bis implements Solution{

  enum MapperDigit{
    ONE("1", "I", "X", "C", "M"),
    TWO("2", "II", "XX", "CC", "MM"),
    THREE("3", "III", "XXX", "CCC", "MMM"),
    FOUR("4", "IV", "XL", "CD", "MMMM"),
    FIVE("5", "V", "L", "D", "MMMMM"),
    SIX("6", "VI", "LX", "DC"),
    SEVEN("7", "VII", "LXX", "DCC"),
    EIGHT("8", "VIII", "LXXX", "DCCC"),
    NINE("9", "IX", "XC", "CM");

    private String arabicSymbol;
    private List<String> romanSymbols;

    MapperDigit(String arabicSymbol, String romanSymbolUnit, String romanSymbolDec, String romanSymbolCen){
      this(arabicSymbol, romanSymbolUnit, romanSymbolDec, romanSymbolCen, null);
    }
    MapperDigit(String arabicSymbol, String romanSymbolUnit, String romanSymbolDec, String romanSymbolCen, String romanSymbolMil){
      this.arabicSymbol = arabicSymbol;
      this.romanSymbols = Arrays.asList(romanSymbolUnit, romanSymbolDec, romanSymbolCen, romanSymbolMil);
    }

    public String findRomanSymbol(int index){
      try{
        return romanSymbols.get(index);
      } catch (RuntimeException e){
        throw new RuntimeException("Not supported");
      }
    }
  }

  public String convert(int value){
    String number = Integer.toString(value);
    String result = "";
    for(int index = 0; index <number.length(); index++){
      String arabicSymbol = String.valueOf(number.charAt(index));
      result = result + convertToRoman(arabicSymbol, number.length() -1 - index);
    }
    return result;
  }

  public String convertToRoman(String arabicSymbol, int index){
    return Arrays.stream(MapperDigit.values())
        .filter(mapper -> arabicSymbol.equals(mapper.arabicSymbol))
        .map(mapper -> mapper.findRomanSymbol(index))
        .findFirst()
        .orElse(""); // zero

  }
}
