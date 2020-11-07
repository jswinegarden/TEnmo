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
					"WHERE t.account_from IN (SELECT account_id FROM accounts WHERE account_id = ?) " +
					"OR t.account_to IN (SELECT account_id FROM accounts WHERE account_id = ?)";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId, accountId);
		while(results.next()) {
			Transfer transfer = mapRowToTransfer(results);
			pastTransfers.add(transfer);
		}
		
		return pastTransfers;
	}

	@Override
	public Transfer viewTransferById(Long transferId) {
		
		for (Transfer transfer : this.getAllTransfers()) {
			if (transfer.getTransferId() == transferId) {
				return transfer;
			}
		}
		return null;
		
//		for (Transfer transfer : this.getAllTransfers()) {
//			if (transfer.getTransferId() == transferId &&
//					(transfer.getAccountFrom() == accountId || transfer.getAccountTo() == accountId)) {
//				return transfer;
//			}
//		}
//		return null;
	}

	@Override
	public boolean sendTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
		
		String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
					"VALUES (DEFAULT, (SELECT transfer_status_id FROM transfer_statuses WHERE transfer_status_desc = 'Approved'), " +
							"(SELECT transfer_type_id FROM transfer_types WHERE transfer_type_desc = 'Send'), " +
							"? , ? , ?) ";
		
		jdbcTemplate.update(sql, fromAccountId, toAccountId, amount);
		
		return true;
	
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
