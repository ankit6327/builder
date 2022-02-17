package com.aki.jsonbuilder.builder.model;

import java.util.List;

public class SyncInput {

	private List<Long> offers;

	private List<AccountDto> accounts;

	public List<Long> getOffers() {
		return offers;
	}

	public void setOffers(List<Long> offers) {
		this.offers = offers;
	}

	public List<AccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDto> accounts) {
		this.accounts = accounts;
	}

}
