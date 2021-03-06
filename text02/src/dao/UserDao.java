package dao;

import web.bean.User;
import tools.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private ConnDB conn = null;

	public UserDao() {
		conn = new ConnDB();
	}

	// 验证用户的方法，返回值为1表示登录成功，否则表示登录失败
	public int login(User user) {
		int flag = 0;
		String sql = "SELECT * FROM th_user where username='"
				+ user.getUsername() + "' and userpwd='"+user.getPwd()+"'";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			if (rs.next()) {
				String pwd = user.getPwd();// 获取密码
				int uid = rs.getInt(1);// 获取第一列的数据
				if (pwd.equals(rs.getString(2))) {
					flag = uid;
					rs.last(); // 定位到最后一条记录
					int rowSum = rs.getRow();// 获取记录总数
					rs.first();// 定位到第一条记录
					if (rowSum != 1) {
						flag = 0;
					}
				} else {
					flag = 0;
				}
			} else {
				flag = 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();// 输出异常信息
			flag = 0;
		} finally {
			conn.close();// 关闭数据库连接
		}

		return flag;

	}

	/**
	 * 功能：检测用户名是否为空
	 *
	 * @param sql
	 * @return
	 */
	public String checkUser(String sql) {
		ResultSet rs = conn.executeQuery(sql); // 执行查询语句
		String result = "";
		try {
			if (rs.next()) {
				result = "很抱歉，[" + rs.getString(1) + "]已经被注册！";
			} else {
				result = "1"; // 表示用户没有被注册
			}
		} catch (SQLException e) {
			e.printStackTrace(); // 输出异常信息
		} finally {
			conn.close(); // 关闭数据库连接
		}

		return result; // 返回判断结果

	}

	/**
	 * 功能：保存用户注册信息
	 *
	 * @param sql
	 * @return
	 */
	public String save(String sql) {
		int rtn = conn.executeUpdate(sql); // 执行更新语句
		String result = "";
		if (rtn > 0) {
			result = "用户注册成功！";
		} else {
			result = "用户注册失败！";
		}
		conn.close(); // 关闭数据库的连接
		return result; // 返回执行结果
	}

}