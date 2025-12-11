/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author Gokhan
 */

public class Product
{
    private int ID;
    private String Name;
    private String Description;
    private String Color;
    private String Size;
    private double Price;

    public Product(int ID, String Name, String Description, String Color, String Size, double Price)
    {
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
        this.Color = Color;
        this.Size = Size;
        this.Price = Price;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getColor() {
        return Color;
    }

    public String getSize() {
        return Size;
    }

    public double getPrice() {
        return Price;
    }

    @Override
    public String toString() {
        return "Product{" + "ID=" + ID + ", Name=" + Name + ", Description=" + Description + ", Color=" + Color + ", Size=" + Size + ", Price=" + Price + '}';
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
}
