package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Transfer;

public class TransferService {
	
	public static String AUTH_TOKEN = ""; 
	private final String BASE_URL;
	private final RestTemplate restTemplate = new RestTemplate();
	
	public TransferService(String url) {
		BASE_URL = url;
	}
	
	public Transfer[] viewTransferHistory(Long accountId) throws TransferServiceException {
		Transfer[] transfers = null;
		try {
			transfers = restTemplate.getForObject(BASE_URL + "accounts/"+ accountId +"/transfers", Transfer[].class);
//			transfers = restTemplate.exchange(BASE_URL + "accounts/"+ accountId +"/transfers", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transfers;
	}
	
	public Transfer sendBucks(String newTransfer) throws TransferServiceException {
		Transfer transfer = makeTransfer(newTransfer);
		try {
			transfer = restTemplate.postForObject(BASE_URL + "transfers", makeTransferEntity(transfer), Transfer.class);
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transfer;
	}
	
	private Transfer makeTransfer(String CSV) {
		
		String[] parsed = CSV.split(",");
		BigDecimal transferAmount = new BigDecimal(parsed[7]);
		
		if(parsed.length < 7 || parsed.length > 8) {
			return null;
		}
		
		if (parsed.length == 7) {
			String[] withId = new String[9];
			Transfer[] transfers = new Transfer[0];
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
