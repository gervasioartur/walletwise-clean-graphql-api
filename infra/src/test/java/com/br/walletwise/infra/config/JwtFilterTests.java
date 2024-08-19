package com.br.walletwise.infra.config;

import com.br.walletwise.infra.helper.GetUsernameFromToken;
import com.br.walletwise.infra.helper.ValidateToken;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.br.walletwise.infra.service.user.LoadUserByUsernameGatewayImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
public class JwtFilterTests {
    @Autowired
    private JwtFilter jwtFilter;
    @MockBean
    private LoadUserByUsernameGatewayImpl loadUserByUsername;
    @MockBean
    private GetUsernameFromToken getUsernameFromToken;
    @MockBean
    private ValidateToken validateToken;
    @MockBean
    private HttpServletRequest request;
    @MockBean
    private HttpServletResponse response;
    @MockBean
    private FilterChain filterChain;

    @Test
    @DisplayName("Should not authenticate if authorization header is null")
    void shouldNotAuthenticateIfAuthorizationHeaderIsNull() throws ServletException, IOException {
        Mockito.when(request.getHeader("Authorization")).thenReturn(null);

        jwtFilter.doFilterInternal(request, response, filterChain);

        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(this.filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("Should not authenticate if authorization header is empty")
    void shouldNotAuthenticateIfAuthorizationHeaderIsEmpty() throws ServletException, IOException {
        Mockito.when(request.getHeader("Authorization")).thenReturn("");

        jwtFilter.doFilterInternal(request, response, filterChain);

        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(this.filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("Should not authenticate if authorization header is not Bearer")
    void shouldNotAuthenticateIfAuthorizationHeaderIsNotBearer() throws ServletException, IOException {
        String authHeader = "NotBearer " + UUID.randomUUID();

        Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);

        jwtFilter.doFilterInternal(request, response, filterChain);

        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(this.filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("Should not authenticate if cant extract username on token")
    void shouldNotAuthenticateIfCantExtractUsernameOnToken() throws ServletException, IOException {
        String authHeader = "Bearer " + UUID.randomUUID();

        Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);

        jwtFilter.doFilterInternal(request, response, filterChain);

        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(this.filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("Should not authenticate if security context if not null")
    void shouldNotAuthenticateIfSecurityContextIsNull() throws ServletException, IOException {
        String authHeader = "Bearer " + UUID.randomUUID();
        String token = authHeader.substring(7);
        String username = MocksFactory.faker.name().username();

        Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);
        Mockito.when(this.getUsernameFromToken.get(token)).thenReturn(username);

        try (MockedStatic<SecurityContextHolder> securityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication())
                    .thenReturn(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()));

            jwtFilter.doFilterInternal(request, response, filterChain);

            Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
            verify(this.filterChain, times(1)).doFilter(request, response);
        }
    }

    @Test
    @DisplayName("Should not authenticate if user does not exist by username")
    void shouldNotAuthenticateIfUserDoesNotExistByUsername() throws ServletException, IOException {
        String authHeader = "Bearer " + UUID.randomUUID();
        String token = authHeader.substring(7);
        String username = MocksFactory.faker.name().username();

        Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);
        Mockito.when(this.getUsernameFromToken.get(token)).thenReturn(username);

        try (MockedStatic<SecurityContextHolder> securityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(null);
            doThrow(UsernameNotFoundException.class).when(this.loadUserByUsername).loadUserByUsername(username);

            Throwable exception = Assertions
                    .catchThrowable(() -> jwtFilter.doFilterInternal(request, response, filterChain));

            Assertions.assertThat(exception).isInstanceOf(UsernameNotFoundException.class);
            Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
            verify(this.loadUserByUsername, times(1)).loadUserByUsername(username);
            verify(this.filterChain, times(0)).doFilter(request, response);
        }
    }

    @Test
    @DisplayName("Should not authenticate if token is invalid")
    void shouldNotAuthenticateIfTokenIsInvalid() throws ServletException, IOException {
        String authHeader = "Bearer " + UUID.randomUUID();
        String token = authHeader.substring(7);
        String username = MocksFactory.faker.name().username();
        UserJpaEntity userDetails = MocksFactory.userJpaEntityFactory();

        Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);
        Mockito.when(this.getUsernameFromToken.get(token)).thenReturn(username);

        try (MockedStatic<SecurityContextHolder> securityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(null);
            when(this.loadUserByUsername.loadUserByUsername(username)).thenReturn(userDetails);
            when(this.validateToken.isValid(token, userDetails)).thenReturn(false);

            jwtFilter.doFilterInternal(request, response, filterChain);

            Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
            verify(this.loadUserByUsername, times(1)).loadUserByUsername(username);
            verify(this.filterChain, times(1)).doFilter(request, response);
        }
    }

    @Test
    @DisplayName("Should authenticate")
    void shouldAuthenticate() throws ServletException, IOException {
        String authHeader = "Bearer " + UUID.randomUUID();
        String token = authHeader.substring(7);
        String username = MocksFactory.faker.name().username();
        UserJpaEntity userDetails = MocksFactory.userJpaEntityFactory();

        Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);
        Mockito.when(this.getUsernameFromToken.get(token)).thenReturn(username);

        try (MockedStatic<SecurityContextHolder> securityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(null);
            when(this.loadUserByUsername.loadUserByUsername(username)).thenReturn(userDetails);
            when(this.validateToken.isValid(token, userDetails)).thenReturn(true);
            securityContextHolder.when(SecurityContextHolder::createEmptyContext).thenReturn(securityContext);
            doNothing().when(securityContext).setAuthentication(any());

            jwtFilter.doFilterInternal(request, response, filterChain);

            Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
            verify(this.loadUserByUsername, times(1)).loadUserByUsername(username);
            verify(this.filterChain, times(1)).doFilter(request, response);
        }
    }
}