package com.programmers.mycoffee.utils;


import com.programmers.mycoffee.model.OrderStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Utils {


    public static UUID createUUID() {
        return UUID.randomUUID();
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    public static OrderStatus orderStatusCheck(LocalDateTime now) {
        LocalTime twoPM = LocalTime.of(14, 0);
        if (now.toLocalTime().isBefore(twoPM)) {
            return OrderStatus.READY_FOR_DELIVERY;
        } else {
            return OrderStatus.PAYMENT_CONFIRMED;
        }
    }
    public static OrderStatus orderStatusCheck(OrderStatus orderStatus) {
        if (orderStatus.equals(OrderStatus.ACCEPTED) ||
            orderStatus.equals(OrderStatus.PAYMENT_CONFIRMED) ||
            orderStatus.equals(OrderStatus.READY_FOR_DELIVERY))
        {
            return OrderStatus.CANCELLED;
        } else {
            return orderStatus;
        }
    }
}
