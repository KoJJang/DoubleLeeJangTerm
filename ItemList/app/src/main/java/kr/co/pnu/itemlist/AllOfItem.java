package kr.co.pnu.itemlist;

/**
 * Created by A on 2017-04-23.
 */

public class AllOfItem {
    private String Name;
    private int Price;
    private int Effect_Health;
    private int Effect_Hungry;
    private int Effect_Happiness;
    private String Category;

    public AllOfItem(String _name, int _price, int _effect_health,
                     int _effect_hungry, int _effect_happniess ,String _category)
    {
        this.Name = _name;
        this.Price = _price;
        this.Effect_Health = _effect_health;
        this.Effect_Hungry = _effect_hungry;
        this.Effect_Happiness = _effect_happniess;
        this.Category = _category;
    }

    public String getName() { return Name; }
    public int getPrice() { return Price; }
    public int getEffect_Health() { return Effect_Health; }
    public int getEffect_Hungry() { return Effect_Hungry; }
    public int getEffect_Happiness() { return Effect_Happiness; }
    public String getCategory() { return Category;}
}
