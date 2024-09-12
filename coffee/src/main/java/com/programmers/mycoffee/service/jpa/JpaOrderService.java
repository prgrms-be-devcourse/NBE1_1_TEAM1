package com.programmers.mycoffee.service.jpa;

import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.model.OrderItem;
import com.programmers.mycoffee.model.entity.OrderEntitiy;
import com.programmers.mycoffee.model.entity.OrderItemEntity;
import com.programmers.mycoffee.model.entity.ProductEntity;
import com.programmers.mycoffee.repository.jpa.JpaOrderItemRepository;
import com.programmers.mycoffee.repository.jpa.JpaOrderRepository;
import com.programmers.mycoffee.repository.jpa.JpaProductRepository;
import com.programmers.mycoffee.service.OrderService;
import com.programmers.mycoffee.utils.OrderMapTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JpaOrderService implements OrderService {

    private final JpaOrderRepository jpaOrderRepository;
    private final JpaOrderItemRepository orderItemRepository;
    private final JpaProductRepository productRepository;

    //사용자의 Email 유/무에 따른 동작
    @Transactional
    public Order createOrder(Email email, String address, String postcode, List<OrderItem> orderItems) {
        userEmailChaeck(jpaOrderRepository.findByEmailAndAddressAndPostCode(email.getAddress(), address, postcode),orderItems, email, address, postcode);
        return null;
    }

    //사용자의 Email 유/무에 따른 동작
    private void userEmailChaeck(Optional<OrderEntitiy> byEmail, List<OrderItem> orderItems,Email email, String address, String postcode) {
        if (byEmail.isEmpty()) {
            OrderEntitiy orderEntitiy = OrderMapTo.mapToOrderEntity(email.getAddress(),address,postcode);
            jpaOrderRepository.save(orderEntitiy);
            addOrder(orderEntitiy, orderItems);
        } else {
            addOrder(byEmail.get(), orderItems);
        }
    }

    //사용자 주문내역 추가
    private void addOrder(OrderEntitiy orderEntitiy, List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            Optional<ProductEntity> byId = productRepository.findById(orderItem.productId());
            if (byId.isPresent()) {
                OrderItemEntity orderItemEntity = OrderMapTo.mapToOrderItemEntity(orderEntitiy, byId.get(), orderItem);
                orderEntitiy.settingOrderItemList(orderItemRepository.save(orderItemEntity));
            }
        }
    }

    @Override
    public List<Order> findAll() {
        return OrderMapTo.mapToOrderList(jpaOrderRepository.findAll());
    }

    @Override
    public Order update(Order order) {
        Optional<OrderEntitiy> byId = jpaOrderRepository.findById(order.getOrderId());
        byId.orElseThrow();
        OrderEntitiy orderEntitiy = byId.get();
//        if (Utils.updateOrderStatusCheck(orderEntitiy.getOrderStatus())) {
//            orderEntitiy.
//        }
        return order;
    }

    @Override
    public Order findById(UUID orderID) {
        Optional<OrderEntitiy> byId = jpaOrderRepository.findById(orderID);
        byId.orElseThrow();
        return OrderMapTo.mapToOrderEntity(byId.get());
    }

    @Override
    public Order findByName(String productName) {
//        jpaOrderRepository.find
        return null;
    }
}
