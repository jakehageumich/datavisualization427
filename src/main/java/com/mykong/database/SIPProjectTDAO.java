package com.mykong.database;

import java.sql.Connection;
import com.mykong.model.*;
import com.mykong.database.*;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SIPProjectTDAO {

	public ArrayList<SIPProjectT> getAll() throws ClassNotFoundException, SQLException {

		ArrayList<SIPProjectT> ls = new ArrayList<SIPProjectT>();

		Connection con = SIPProjectTDAOUtils.getStoredConnection();

		String sql = "SELECT * from SIP_PROJECT_T";
		
		/*
		 * "SELECT FROM SIP_PROJECT_T " + "id=?,tenant=?,resource_uri=?,owner_id=?,"
				+ "owner_email=?,publish_status=?,major_version=?,minor_version=?,patch_version=?,total_bytes=?,"
				+ "active_ind=?,created_ts=?,updated_ts=?,disp_name=?,ref_old_id=?" + " WHERE id = " + idTemp;
		 * 
		 * */

		PreparedStatement ps = SIPProjectTDAOUtils.getPreparedStatement(con, sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			SIPProjectT entry = new SIPProjectT();
			
			entry.setId(rs.getInt(1));
			entry.setTenant(rs.getString(2));
			entry.setResource_uri(rs.getString(3));
			entry.setOwner_id(rs.getInt(4));
			entry.setOwner_email(rs.getString(5));
			entry.setStatus(rs.getString(6));
			entry.setMajor_version(rs.getInt(7));
			entry.setMinor_version(rs.getInt(8));
			entry.setPatch_version(rs.getInt(9));
			entry.setTotal_bytes(rs.getInt(10));
			entry.setActive_ind(rs.getString(11));
			entry.setCreated_ts(rs.getString(12));
			entry.setUpdated_ts(rs.getString(13));
			entry.setDisp_name(rs.getString(14));
			entry.setRef_old_id(rs.getInt(15));
			
			ls.add(entry);
		}
		return ls;
	}

	public void addNew(SIPProjectT p) throws ClassNotFoundException, SQLException {

		if (SIPProjectTDAO.getById(p.getId()) == null) {
			// Query insert to table product with 4 values
			String query = "INSERT INTO SIP_PROJECT_T VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// call prepare statement
			PreparedStatement ps = SIPProjectTDAOUtils.getPreparedStatement(SIPProjectTDAOUtils.getStoredConnection(),
					query);
			ps.setInt(1, p.getId());
			ps.setInt(4, p.getOwner_id());
			ps.setInt(7, p.getMajor_version());
			ps.setInt(8, p.getMinor_version());
			ps.setInt(9, p.getPatch_version());
			ps.setInt(10, p.getTotal_bytes());
			ps.setInt(15, p.getRef_old_id());
			ps.setString(2, p.getTenant());
			ps.setString(3, p.getResource_uri());
			ps.setString(5, p.getOwner_email());
			ps.setString(6, p.getStatus());
			ps.setString(11, p.getActive_ind());
			ps.setString(12, p.getCreated_ts());
			ps.setString(13, p.getUpdated_ts());
			ps.setString(14, p.getDisp_name());

			ps.executeUpdate();
		}
	}

	public static SIPProjectT getById(int idTemp) throws ClassNotFoundException, SQLException {

		String sql = "SELECT FROM SIP_PROJECT_T " + "id=?,tenant=?,resource_uri=?,owner_id=?,"
				+ "owner_email=?,publish_status=?,major_version=?,minor_version=?,patch_version=?,total_bytes=?,"
				+ "active_ind=?,created_ts=?,updated_ts=?,disp_name=?,ref_old_id=?" + " WHERE id = " + idTemp;
		
		SIPProjectT entry = null;
		ResultSet rs = SIPProjectTDAOUtils.getPreparedStatement(SIPProjectTDAOUtils.getStoredConnection(), sql)
				.executeQuery();
		while (rs.next()) {
			
			entry = new SIPProjectT();
			entry.setId(rs.getInt(1));
			entry.setTenant(rs.getString(2));
			entry.setResource_uri(rs.getString(3));
			entry.setOwner_id(rs.getInt(4));
			entry.setOwner_email(rs.getString(5));
			entry.setStatus(rs.getString(6));
			entry.setMajor_version(rs.getInt(7));
			entry.setMinor_version(rs.getInt(8));
			entry.setPatch_version(rs.getInt(9));
			entry.setTotal_bytes(rs.getInt(10));
			entry.setActive_ind(rs.getString(11));
			entry.setCreated_ts(rs.getString(12));
			entry.setUpdated_ts(rs.getString(13));
			entry.setDisp_name(rs.getString(14));
			entry.setRef_old_id(rs.getInt(15));
			
		}
		return entry;
	}

	public void edit(int id, int owner_id, int major_version, int minor_version, int patch_version, int total_bytes,
			int ref_old_id, String tenant, String resource_uri, String owner_email, String publish_status,
			String active_ind, String created_ts, String updated_ts, String disp_name) throws ClassNotFoundException {

		String sql = "Update SIP_PROJECT_T " + "Set id=?,tenant=?,resource_uri=?,owner_id=?,"
				+ "owner_email=?,publish_status=?,major_version=?,minor_version=?,patch_version=?,total_bytes=?,"
				+ "active_ind=?,created_ts=?,updated_ts=?,disp_name=?,ref_old_id=?" + "where id = " + id;
		try {

			// Create prepare statement
			PreparedStatement ps = SIPProjectTDAOUtils.getPreparedStatement(SIPProjectTDAOUtils.getStoredConnection(),
					sql);
			ps.setInt(1, id);
			ps.setString(2, tenant);
			ps.setString(3, resource_uri);
			ps.setInt(4, owner_id);
			ps.setString(5, owner_email);
			ps.setString(6, publish_status);
			ps.setInt(7, major_version);
			ps.setInt(8, minor_version);
			ps.setInt(9, patch_version);
			ps.setInt(10, total_bytes);
			ps.setString(11, active_ind);
			ps.setString(12, created_ts);
			ps.setString(13, updated_ts);
			ps.setString(14, disp_name);
			ps.setInt(15, ref_old_id);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try {
			String sql = "Delete SIP_PROJECT_T where id =?";
			// Create prepare statement
			PreparedStatement ps = SIPProjectTDAOUtils.getPreparedStatement(SIPProjectTDAOUtils.getStoredConnection(),
					sql);

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
