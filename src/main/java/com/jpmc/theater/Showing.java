package com.jpmc.theater;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Showing {
    private final Movie movie;
    private final int sequenceOfTheDay;
    private final LocalDateTime showStartTime;
    private final CurrencyUnit currencyUnit;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
        this.currencyUnit = movie.getTicketPrice().getCurrency();
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public MonetaryAmount calculateTicketPrice() {
        return movie.getTicketPrice().subtract(getDiscount()).with(Monetary.getDefaultRounding());
    }

    private MonetaryAmount getDiscount() {
        return Collections.max(List.of(getSpecialDiscount(), getSequenceDiscount(), getDayDiscount(), getTimeDiscount()));
    }

    private MonetaryAmount getSpecialDiscount() {
        return movie.isSpecialMovie() ? movie.getTicketPrice().multiply(0.2) : Money.zero(currencyUnit);
    }

    private MonetaryAmount getSequenceDiscount() {
        switch (sequenceOfTheDay) {
            case 1:
                return FastMoney.of(3, currencyUnit);
            case 2:
                return FastMoney.of(2, currencyUnit);
            default:
                return Money.zero(currencyUnit);
        }
    }

    private MonetaryAmount getDayDiscount() {
        return showStartTime.getDayOfMonth() == 7 ? FastMoney.of(1, currencyUnit) : Money.zero(currencyUnit);
    }

    private MonetaryAmount getTimeDiscount() {
        return isMatinee() ? movie.getTicketPrice().multiply(0.25) : Money.zero(currencyUnit);
    }

    private boolean isMatinee() {
        var localTime = showStartTime.toLocalTime();
        return (!localTime.isBefore(LocalTime.of(11, 0))
                && !localTime.isAfter(LocalTime.of(16, 0)));
    }
}
