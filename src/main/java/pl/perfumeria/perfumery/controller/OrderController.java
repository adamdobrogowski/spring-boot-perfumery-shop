package pl.perfumeria.perfumery.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.perfumeria.perfumery.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/place")
    public String placeOrder() {
        orderService.placeOrder();
        return "redirect:/order/success";
    }

    @GetMapping("/order/success")
    public String orderSuccess() {
        return "order-success";
    }

}
