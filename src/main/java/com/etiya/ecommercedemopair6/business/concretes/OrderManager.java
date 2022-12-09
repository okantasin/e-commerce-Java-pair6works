package com.etiya.ecommercedemopair6.business.concretes;

import com.etiya.ecommercedemopair6.business.abstracts.CustomerService;
import com.etiya.ecommercedemopair6.business.abstracts.OrderService;
import com.etiya.ecommercedemopair6.business.dto.request.concretes.order.CreateOrderRequest;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.order.CreateOrderResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.order.GetAllOrderResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.order.GetOrderResponse;
import com.etiya.ecommercedemopair6.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair6.entities.concretes.Order;
import com.etiya.ecommercedemopair6.repository.abstracts.OrderRepository;
import com.etiya.ecommercedemopair6.repository.abstracts.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderManager implements OrderService {
    OrderRepository orderRepository;
    CustomerService customerService;
    ModelMapperService modelMapperService;
    private final PaymentRepository paymentRepository;

    @Override
    public GetOrderResponse getById(int id) {
        Order order = orderRepository.findById(id).orElseThrow();
        GetOrderResponse response = modelMapperService.forResponse().map(order,GetOrderResponse.class);
        return response;


    }

    @Override
    public List<GetAllOrderResponse> getAllOrders() {
       List<Order> orders = orderRepository.findAll();
       List<GetAllOrderResponse> responses = orders.stream().map(order -> modelMapperService.forResponse().map(order, GetAllOrderResponse.class)).collect(Collectors.toList());
       return responses;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        Order order =modelMapperService.forRequest().map(createOrderRequest,Order.class);

        Order savedOrder = orderRepository.save(order);
        CreateOrderResponse response = modelMapperService.forResponse().map(savedOrder,CreateOrderResponse.class);
//***********************************ManuelMapper******************************************
//        Customer customer = customerService.getById(createOrderRequest.getCustomerId());
//        Order order = new Order();
//        order.setOrderDate(createOrderRequest.getOrderDate());
//        order.setOrderNumber(createOrderRequest.getOrderNumber());
//        order.setOrderQuantity(createOrderRequest.getOrderQuantity());
//        order.setTotalPrice(createOrderRequest.getTotalPrice());
//        order.setOrderNumber(createOrderRequest.getOrderNumber());
//        order.setCustomer(customer);
//        Order savedOrder = orderRepository.save(order);
//        CreateOrderResponse response = new CreateOrderResponse(savedOrder.getOrderNumber(),
//                savedOrder.getOrderQuantity(), savedOrder.getTotalPrice() , savedOrder.getCustomer().getCustomerId());
        return response;

    }
}