package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TxrRequestQueue;
import com.example.service.TxrService;

@RestController
public class RestTxrController {

	@Autowired
	private TxrService txrService;

	@Autowired
	private TxrRequestQueue txRequestQueue;

	@RequestMapping(value = "/api/txr", consumes = { "application/json" }, produces = { "application/json" })
	public @ResponseBody TxrResponse txrForm(@RequestBody TxrRequest request, Model model) {

		txRequestQueue.addTxrRequest(request);

		TxrResponse response = new TxrResponse();
		response.setStatus("Txr initiated successfully");
		return response;

	}

}
