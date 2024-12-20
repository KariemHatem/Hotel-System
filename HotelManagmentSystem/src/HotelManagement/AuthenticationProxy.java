/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelManagement;

/**
 *
 * @author Kariem Hatem
 */

// Proxy Pattern

public class AuthenticationProxy implements AuthenticationService {
    private RealAuthenticationService realService;

    public AuthenticationProxy() {
        this.realService = new RealAuthenticationService();
    }

    @Override
    public boolean authenticate(String username, String password) {
        // Add logging or access control
        System.out.println("Authentication attempt for user: " + username);
        if (username == null || password == null) {
            System.out.println("Invalid credentials provided.");
            return false;
        }
        return realService.authenticate(username, password);
    } 
}
