
public class Main {
    public static void main(String[] args) {
//        Item i = new Item("G0004", "ရွှေခြေကျင်း", new Weight(1,1,0), new Weight(0,0,1));
//       int k =	new  Utilities().addItem(i);
      int k = new Utilities().deleteData("G0004","Stock");
       System.out.println(k);
    }
}