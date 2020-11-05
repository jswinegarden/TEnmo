package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

@Component
public class TransferSqlDAO implements TransferDAO {
	
	private JdbcTemplate jdbcTemplate;

    public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public List<Transfer> findOwnPastTransfers() {
		List<Transfer> pastTransfers = new ArrayList<>();
		
		String sql = ""; //need to join to transfer_type and transfer_status
		
		return pastTransfers;
	}

	@Override
	public Transfer findTransferById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createTransferTypeSend(Long fromUserId, Long toUserId, BigDecimal amount) {
		// TODO Auto-generated method stub
		return false;
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
