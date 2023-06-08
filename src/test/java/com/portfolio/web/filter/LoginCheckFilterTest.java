package com.portfolio.web.filter;



import com.portfolio.config.SessionConst;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LoginCheckFilterTest {

    private LoginCheckFilter filter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private FilterChain chain;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        filter = new LoginCheckFilter();
    }

    @Test
    public void doFilter_shouldRedirectToLoginWhenNotAuthenticated() throws ServletException, IOException {
        // Arrange
        when(request.getRequestURI()).thenReturn("/secure-page");
        when(request.getSession(false)).thenReturn(null);

        // Act
        filter.doFilter(request, response, chain);

        // Assert
        verify(response).sendRedirect("/login?redirectURL=/secure-page");
        verify(chain, never()).doFilter(request, response);
    }

    @Test
    public void doFilter_shouldCallChainWhenAuthenticated() throws ServletException, IOException {
        // Arrange
        when(request.getRequestURI()).thenReturn("/public-page");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(SessionConst.LOGIN_MEMBER)).thenReturn(new Object());

        // Act
        filter.doFilter(request, response, chain);

        // Assert
        verify(response, never()).sendRedirect(anyString());
        verify(chain).doFilter(request, response);
    }
}
