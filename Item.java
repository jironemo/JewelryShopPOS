/**
 * Item
 */
public class Item {

    String id;
    String name;
    Weight weight;
    Weight depreciation;

    public Item() {
        this.id = "";
        this.name = "";
        this.weight = new Weight();
        this.depreciation = new Weight();
    }

    public Item(String id, String name, Weight weight, Weight depreciation) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.depreciation = depreciation;
    }

    public String getId() {
        return (this.id);
    }

    public String getName() {
        return this.name;
    }

    public Weight getWeight() {
        return this.weight;
    }

    public Weight depreciation() {
        return this.depreciation;
    }
}