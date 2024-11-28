package CarRental.example.CarRentalLLD.dtos;

public class VehicleRequest {
    private String licensePlate;
    private String type;
    private String barcode;
    private Long branchId;

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

    public String getBarcode() {
        return barcode;
    }

    public Long getBranchId() {
        return branchId;
    }
}
