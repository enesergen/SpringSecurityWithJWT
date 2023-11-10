package com.enesergen.demoproject.controller;

import com.enesergen.demoproject.model.Order;
import com.enesergen.demoproject.model.Product;
import com.enesergen.demoproject.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/user/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/create-order")
    public ResponseEntity<Order> createOrder() throws Exception {
        return ResponseEntity.ok(orderService.createOrder());
    }

    @PostMapping("/add-product-to-order")
    public ResponseEntity<Set<Product>> addProductToOrderList(@RequestBody Map<String, String> json) {
        return ResponseEntity.ok(orderService.addProductToOrderList(json.get("orderId"), json.get("productId")));
    }

    @PostMapping("/delete-order")
    public ResponseEntity<Boolean> deleteOrder(@RequestBody Map<String, String> json) {
        return ResponseEntity.ok(orderService.deleteOrder(json.get("orderId")));
    }

    @PostMapping("/remove-prodtuct-from-order")
    public ResponseEntity<Set<Product>> removeProductFromOrder(@RequestBody Map<String, String> json) {
        return ResponseEntity.ok(orderService.removeProductToOrderList(json.get("orderId"), json.get("productId")));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Order>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @PostMapping("/get-one-order")
    public ResponseEntity<Order> getOneOrder(@RequestBody Map<String, String> json) {
        return ResponseEntity.ok(orderService.getOrder(json.get("orderId")));
    }


}
