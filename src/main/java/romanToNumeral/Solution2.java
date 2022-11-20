package romanToNumeral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Solution2 {


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
  public Solution2(){
    existingRomanSymbol = Arrays.asList(RomanSymbol.values());
    existingRomanSymbol.sort(Comparator.comparingInt(RomanSymbol::getQuantity));
    existingRomanSymbol.sort(Comparator.reverseOrder()); // we want the bigger values first
  }


  public String convert(int value){
    List<Integer> decomposition = roughDecompose(Arrays.asList(value));
    String result = "";
    for(int item: decomposition){
      result += translate(item);
    }
    return result;
  }

  private List<Integer> roughDecompose(List<Integer> values){
    values = new ArrayList<>(values);
    int valueToDecompose = values.get(values.size() - 1);
    for(RomanSymbol romanSymbol: existingRomanSymbol){
      if(valueToDecompose == 0){
        return values;
      }
      int leftover = valueToDecompose % romanSymbol.getQuantity();
      int quantity = valueToDecompose / romanSymbol.getQuantity();
      if(quantity != 0){ // multiply the current letter
        int rough = quantity * romanSymbol.getQuantity();
        values = replaceLastValue(values, Arrays.asList(rough));
        values.add(leftover);
        valueToDecompose = leftover;
      }
      if(romanSymbol.canBeUsedForLowerValue(leftover)){ //subtract the current letter
        List<Integer> specialDecomposing = Arrays.asList(-romanSymbol.getDifferenceAllowed(), romanSymbol.getQuantity());
        values = replaceLastValue(values, specialDecomposing);
        leftover = valueToDecompose - (specialDecomposing.stream().reduce(0, Integer::sum));
        values.add(leftover);
      }
      valueToDecompose = leftover;
    }
    return values;
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
