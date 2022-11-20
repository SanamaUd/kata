package romanToNumeral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NonSolution2Bis implements Solution{


  enum RomanSymbol{
    I(1, q -> q<=3, 0),
    V(5, q -> q<=1, 1),
    X(10, q-> q<=3, 1),
    L(50, q-> q<=1, 10),
    C(100, q -> q<=3, 10),
    D(500, q->q<=1, 100),
    M(1000, q -> true, 100);

    private int quantity;
    private Predicate<Integer> ruleOnQuantity;
    private int differenceAllowed;

    RomanSymbol(int quantity, Predicate<Integer> ruleOnQuantity, int differenceAllowed){
      this.quantity = quantity;
      this.ruleOnQuantity = ruleOnQuantity;
      this.differenceAllowed = differenceAllowed;
    }

    public int getQuantity(){
      return quantity;
    }
    public int getDifferenceAllowed(){
      return differenceAllowed;
    }
    public boolean isQuantityAllowed(int quantity){
      return ruleOnQuantity.test(quantity);
    }
    public boolean canBeUsedForLowerValue(int value){
      return quantity - value <= differenceAllowed;
    }
  }

  private List<RomanSymbol> existingRomanSymbol;
  public NonSolution2Bis(){
    existingRomanSymbol = Arrays.asList(RomanSymbol.values());
    existingRomanSymbol.sort(Comparator.comparingInt(RomanSymbol::getQuantity));
    existingRomanSymbol.sort(Comparator.reverseOrder()); // we want the bigger values first
  }


  public String convert(int value){
    String result = "";
    result = convertInternal(value, result);
    return result;
  }

  private String convertInternal(int value, String result){
    if(value <=0){
      return result;
    }
    int valueToDecompose = value;

    int magnitude = (int)(Math.log10(valueToDecompose));
    List<RomanSymbol> candidates = Stream.of(RomanSymbol.values())
        .filter(symbol -> (int) (Math.log10(symbol.getQuantity())) == magnitude)// || (int) (Math.log10(symbol.getQuantity())) == magnitude+1)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

    for(RomanSymbol romanSymbol: candidates){
      int leftover = valueToDecompose % romanSymbol.getQuantity();
      int quantity = valueToDecompose / romanSymbol.getQuantity();

      if(String.valueOf(valueToDecompose).startsWith("4")
          || String.valueOf(valueToDecompose).startsWith("9")){
        //if more than three letters required, subtract from bigger letter
        RomanSymbol nextInRange = findHigherRange(valueToDecompose);
        int diff = Math.abs(valueToDecompose-nextInRange.getQuantity());
        result += findSymbol((int)Math.pow(10,magnitude))+nextInRange.name();
        if(String.valueOf(valueToDecompose).startsWith("9")){

          result+=convertInternal(diff, "");
        }
        break;
      }
      if(quantity != 0){ // multiply the current letter
        for(int i =0; i< quantity; i++){
          result += romanSymbol.name();
        }
        valueToDecompose = leftover;
        if(leftover > 0){
          result = convertInternal(leftover, result);
          break;
        }
      }
//      if(romanSymbol.canBeUsedForLowerValue(leftover)){ //subtract the current letter
//        List<Integer> specialDecomposing = Arrays.asList(-romanSymbol.getDifferenceAllowed(), romanSymbol.getQuantity());
//        values = replaceLastValue(values, specialDecomposing);
//        leftover = valueToDecompose - (specialDecomposing.stream().reduce(0, Integer::sum));
//        values.add(leftover);
//      }
      valueToDecompose = leftover;
    }

    return result;
  }


  private RomanSymbol findHigherRange(int value){
    return Stream.of(RomanSymbol.values())
        .filter(symbol -> symbol.getQuantity() > value)
        .findFirst().orElse(RomanSymbol.M);
  }

  private String findSymbol(int value){
    return Stream.of(RomanSymbol.values())
        .filter(romanSymbol -> value == romanSymbol.getQuantity())
        .map(RomanSymbol::name)
        .findFirst().orElse("");
  }
  private List<Integer> replaceLastValue(List<Integer> toReplace, List<Integer> valuesForReplacement){
    List<Integer> replacement = new ArrayList<>();
    if(toReplace.size() > 1){
      replacement = toReplace.subList(0, toReplace.size() - 1);
    }
    replacement.addAll(valuesForReplacement);
    return replacement;
  }

  private String translate(int value){
    int abs = Math.abs(value);
    String result = "";
    for(RomanSymbol symbol: existingRomanSymbol){
      int quantity = abs/ symbol.getQuantity();
      if(quantity != 0){
        for(int i = 0; i < quantity; i++){
          result += symbol.name();
        }
        return result;
      }
    }
    return result;
  }
}
