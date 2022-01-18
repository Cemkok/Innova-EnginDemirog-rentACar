package com.btkAkademi.rentACar.business.requests.invoiceRequests;

import java.time.LocalDate;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import lombok.Data;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	private int rentalId;
	private LocalDate creationDate;
}