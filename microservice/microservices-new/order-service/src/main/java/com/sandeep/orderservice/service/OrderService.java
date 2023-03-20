package com.sandeep.orderservice.service;

import com.sandeep.orderservice.dto.InventoryResponse;
import com.sandeep.orderservice.dto.OrderLineItemsDto;
import com.sandeep.orderservice.dto.OrderRequest;
import com.sandeep.orderservice.model.Order;
import com.sandeep.orderservice.model.OrderLineItems;
import com.sandeep.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToDTO(orderLineItemsDto))
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList();

        //call inventory service and check for inventory
        //its a syncronous request using webclient
        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean result = Arrays.stream(inventoryResponseArray).allMatch(inventoryResponse -> inventoryResponse.isInInStock());

        if(result){
            orderRepository.save(order);
            System.out.println("Order placed.");
        } else {
            throw new IllegalArgumentException("product not in stock...!!!");
        }


    }

    private OrderLineItems mapToDTO(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }
}
