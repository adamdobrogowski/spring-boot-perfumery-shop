package pl.perfumeria.perfumery.controller.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.perfumeria.perfumery.repository.OrderRepository;
import pl.perfumeria.perfumery.repository.PerfumeRepository;
import pl.perfumeria.perfumery.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardRestController {

    private final PerfumeRepository perfumeRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public DashboardRestController(PerfumeRepository perfumeRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.perfumeRepository = perfumeRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("productCount", perfumeRepository.count());
        stats.put("orderCount", orderRepository.count());
        stats.put("userCount", userRepository.count());
        return stats;
    }


}
