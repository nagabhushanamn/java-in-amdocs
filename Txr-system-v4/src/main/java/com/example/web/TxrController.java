package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.AccountBalanceException;
import com.example.service.TxrService;

@Controller
public class TxrController {

	@Autowired
	private TxrService txrService;

	@GetMapping("/txr")
	public String txrForm() {
		return "txr-form";
	}

	@PostMapping("/txr")
	public String txrForm(@ModelAttribute TxrRequest request, Model model) {
		TxrResponse response = new TxrResponse();
		try {
			boolean b = txrService.txr(request.getAmount(), request.getFromAccNum(), request.getToAccNum());
			response.setStatus(b ? "Txr success" : "Txr failed");
		} catch (Exception e) {
			response.setStatus(e.getMessage());
		}
		model.addAttribute("response", response);
		return "txr-form";
	}

}
