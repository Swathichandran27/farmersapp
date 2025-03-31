package com.springboot.farmersapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.farmersapp.Entity.Delivery;
import com.springboot.farmersapp.Repository.DeliveryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    public List<Delivery> getDeliveriesByStatus(String status) {
        return deliveryRepository.findByStatus(status);
    }

    public List<Delivery> getDeliveriesByOrderId(Long orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery updateDelivery(Long id, Delivery deliveryDetails) {
        return deliveryRepository.findById(id).map(delivery -> {
            delivery.setOrderId(deliveryDetails.getOrderId());
            delivery.setStatus(deliveryDetails.getStatus());
            delivery.setEstimatedDeliveryTime(deliveryDetails.getEstimatedDeliveryTime());
            delivery.setActualDeliveryTime(deliveryDetails.getActualDeliveryTime());
            return deliveryRepository.save(delivery);
        }).orElseThrow(() -> new RuntimeException("Delivery not found"));
    }

    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
