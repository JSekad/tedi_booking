package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.PaymentMethod;
import di.uoa.tedi_booking.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService extends GenericService<PaymentMethod> {

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository){ super(paymentMethodRepository); }
}