package com.programmers.mycoffee.repository;

import com.programmers.mycoffee.model.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.programmers.mycoffee.JdbcUtils.toLocalDateTime;
import static com.programmers.mycoffee.JdbcUtils.toUUID;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Order insert(Order order) {
        jdbcTemplate.update("INSERT INTO orders(order_id, email, address, postcode, order_status, created_at, updated_at) " +
                        "VALUES (UUID_TO_BIN(:orderId), :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)",
                toOrderParamMap(order));
        order.getOrderItems()
                .forEach(item ->
                        jdbcTemplate.update("INSERT INTO order_items(order_id, product_id, category, price, quantity, created_at, updated_at) " +
                                        "VALUES (UUID_TO_BIN(:orderId), UUID_TO_BIN(:productId), :category, :price, :quantity, :createdAt, :updatedAt)",
                                toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(), item)));
        return order;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT BIN_TO_UUID(order_id) AS order_id, email, address, postcode, order_status, created_at, updated_at FROM orders";
        return jdbcTemplate.query(sql, orderRowMapper);
    }

    @Override
    public Order update(Order order) {
        String sql = "UPDATE orders SET email = :email, address = :address, postcode = :postcode, order_status = :orderStatus, update_at = :updatedAt " +
                "WHERE order_id = UUID_TO_BIN(:orderId)";

        int updateCount = jdbcTemplate.update(sql, toOrderParamMap(order));
        if(updateCount != 1){
            throw new RuntimeException("업데이트가 없습니다");
        }

        order.getOrderItems().forEach(item ->
                jdbcTemplate.update("UPDATE order_items SET category = :category, price = :price, quantity = :quantity, update_at = :updatedAt " +
                                "WHERE order_id = UUID_TO_BIN(:orderId) AND product_id = UUID_TO_BIN(:productId)",
                        toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(), item)));

        return order;
    }

    @Override
    public Order findById(UUID orderId) {
        try{
            String sql = "SELECT BIN_TO_UUID(order_id) AS order_id, email, address, postcode, order_status, created_at, updated_at " +
                    "FROM orders WHERE order_id = UUID_TO_BIN(:orderId)";

            Map<String, Object> params = new HashMap<>();
            params.put("orderId", orderId.toString().getBytes());

            return jdbcTemplate.queryForObject(sql, params, orderRowMapper);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Order findByName(String productName) {
        String sql = "SELECT o.order_id, o.email, o.address, o.postcode, o.order_status, o.created_at, o.updated_at " +
                "FROM orders o " +
                "JOIN order_items oi ON o.order_id = oi.order_id " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "WHERE p.product_name = :productName";

        Map<String, Object> params = new HashMap<>();
        params.put("productName", productName);

        try{
            return jdbcTemplate.queryForObject(sql, params, orderRowMapper);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }


    private Map<String, Object> toOrderParamMap(Order order) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", order.getOrderId().toString().getBytes());
        paramMap.put("email", order.getEmail().getAddress());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());
        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderItem item) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", orderId.toString().getBytes());
        paramMap.put("productId", item.productId().toString().getBytes());
        paramMap.put("category", item.category().toString());
        paramMap.put("price", item.price());
        paramMap.put("quantity", item.quantity());
        paramMap.put("createdAt", createdAt);
        paramMap.put("updatedAt", updatedAt);
        return paramMap;
    }

    private final RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
        var orderId = toUUID(rs.getBytes("order_id"));
        var email = new Email(rs.getString("email"));
        var address = rs.getString("address");
        var postcode = rs.getString("postcode");
        var orderStatus = OrderStatus.valueOf(rs.getString("order_status"));
        var created_at = toLocalDateTime(rs.getTimestamp("created_at"));
        var updated_at = toLocalDateTime(rs.getTimestamp("updated_at"));

        List <OrderItem> orderItems = this.findOrderItemsByOrderId(orderId);

        return new Order(orderId, email, address, postcode, orderItems, orderStatus, created_at, updated_at);
    };

    private List<OrderItem> findOrderItemsByOrderId(UUID orderId){
        String sql = "SELECT BIN_TO_UUID(product_id) AS product_id, category, price, quantity, created_at, updated_at " +
                "FROM order_items WHERE order_id = UUID_TO_BIN(:orderId)";

        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId.toString().getBytes());

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new OrderItem(
                UUID.fromString(rs.getString("product_id")),
                Category.valueOf(rs.getString("category")),
                rs.getLong("price"),
                rs.getInt("quantity")
        ));
    }
}
