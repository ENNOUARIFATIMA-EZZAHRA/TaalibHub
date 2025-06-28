package com.taliibHub.backend.dto;

public class AuthRequest {
    private String email;
    private String password;
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    // For backward compatibility
    public String getMotDePass() { return password; }
    public void setMotDePass(String motDePass) { this.password = motDePass; }
}
