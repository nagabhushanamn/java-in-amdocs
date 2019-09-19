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
import com.example.service.TxrRequestQueue;
import com.example.service.TxrService;

@Controller
public class TxrController {

	@Autowired
	private TxrRequestQueue txRequestQueue;

	@GetMapping("/txr")
	public String txrForm() {
		return "txr-form";
	}

	@PostMapping("/txr")
	public String txr(@ModelAttribute TxrRequest request, Model model) {
		
		txRequestQueue.addTxrRequest(request);
		
		TxrResponse response = new TxrResponse();
		response.setStatus("Txr initiated successfully");
		model.addAttribute("response", response);

		return "txr-form";

	}

}
