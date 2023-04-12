package Tickets;

import java.sql.Date;

import static java.lang.Math.max;

public class Pre_booking extends Ticket_class{
    private Date date_of_visiting;
    private boolean payment_done;
    private int due_payment;

    public Pre_booking()
    {
        this.date_of_visiting = null;
        this.payment_done = false;
        this.due_payment = 0;
    }
    public Pre_booking(Date date, boolean payment_status, int due)
    {
        this.date_of_visiting = date;
        this.payment_done = payment_status;
        this.due_payment = due;
    }

    public void update_date_of_visiting(Date date)
    {
        this.date_of_visiting = date;
    }
    public Date getDate_of_visiting()
    {
        return this.date_of_visiting;
    }

    public boolean isPayment_done() {
        return payment_done;
    }
    public void setPayment_done(boolean payment_done) {
        this.payment_done = payment_done;
    }

    public void setDue_payment(int due_payment) {
        this.due_payment = due_payment;
    }

    public int getDue_payment() {
        return due_payment;
    }
    public void update_due_payment(int new_payment)
    {
        this.due_payment = max (0, this.due_payment - new_payment);
    }
}
