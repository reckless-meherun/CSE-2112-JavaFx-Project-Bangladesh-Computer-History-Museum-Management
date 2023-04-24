package Tickets;

import java.sql.Date;

public interface Ticket {
    public Date getIssue_date();
    public void setIssue_date(Date dd);
    public void setVisitor(application.museum.People.Visitor visitor);
    public application.museum.People.Visitor getVisitor();
    public void setPrice(int price);
    public int getPrice();
}
