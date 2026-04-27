package com.klef.sdp.controller;

import com.klef.sdp.configuration.RazorPayConfig;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payments")
public class RazorPayController {

	@Value("${razorpay.key.id}")
	public String keyId;

    @Autowired
    private RazorPayConfig razorpayConfig;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {

        int amount = ((Number) data.get("amount")).intValue();

        RazorpayClient client = new RazorpayClient(
                razorpayConfig.getKey().getId(),
                razorpayConfig.getKey().getSecret()
        );

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount);
        orderRequest.put("currency", razorpayConfig.getCurrency());
        orderRequest.put("payment_capture", razorpayConfig.getPayment().isCapture() ? 1 : 0);

        Order order = client.orders.create(orderRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.get("id"));
        response.put("amount", amount);
        response.put("currency", razorpayConfig.getCurrency());
        response.put("keyId", keyId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-payment")
    public ResponseEntity<Map<String, String>> verifyPayment(@RequestBody Map<String, String> payload) {

        String orderId = payload.get("razorpay_order_id");
        String paymentId = payload.get("razorpay_payment_id");
        String signature = payload.get("razorpay_signature");

        Map<String, String> response = new HashMap<>();

        try {
            String generatedSignature = Utils.getHash(
                    orderId + "|" + paymentId,
                    razorpayConfig.getKey().getSecret()
            );

            if (generatedSignature.equals(signature)) {
                response.put("status", "success");
            } else {
                response.put("status", "failure");
            }
        } catch (Exception e) {
            response.put("status", "error");
        }

        return ResponseEntity.ok(response);
    }
}