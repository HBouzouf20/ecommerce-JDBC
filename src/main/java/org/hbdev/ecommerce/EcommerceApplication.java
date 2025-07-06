package org.hbdev.ecommerce;

import net.datafaker.Faker;
import org.hbdev.ecommerce.enums.Gender;
import org.hbdev.ecommerce.models.Client;
import org.hbdev.ecommerce.models.Order;
import org.hbdev.ecommerce.models.Product;
import org.hbdev.ecommerce.models.Provider;
import org.hbdev.ecommerce.services.*;

import java.util.Random;

public class EcommerceApplication {

    public static void main(String[] args) {

        OrderService orderService = new OrderServiceImpl();
        ClientService clientService = new ClientServiceImpl();
        ProductService productService = new ProductServiceImpl();

        Order order = new Order();
        order.setClient(clientService.getById(81));
        order.setProduct(productService.getById(1));
        order.setQuantity(new Random().nextInt(10));
        orderService.create(order);

        orderService.getAll().forEach(System.out::println);
    }

}
