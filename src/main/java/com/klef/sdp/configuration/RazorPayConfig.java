package com.klef.sdp.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "razorpay")
public class RazorPayConfig {

    private Key key = new Key();
    private String currency;
    private Payment payment = new Payment();

    public static class Key {
        private String id;
        private String secret;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getSecret() { return secret; }
        public void setSecret(String secret) { this.secret = secret; }
    }

    public static class Payment {
        private boolean capture;

        public boolean isCapture() { return capture; }
        public void setCapture(boolean capture) { this.capture = capture; }
    }

    // Getters & Setters
    public Key getKey() { return key; }
    public void setKey(Key key) { this.key = key; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }
}