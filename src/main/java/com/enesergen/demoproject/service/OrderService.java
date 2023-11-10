package com.enesergen.demoproject.service;

import com.enesergen.demoproject.model.Order;
import com.enesergen.demoproject.model.Product;
import com.enesergen.demoproject.model.User;
import com.enesergen.demoproject.repository.OrderRepository;
import com.enesergen.demoproject.repository.ProductRepository;
import com.enesergen.demoproject.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository=userRepository;
       }

    public Order createOrder() throws Exception {
        var userInfo=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=null;
        Order order;
        if(userInfo!=null){
            user=userRepository.findUserByUsername(userInfo).orElseThrow();
        }
        if(user!=null){
            order=new Order();
            order.setUserId(user.getObjId());
            order.setTotalPrice(0);
            order.setProductList(new HashSet<>());
            return orderRepository.save(order);
        }else{
            throw new Exception("User is null");
        }
    }

    public Set<Product> addProductToOrderList(String orderId, String productId){
        var order=orderRepository.findById(orderId);
        var product=productRepository.findById(productId);
        order.ifPresent((item)->{
            item.getProductList().add(product.orElseThrow());
            orderRepository.save(item);
        });
        return orderRepository.findById(orderId).orElseThrow().getProductList();
    }
    public Set<Product>removeProductToOrderList(String orderId,String productId){
        var order=orderRepository.findById(orderId);
        var product=productRepository.findById(productId);
        order.ifPresent((item)->{
            item.getProductList().remove(product.orElseThrow());
            orderRepository.save(item);
        });
        return orderRepository.findById(orderId).orElseThrow().getProductList();
    }

    public boolean deleteOrder(String orderId){
        var order=orderRepository.findById(orderId);
        order.ifPresent(orderRepository::delete);
        return !orderRepository.existsById(orderId);
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
    public Order getOrder(String orderId){
        return orderRepository.findById(orderId).orElseThrow();
    }
}
