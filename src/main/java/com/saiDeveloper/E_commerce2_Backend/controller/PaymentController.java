package com.saiDeveloper.E_commerce2_Backend.controller;



import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.saiDeveloper.E_commerce2_Backend.exception.OrderException;
import com.saiDeveloper.E_commerce2_Backend.model.Order;
import com.saiDeveloper.E_commerce2_Backend.repo.OrderRepo;
import com.saiDeveloper.E_commerce2_Backend.response.ApiResponse;
import com.saiDeveloper.E_commerce2_Backend.response.PaymentLinkResponse;
import com.saiDeveloper.E_commerce2_Backend.service.OrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Value("${razorpay.api.key}")
    private String apiKey;
    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepo orderRepo;

    @PostMapping("/payment/{orderId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @PathVariable("orderId") Long orderId
    ) throws OrderException, RazorpayException {
        Order order = orderService.findById(orderId);

        try{
            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", order.getTotalPrice()*100);
            paymentLinkRequest.put("currency", "INR");

            JSONObject customer = new JSONObject();
            customer.put("name",order.getUser().getFirstName()+" "+order.getUser().getLastName());
            customer.put("email",order.getUser().getEmail());

            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);

            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("customer",customer);

            //redirecting to payment success page
            paymentLinkRequest.put("callback_url","http://localhost:3000/payment/"+orderId);
            paymentLinkRequest.put("callback_method","get");

            //creating payment link
            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

            String paymentLinkId = payment.get("id");
            String paymentLinkUrl = payment.get("short_url");

            //creating response
            PaymentLinkResponse res = new PaymentLinkResponse(paymentLinkId,paymentLinkUrl);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
        catch (Exception e) {
            throw new RazorpayException(e.getMessage());
        }
    }


    @GetMapping("/payment")
    public ResponseEntity<ApiResponse> redirect(@RequestParam("paymentId") String paymentId,
                                                @RequestParam("orderId") Long orderId) throws OrderException, RazorpayException {
        Order order = orderService.findById(orderId);
        RazorpayClient razorpay = new RazorpayClient(apiKey,apiSecret);
        try{
           Payment payment = razorpay.payments.fetch(paymentId);
           if(payment.get("status").equals("captured")){
               order.getPaymentDetails().setPaymentId(paymentId);
               order.getPaymentDetails().setStatus("COMPLETED");
               order.setOrderStatus("PLACED");
               orderRepo.save(order);
           }

           ApiResponse res = new ApiResponse();
           res.setMessage("Payment successful, Order Got placed!");
           res.setStatus(true);
           return new ResponseEntity<>(res,HttpStatus.OK);
        }
        catch (Exception e){
            throw new RazorpayException(e.getMessage());



        }
    }

}
