package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.Transfer;

public class TransferService {
	
	public static String AUTH_TOKEN = ""; 
	private final String BASE_URL;
	private final RestTemplate restTemplate = new RestTemplate();
	
	Account account;
	
	public TransferService(String url) {
		BASE_URL = url;
	}

<<<<<<< HEAD
	public Transfer[] viewTransferHistory() throws TransferServiceException {
=======
	public boolean viewTransferHistory(Long accountId) throws TransferServiceException {
		boolean hasHistory = false;
		
>>>>>>> 950716b8d888ecae464697d3fdcae5bd5cbc94cc
		Transfer[] transfers = null;
		try {
			transfers = restTemplate.exchange(BASE_URL + "accounts/"+ account.getAccountId() +"/transfers", 
					HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
		
			if (transfers.length == 0 || transfers == null) {
				return hasHistory;
			
<<<<<<< HEAD
			for(int i = 0; i < transfers.length; i++) {
				if(transfers[i].getAccountFrom()==account.getAccountId()){
=======
			} else {
				hasHistory = true;
				
				System.out.println("----------------------------------------------");
				String heading1 = "Transfers ID";
				String heading2 = "From/To";
				String heading3 = "Amount";
				System.out.printf( "%-15s %10s %15s %n", heading1, heading2, heading3);
				System.out.println("----------------------------------------------");
				
				for(int i = 0; i < transfers.length; i++) {
					if(transfers[i].getAccountFrom() == accountId){
						System.out.printf("%-15s %10s %15s %n",transfers[i].getTransferId(), "To: "+transfers[i].getToUsername(), "$"+transfers[i].getAmount());
>>>>>>> 950716b8d888ecae464697d3fdcae5bd5cbc94cc
					
					} else if (transfers[i].getAccountTo() == accountId){
						System.out.printf("%-15s %10s %15s %n",transfers[i].getTransferId(), "From: "+transfers[i].getFromUsername(), "$"+transfers[i].getAmount());
					}
					
				}
				System.out.println("---------");
				return hasHistory;
				
<<<<<<< HEAD
				} else if (transfers[i].getAccountTo()==account.getAccountId()){
					System.out.println(transfers[i].getTransferId()+"        \t From: "+
										transfers[i].getAccountFrom()+"      \t\t $"+transfers[i].getAmount());
				}	
=======
>>>>>>> 950716b8d888ecae464697d3fdcae5bd5cbc94cc
			}
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transfers;
	
	}
	
	public Transfer viewTransferDetails(Long accountId, Long transferId) throws TransferServiceException {
		
		Transfer transfer = null;
		try {
			transfer = restTemplate.exchange(BASE_URL + "transfers/"+ transferId, HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
			if (transfer != null && (transfer.getAccountFrom() == accountId || transfer.getAccountTo() == accountId)) {
				return transfer;
			} else {
				return null;
			}
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		
	}
	
	public Transfer sendBucks(String newTransfer) throws TransferServiceException {
		Transfer transfer = makeTransfer(newTransfer);
		try {
			transfer = restTemplate.postForObject(BASE_URL + "accounts/" + transfer.getAccountTo() +  "/transfers", makeTransferEntity(transfer), Transfer.class);
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transfer;
	}
	
	private Transfer makeTransfer(String CSV) {
		
		String[] parsed = CSV.split(",");
		BigDecimal transferAmount = new BigDecimal(parsed.length - 1);
		
		if(parsed.length < 7 || parsed.length > 8) {
			return null;
		}
		
		if (parsed.length == 7) {
			String[] withId = new String[9];
			Transfer[] transfers = new Transfer[7];
			try {
				transfers = viewTransferHistory();
			} catch (TransferServiceException e) {
				e.printStackTrace();
			}
			if (transfers == null) {
				return null;
			}
			String[] idArray = new String[] { transfers.length + 1 + ""};
			System.arraycopy(idArray, 0, withId, 0, 1);
			System.arraycopy(parsed, 0, withId, 1, 5);
			parsed = withId;
			
		}
		return new Transfer(Long.parseLong(parsed[0].trim()), Long.parseLong(parsed[1].trim()), parsed[2].trim(), Long.parseLong(parsed[3].trim()), parsed[4].trim(), Long.parseLong(parsed[5].trim()), Long.parseLong(parsed[6].trim()), transferAmount);
	}
	
	private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
		return entity;
	}
	
	private HttpEntity makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}
	
}
