package com.taliibHub.backend.dto;

import com.taliibHub.backend.model.Utilisateur;

public class AuthResponse {
    private String token;
    private Utilisateur user;
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public Utilisateur getUser() { return user; }
    public void setUser(Utilisateur user) { this.user = user; }
}
