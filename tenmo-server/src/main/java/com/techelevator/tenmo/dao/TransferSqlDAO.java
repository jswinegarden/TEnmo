package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.LoginDTO;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

@Component
public class TransferSqlDAO implements TransferDAO {
	
//	private int currenUserId;
//	private UserDAO userDAO;
//	private LoginDTO loginDto;
	
	private JdbcTemplate jdbcTemplate;

	public TransferSqlDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Transfer> viewOwnPastTransfers(Long accountId) {
		
		List<Transfer> pastTransfers = new ArrayList<>();
		
		String sql = "SELECT t.transfer_id, t.transfer_type_id, tt.transfer_type_desc, t.transfer_status_id, ts.transfer_status_desc, " +
					"t.account_from, t.account_to, t.amount " +
					"FROM transfers t " +
					"INNER JOIN transfer_types tt ON tt.transfer_type_id = t.transfer_type_id " +
					"INNER JOIN transfer_statuses ts ON ts.transfer_status_id = t.transfer_status_id " +
					"INNER JOIN accounts a ON a.account_id = t.account_from OR a.account_id = t.account_to " +
					"AND a.account_id = ?"; 
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
		while(results.next()) {
			Transfer transfer = mapRowToTransfer(results);
			pastTransfers.add(transfer);
		}
		
		return pastTransfers;
	}

	@Override
	public Transfer viewTransferById(Long id) {
		for (Transfer transfer : this.getAllTransfers()) {
			if (transfer.getTransferId() == id) {
				return transfer;
			}
		}
		return null;
	}

	@Override
	public void sendTransfer(Long fromUserId, Long toUserId, BigDecimal amount) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public List<Transfer> getAllTransfers(){
		List<Transfer> allTransfers = new ArrayList<>();
		
		String sql = "SELECT t.transfer_id, t.transfer_type_id, tt.transfer_type_desc, t.transfer_status_id, ts.transfer_status_desc, " +
				"t.account_from, t.account_to, t.amount " +
				"FROM transfers t " +
				"INNER JOIN transfer_types tt ON tt.transfer_type_id = t.transfer_type_id " +
				"INNER JOIN transfer_statuses ts ON ts.transfer_status_id = t.transfer_status_id ";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			Transfer transfer = mapRowToTransfer(results);
			allTransfers.add(transfer);
		}
		
		return allTransfers;
	}
		
	
	private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getLong("transfer_id"));
        transfer.setTransferTypeId(rs.getLong("transfer_type_id"));
        transfer.setTransferType(rs.getString("transfer_type_desc"));
        transfer.setTransferStatusId(rs.getLong("transfer_status_id"));
        transfer.setTransferStatus(rs.getString("transfer_status_desc"));
        transfer.setAccountFrom(rs.getLong("account_from"));
        transfer.setAccountTo(rs.getLong("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        
        return transfer;
    }


}