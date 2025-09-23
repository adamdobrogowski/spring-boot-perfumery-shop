package pl.perfumeria.perfumery.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.perfumeria.perfumery.domain.*;
import pl.perfumeria.perfumery.dto.CartItemDto;
import pl.perfumeria.perfumery.repository.OrderRepository;
import pl.perfumeria.perfumery.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private CartService cartService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EmailService emailService;

    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void shouldPlaceOrderSuccessfully() {
        User user = new User();
        user.setEmail("test@user.com");
        user.setFirstName("Jan");

        Perfume perfume = new Perfume();
        perfume.setId(1L);
        perfume.setPrice(new BigDecimal("150.00"));

        CartItemDto cartItem = new CartItemDto(perfume, 2);

        when(cartService.getItems()).thenReturn(List.of(cartItem));
        when(cartService.getTotalPrice()).thenReturn(new BigDecimal("300.00"));
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        when(authentication.getName()).thenReturn("test@user.com");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order orderToSave = invocation.getArgument(0);
            orderToSave.setId(1L);
            return orderToSave;
        });

        orderService.placeOrder();

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(cartService, times(1)).clearCart();
        verify(emailService, times(1)).sendOrderConfirmationEmail(any(User.class), any(Order.class));
    }
}