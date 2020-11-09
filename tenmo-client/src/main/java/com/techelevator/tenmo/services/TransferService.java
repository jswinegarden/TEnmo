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

	public Transfer[] viewTransferHistory() throws TransferServiceException {
		Transfer[] transfers = null;
		try {
			transfers = restTemplate.exchange(BASE_URL + "accounts/"+ account.getAccountId() +"/transfers", 
					HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
		
			System.out.println("----------------------------------------------");
			System.out.println("Transfers\t From/To \t\t Amount");
			System.out.println("ID");
			System.out.println("----------------------------------------------");
			
			for(int i = 0; i < transfers.length; i++) {
				if(transfers[i].getAccountFrom()==account.getAccountId()){
					
					System.out.println(transfers[i].getTransferId()+"        \t To: "+
										transfers[i].getAccountTo()+ "        \t\t $"+transfers[i].getAmount());
				
				} else if (transfers[i].getAccountTo()==account.getAccountId()){
					System.out.println(transfers[i].getTransferId()+"        \t From: "+
										transfers[i].getAccountFrom()+"      \t\t $"+transfers[i].getAmount());
				}	
			}
			
			System.out.println("");
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transfers;
	
	}
	
	public Transfer viewTransferDetails(int choice) throws TransferServiceException {
		//use this in a try block
		Transfer transfer = null;
		try {
			transfer = restTemplate.exchange(BASE_URL + "transfers/"+ choice, HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		
		return transfer;
		
		
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
