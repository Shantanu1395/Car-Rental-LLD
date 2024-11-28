package CarRental.example.CarRentalLLD.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class BookingRequest {
    private Long userId;
    private Long vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> addOnIds;

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<Long> getAddOnIds() {
        return addOnIds;
    }
}
