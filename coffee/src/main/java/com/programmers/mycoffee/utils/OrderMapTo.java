package com.programmers.mycoffee.utils;


import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.model.OrderItem;
import com.programmers.mycoffee.model.entity.OrderEntitiy;
import com.programmers.mycoffee.model.entity.OrderItemEntity;
import com.programmers.mycoffee.model.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;

import static com.programmers.mycoffee.utils.Utils.orderStatusCheck;


public class OrderMapTo {

    private static LocalDateTime timeCheck() {
        return LocalDateTime.now();
    }

    public static Order mapToOrderEntity(OrderEntitiy orderEntitiy) {
        return new Order(
                orderEntitiy.getId(),
                new Email(orderEntitiy.getEmail()),
                orderEntitiy.getAddress(),
                orderEntitiy.getPostCode(),
                orderEntitiy.getOrderItemList().stream()
                        .map(orderItemEntity -> new OrderItem(
                                orderItemEntity.getId(),
                                orderItemEntity.getCategory(),
                                orderItemEntity.getPrice(),
                                Integer.parseInt(orderItemEntity.getQuantity().toString())
                        )).toList(),
                orderEntitiy.getOrderStatus(),
                orderEntitiy.getCreatedAt(),
                orderEntitiy.getUpdateAt()
        );
    }

    public static OrderEntitiy mapToOrderEntity(String email, String address, String postcode) {
        return OrderEntitiy.builder()
                .id(Utils.createUUID())
                .email(email)
                .address(address)
                .postCode(postcode)
                .updateAt(timeCheck())
                .orderStatus(orderStatusCheck(timeCheck()))
                .build();
    }

    public static List<Order> mapToOrderList(List<OrderEntitiy> orderEntitiy) {
        return orderEntitiy.stream()
                .map(orderEntitiy1 -> new Order(
                        orderEntitiy1.getId(),
                        new Email(orderEntitiy1.getEmail()),
                        orderEntitiy1.getAddress(),
                        orderEntitiy1.getPostCode(),
                        orderEntitiy1.getOrderItemList().stream()
                                .map(orderItemList -> new OrderItem(
                                        orderItemList.getId(),
                                        orderItemList.getCategory(),
                                        orderItemList.getPrice(),
                                        Integer.parseInt(String.valueOf(orderItemList.getQuantity()))
                                )).toList(),
                        orderEntitiy1.getOrderStatus(),
                        orderEntitiy1.getCreatedAt(),
                        orderEntitiy1.getUpdateAt()
                )).toList();
    }


//   private static OrderItem toOrderItem(OrderItemEntity orderItem) {
//      ProductEntity productEntity = orderItem.getProductEntity();
//      OrderEntitiy orderEntitiy = orderItem.getOrderEntitiy();
//      return OrderItem.builder()
//              .address(orderEntitiy.getAddress())
//              .postCode(orderEntitiy.getPostCode())
//              .orderStatus(orderEntitiy.getOrderStatus())
//              .productName(productEntity.getProductName())
//              .category(productEntity.getCategory())
//              .description(productEntity.getDescription())
//              .quantity(orderItem.getQuantity())
//              .totalPrice(productEntity.getPrice() * orderItem.getQuantity())
//              .build();
//   }
////
   public static OrderItemEntity mapToOrderItemEntity(OrderEntitiy orderEntitiy, ProductEntity productEntity, OrderItem productList) {
      return OrderItemEntity.builder()
              .id(Utils.createUUID())
              .orderEntitiy(orderEntitiy)
              .productEntity(productEntity)
              .quantity((long) productList.quantity())
              .category(productList.category())
              .price((long) productList.quantity() * productList.price())
              .updatedAt(timeCheck())
              .build();
   }

}
