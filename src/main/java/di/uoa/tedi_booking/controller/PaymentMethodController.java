package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.PaymentMethod;
import di.uoa.tedi_booking.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/paymentMethod")
public class PaymentMethodController extends GenericController<PaymentMethod>{

    private final PaymentMethodService paymentMethodService;
    @Autowired
    public PaymentMethodController(PaymentMethodService service){
        super(service);
        this.paymentMethodService = service;
    }
}
