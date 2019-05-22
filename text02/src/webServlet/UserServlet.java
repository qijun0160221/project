package webServlet;

import dao.UserDao;
import web.bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		System.out.println("调用UserServlet");
		userDao = new UserDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);// 执行doPost()方法
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username"); // 获取用户名
		String pwd = request.getParameter("userpwd"); // 获取密码
		System.out.println("servlet"+username);
		System.out.println("servlet"+pwd);
		String action = request.getParameter("action");
		if ("login".equals(action)) { // 用户登录
			this.login(request, response);
		} else if ("exit".equals(action)) {// 用户退出
			this.exit(request, response);
		} else if ("save".equals(action)) { // 保存用户注册信息
			this.save(request, response);
		} else if ("checkUser".equals(action)) {// 检测用户名是否存在
			this.checkUser(request, response);
		}
	}

	/**
	 * 功能：用户登录
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User f = new User();
		f.setUsername(request.getParameter("username")); // 获取并设置用户名
		f.setPwd(request.getParameter("userpwd")); // 获取并设置密码
		int r = userDao.login(f);
		if (r > 0) {// 当用户登录成功时
			HttpSession session = request.getSession();
			session.setAttribute("username", f.getUsername());// 保存用户名
			request.setAttribute("returnValue", "登录成功！");// 保存提示信息
			request.getRequestDispatcher("userMessage.jsp").forward(request,
					response);// 重定向页面
		} else {// 当用户登录不成功时
			request.setAttribute("returnValue", "您输入的用户名或密码错误，请重新输入！");
			request.getRequestDispatcher("userMessage.jsp").forward(request,
					response);// 重定向页面
		}
	}

	/**
	 * 功能：用户退出
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();// 获取HttpSession的对象
		session.invalidate();// 销毁session
		request.getRequestDispatcher("userMessage.jsp")
				.forward(request, response);// 重定向页面
	}

	/**
	 * 检测用户名是否被注册
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUser(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");        //获取用户名
		String sql = "SELECT * FROM th_user WHERE username='" + username + "'";
		String result = userDao.checkUser(sql);        //调用UserDao类的checkUser()方法判断用户是否被注册
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(result);                            // 输出检测结果
		out.flush();
		out.close();

	}

	/**
	 * 保证注册的用户信息
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username"); // 获取用户名
		String pwd = request.getParameter("userpwd"); // 获取密码
		System.out.println("save"+username);
		System.out.println("save"+pwd);
		String sql = "INSERT INTO th_user (username,userpwd) VALUE ('"
				+ username
				+ "','"
				+ pwd
				+ "')";
		String result = userDao.save(sql);// 保存用户信息
		response.setContentType("text/html"); // 设置响应的类型
		PrintWriter out = response.getWriter();
		out.print(result); // 输出执行结果
		out.flush();
		out.close();// 关闭输出流对象
		response.sendRedirect("index.jsp");
	}

}