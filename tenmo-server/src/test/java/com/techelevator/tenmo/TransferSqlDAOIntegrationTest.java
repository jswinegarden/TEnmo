package com.techelevator.tenmo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.tenmo.dao.TransferSqlDAO;
import com.techelevator.tenmo.model.Transfer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransferSqlDAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private TransferSqlDAO dao;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		
		dao = new TransferSqlDAO(dataSource);
		
		String sqlTruncate1 = "TRUNCATE transfers CASCADE";
		String sqlTruncate2 = "TRUNCATE transfer_types CASCADE";
		String sqlTruncate3 = "TRUNCATE transfer_statuses CASCADE";
		
		
		jdbcTemplate.update(sqlTruncate1);
		jdbcTemplate.update(sqlTruncate2);
		jdbcTemplate.update(sqlTruncate3);
	}
	
	@Test
	public void findOwnPastTransfers_returnAll() {
		//Arrange
		String sqlInsertType = "INSERT INTO transfer_types (transfer_type_id, transfer_type_desc) " +
						"VALUES (1, 'Test')";

		String sqlInsertStatus = "INSERT INTO transfer_statuses (transfer_status_id, transfer_status_desc) " +
						"VALUES (1, 'Test')";
				
		jdbcTemplate.update(sqlInsertType);
		jdbcTemplate.update(sqlInsertStatus);
				
		String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
						"VALUES (1, 1, 1, 1, 2, 10.00)";
				
		jdbcTemplate.update(sql);
		
		//Act
		List<Transfer> results = dao.findOwnPastTransfers((long)1);
		
		//Assert
		Assert.assertNotEquals(null, results);
		Assert.assertEquals(1, results.size());
	}
	
	@Test
	public void findTransferId_ValidId_InvalidId_ReturnOnlyValidId() {
		//Arrange
		
		String sqlInsertType = "INSERT INTO transfer_types (transfer_type_id, transfer_type_desc) " +
				"VALUES (1, 'Test')";

		String sqlInsertStatus = "INSERT INTO transfer_statuses (transfer_status_id, transfer_status_desc) " +
				"VALUES (1, 'Test')";
		
		jdbcTemplate.update(sqlInsertType);
		jdbcTemplate.update(sqlInsertStatus);
		
		String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
					"VALUES (1, 1, 1, 1, 2, 10.00)";
		
		jdbcTemplate.update(sql);
		
		//Act
		Transfer validId = dao.findTransferById((long)1);
		
		//Assert
		Assert.assertNotNull(validId);
	}
	
	//Need to complete
	public void sendTransfer_expectedResult_TBD() {
		// TODO Auto-generated method stub
	}
	
	@Test
	public void getAllTransfers_returnAll() {
		
		//Arrange
		String sqlInsertType = "INSERT INTO transfer_types (transfer_type_id, transfer_type_desc) " +
				"VALUES (1, 'Test')";

		String sqlInsertStatus = "INSERT INTO transfer_statuses (transfer_status_id, transfer_status_desc) " +
				"VALUES (1, 'Test')";
		
		jdbcTemplate.update(sqlInsertType);
		jdbcTemplate.update(sqlInsertStatus);
		
		String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
					"VALUES (1, 1, 1, 1, 2, 10.00)";
		
		jdbcTemplate.update(sql);
		
		//Act
		List<Transfer> results = dao.getAllTransfers();
		
		//Assert
		
		Assert.assertNotEquals(null, results);
		Assert.assertEquals(1, results.size());
	}

}
