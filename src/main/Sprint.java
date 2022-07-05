package main;

import java.util.Arrays;

public class Sprint {

    private final int capacity;
    private final int ticketsLimit;
    private int ticketCount;
    private int currCapacity;

    private Ticket[] tickets;

    public Sprint(int capacity, int ticketsLimit) {
        this.capacity = capacity;
        this.ticketsLimit = ticketsLimit;
        tickets = new Ticket[0];
    }

    public boolean addUserStory(UserStory userStory) {
        if (userStory != null && storyCont(userStory.getDependencies()) &&
                !userStory.isCompleted() && ticketCount < ticketsLimit &&
                currCapacity + userStory.getEstimate() <= capacity) {

            tickets = Arrays.copyOf(tickets, ticketCount + 1);
            tickets[ticketCount++] = userStory;

            currCapacity += userStory.getEstimate();
            return true;
        } return false;
    }

    public boolean addBug(Bug bugReport) {
        if (bugReport != null &&
                !bugReport.isCompleted() &&  ticketCount + 1 <= ticketsLimit &&
                currCapacity + bugReport.getEstimate() <= capacity) {

            this.tickets = Arrays.copyOf(this.tickets, ticketCount + 1);
            currCapacity += bugReport.getEstimate();
            this.tickets[ticketCount++] = bugReport;
            return true;

        }  return false;
    }

    public Ticket[] getTickets() {
        return Arrays.copyOf(tickets, tickets.length);
    }

    public int getTotalEstimate() {
        return currCapacity;
    }

    private boolean storyCont(UserStory[] userStory) {
        for (UserStory story:
                userStory) {
            // if (!Arrays.asList(this.tickets).contains(story)) return false;

            boolean isCont = false;

            for (Ticket ticket:
                 tickets) {
                if (story.equals(ticket)) {
                    isCont = true;
                    break;
                }
            }

            if (!isCont) return false;
        }

        return true;
    }
}
