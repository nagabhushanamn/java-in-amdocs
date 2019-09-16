package com.example.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.repository.AccountRepository;
import com.example.repository.SQLAccountRepository;
import com.example.service.TxrService;
import com.example.service.TxrServiceImpl;

@WebServlet(urlPatterns = { "/txr" })
public class TxrController extends HttpServlet {

	private TxrService txrService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		AccountRepository accountRepository = new SQLAccountRepository();
		txrService = new TxrServiceImpl(accountRepository);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		double amount = Double.parseDouble(req.getParameter("amount"));
		String fromAccNumber = req.getParameter("fromAccNumber");
		String toAccNumber = req.getParameter("toAccNumber");

		try {
			boolean b = txrService.transfer(amount, fromAccNumber, toAccNumber);
			req.setAttribute("message", b ? "txr success" : "txr failed");
		} catch (Exception e) {
			req.setAttribute("message", e.getMessage());
		}

		req.getRequestDispatcher("txr-status.jsp").forward(req, resp);

	}

	@Override
	public void destroy() {
		txrService = null;
	}

}
