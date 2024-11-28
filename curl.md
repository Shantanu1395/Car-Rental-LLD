Adding branches
```cUrl
curl --location 'http://localhost:8080/api/branches' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Main Branch",
  "location": "Downtown",
  "address": "123 Main St"
}'
```

Add Vehicle
```cUrl
curl --location 'http://localhost:8080/api/vehicles' \
--header 'Content-Type: application/json' \
--data '{
  "licensePlate": "AB123CD",
  "type": "SUV",
  "branchId": 1,
  "barcode": "VEH12345"
}'
```

Get Vehicle
```cUrl
curl --location 'http://localhost:8080/api/vehicles/available?branchId=1&type=SUV' \
--header 'Content-Type: application/json'
```

Register User
```cUrl
curl --location 'http://localhost:8080/api/users/register' \
--header 'Content-Type: application/json' \
--data-raw '{
  "username": "john_doe",
  "email": "john.doe@example.com",
  "password": "securepassword",
  "contactDetails": "9876543210",
  "role": "USER"
}'
```

Login User
```cUrl
curl --location 'http://localhost:8080/api/users/login' \
--header 'Content-Type: application/json' \
--data '{
  "username": "john_doe",
  "password": "securepassword"
}'
```

Make booking
```cUrl
curl --location 'http://localhost:8080/api/bookings' \
--header 'Content-Type: application/json' \
--data '{
  "userId": 1,
  "vehicleId": 1,
  "startTime": "2024-12-01T10:00:00",
  "endTime": "2024-12-03T10:00:00",
  "addOnIds": []
}'
```

Modify Booking
```cUrl
curl --location --request PUT 'http://localhost:8080/api/bookings/1' \
--header 'Content-Type: application/json' \
--data '{
  "startTime": "2024-12-02T12:00:00",
  "endTime": "2024-12-04T12:00:00",
  "addOnIds": []
}'
```

Process Payment
```cUrl
curl --location 'http://localhost:8080/api/payments' \
--header 'Content-Type: application/json' \
--data '{
  "bookingId": 1,
  "amount": 200.00,
  "paymentMethod": "CREDIT_CARD"
}'
```

Create Booking Event
```cUrl
curl --location 'http://localhost:8080/api/booking-events/1' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'event=VEHICLE_PICKUP' \
--data-urlencode 'description=The vehicle was picked up by the customer'
```

Get Fees
```cUrl
curl --location 'http://localhost:8080/api/bookings/1/fees' \
--header 'Content-Type: application/json'
```

Delete vehicles
```cUrl
curl --location --request DELETE 'http://localhost:8080/api/vehicles/1' \
--header 'Content-Type: application/json'
```