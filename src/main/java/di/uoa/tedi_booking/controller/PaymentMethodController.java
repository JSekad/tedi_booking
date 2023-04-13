package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.PaymentMethod;
import di.uoa.tedi_booking.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/paymentMethod")
public class PaymentMethodController extends GenericController<PaymentMethod>{
    @Autowired
    public PaymentMethodController(PaymentMethodRepository paymentMethodRepository ){super(paymentMethodRepository);}
}
