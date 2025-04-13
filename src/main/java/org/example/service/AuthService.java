package org.example.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.exception.UnauthenticatedException;
import org.example.model.Session;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final SessionService sessionService;
    private final UserLoginService userLoginService;

    public String setSessionAndCookies(User user, HttpServletResponse response) {
        List<Session> sessionList = userLoginService.getSessionByUser(userLoginService.getUserByLogin(user.getLogin()));
        for (Session s : sessionList) {
            if (sessionService.isValidSession(s.getId())) {
                Cookie cookie = setCookie("SessionId", s.getId().toString(), 7200);
                response.addCookie(cookie);
                return "redirect:/";
            }
        }
        Session session = sessionService.createSession(userLoginService.getUserByLogin(user.getLogin()));
        Cookie cookie = setCookie("SessionId", session.getId().toString(), 7200);
        response.addCookie(cookie);
        return "redirect:/";
    }

    public User getUserFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String idSession = getIdSessionByCookie(cookies);
        if (idSession == null) throw new UnauthenticatedException();

        User user = sessionService.getUserBySession(UUID.fromString(idSession));
        if (user == null) throw new UnauthenticatedException();
        return user;
    }

    //Поменять название метода
    public void logoutDeleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String idSession = getIdSessionByCookie(cookies);
        sessionService.deleteSession(UUID.fromString(idSession));
        Cookie cookie = setCookie("SessionId", "", 0);
        response.addCookie(cookie);
    }

    public Cookie setCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public String getIdSessionByCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SessionId".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
