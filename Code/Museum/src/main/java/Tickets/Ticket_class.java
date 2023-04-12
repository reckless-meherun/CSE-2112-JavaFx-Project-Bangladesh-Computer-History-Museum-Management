package Tickets;

import People.Visitor;

import java.sql.Date;

public class Ticket_class implements Ticket{
    private Date issue_date;
    private int price;
    private int discount;
    private People.Visitor visitor;
    public Ticket_class()
    {
        this.issue_date = null;
        this.price = 0;
        this.discount = 0;
    }
    public Ticket_class(Date issue, int price, int discount)
    {
        this.issue_date = issue;
        this.price = price;
        this.discount = discount;
    }
    public void setIssue_date(Date date)
    {
        this.issue_date = date;
    }
    public Date getIssue_date()
    {
        return this.issue_date;
    }
    @Override
    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
    @Override
    public People.Visitor getVisitor()
    {
        return this.visitor;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }
    @Override
    public int getPrice() {
        return price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public int getDiscount()
    {
        return this.discount;
    }

    public void Print_Ticket()
    {
        System.out.println("Print Ticket...");
        // There are a lot of things to be added here
    }
    public void Print_Map()
    {
        System.out.println("Print Map...");
        // There are many things to add
    }
}
