package romanToNumeral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution2 implements Solution{

  enum RomanSymbol{
    I(1, 0),
    V(5, 1),
    X(10, 1),
    L(50, 10),
    C(100, 10),
    D(500, 100),
    M(1000, 100);

    private int arabicNumeral;
    private int differenceAllowed;

    RomanSymbol(int quantity, int differenceAllowed){
      this.arabicNumeral = quantity;
      this.differenceAllowed = differenceAllowed;
    }

    public int getArabicNumeral(){
      return arabicNumeral;
    }
    public int getDifferenceAllowed(){
      return differenceAllowed;
    }

    public boolean canRepresent(int value){
      return arabicNumeral - value <= differenceAllowed;
    }
  }

  private List<RomanSymbol> existingRomanSymbols;

  public Solution2(){
    existingRomanSymbols = Arrays.asList(RomanSymbol.values());
    existingRomanSymbols.sort(Comparator.comparingInt(RomanSymbol::getArabicNumeral));
    existingRomanSymbols.sort(Comparator.reverseOrder()); // we want the bigger values first
  }


  public String convert(int value){
    List<Integer> decomposition = decompose(value);
    StringBuilder result = new StringBuilder();
    for(int item: decomposition){
      result.append(translateEachArabicNumeral(item));
    }
    return result.toString();
  }

  /**
   * Decompose a value in several bits that can be easily re-used to convert into roman numeral. For example,
   * if input is 95, then output is [10,100,5]
   * If input is 5843, then output is [5000,500,300,10,50,3]
   * @param valueToDecompose value to decompose into bits
   * @return
   */
  private List<Integer> decompose(int valueToDecompose){
    List<Integer> decomposition = new ArrayList<>();
    for(RomanSymbol romanSymbol: existingRomanSymbols){
      if(valueToDecompose == 0){
        return decomposition;
      }
      int remainder = valueToDecompose % romanSymbol.getArabicNumeral();
      int quotient = valueToDecompose / romanSymbol.getArabicNumeral();
      if(quotient != 0){ // value is higher or equal to Roman numeral aka multiply the current letter
        decomposition.addAll(Arrays.asList(quotient * romanSymbol.getArabicNumeral()));
        valueToDecompose = remainder;
      }
      if(romanSymbol.canRepresent(remainder)){ //remainder value is lower BUT Roman Numeral should still be used to represent the remainder
        List<Integer> specialDecomposition = Arrays.asList(romanSymbol.getDifferenceAllowed(), romanSymbol.getArabicNumeral());
        remainder = valueToDecompose - (romanSymbol.getArabicNumeral() - romanSymbol.getDifferenceAllowed());
        decomposition.addAll(specialDecomposition);
      }
      valueToDecompose = remainder;
    }
    return decomposition;
  }

  private String translateEachArabicNumeral(int value){
    StringBuilder result = new StringBuilder();
    for(RomanSymbol symbol: existingRomanSymbols){
      int quantity = value/ symbol.getArabicNumeral();
      if(quantity != 0){
        for(int i = 0; i < quantity; i++){
          result.append(symbol.name());
        }
        return result.toString();
      }
    }
    return result.toString();
  }
}
