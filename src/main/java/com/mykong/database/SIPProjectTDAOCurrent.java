package com.mykong.database;

import java.sql.Connection;


import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.mykong.database.SIPProjectTDAO;
import com.mykong.database.SIPProjectTDAOUtils;
import com.mykong.model.*;

public class SIPProjectTDAOCurrent {
		
	public static ArrayList<SIPProjectTCurrent> getAll() throws ClassNotFoundException, SQLException {

		ArrayList<SIPProjectTCurrent> ls = new ArrayList<SIPProjectTCurrent>();

		Connection con = SIPProjectTDAOUtils.getStoredConnection();

		String sql = "SELECT  tenbystat.tenant, tenbystat.status, count(proj.id) "
				+ "FROM    ( select  ten.tenant as tenant, stat.status as status "
				+ "FROM    ( select distinct tenant from sys_tenant_type_t ) ten,( select distinct status from sys_status_type_t ) stat) tenbystat "
				+ "left join " + "sip_project_t proj on proj.tenant = tenbystat.tenant and "
				+ "proj.status = tenbystat.status, " + "sys_tenant_type_t tentype, " + "sys_status_type_t stattype "
				+ "WHERE tenbystat.tenant = tentype.tenant  and  tenbystat.status = stattype.status "
				+ "GROUP BY tenbystat.tenant, tenbystat.status, tentype.rank, stattype.rank "
				+ "ORDER BY tentype.rank, stattype.rank";

		PreparedStatement ps = SIPProjectTDAOUtils.getPreparedStatement(con, sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			SIPProjectTCurrent spc = new SIPProjectTCurrent();
			spc.setTenant(rs.getString(1));
			spc.setStatus(rs.getString(2));
			spc.setCount(rs.getInt(3));
			ls.add(spc);
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

	public static SIPProjectTCurrent getByTenant(String tenantTemp) throws ClassNotFoundException, SQLException {
	
		ArrayList<SIPProjectTCurrent> all = SIPProjectTDAOCurrent.getAll();
		SIPProjectTCurrent tenant = new SIPProjectTCurrent();
		for(int i = 0; i < all.size(); i++){
			if(all.get(i).getTenant().equals(tenantTemp)){
				tenant = all.get(i);
				return tenant;
			}
		}
		//did not find tenant if we get to here
		System.out.println("TENANT NOT FOUND, RETURNING NULL");
		return null;
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
	
	public static String[] getDistinctTenantNames(ArrayList<SIPProjectTCurrent> list){
		String [] distinctTenants = new String[1];
		String temp = list.get(0).getTenant();
		int index = 0;
		distinctTenants[index] = temp;
		index++;
		for(int i = 1; i < list.size(); i++){
			if(!(temp.equals(list.get(i).getTenant()))){
				//increase array size, increment index of names
				distinctTenants = Arrays.copyOf(distinctTenants, ++index);
				//distinctTenants = new String[index++];
				temp = list.get(i).getTenant();
				distinctTenants[index-1] = temp;
			}
		}
		return distinctTenants;
	}
}
