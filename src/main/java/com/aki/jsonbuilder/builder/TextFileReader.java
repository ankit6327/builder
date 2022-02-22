package com.aki.jsonbuilder.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aki.jsonbuilder.builder.model.AccountDto;
import com.aki.jsonbuilder.builder.model.SyncInput;
import com.google.gson.Gson;

public class TextFileReader {

	private static final Long SOR_ID = 7l;
	private static final List<Long> OFFER_LIST = Arrays.asList(100l, 101l);

	public static void main(String[] args) {
	
		TextFileReader fileReader = new TextFileReader();
		SyncInput input = fileReader.prepairObject();
		String gson = new Gson().toJson(input);
		System.out.println(gson);

	}
	
	
	public SyncInput prepairObject() {
		SyncInput input = new SyncInput();
		TextFileReader fileReader = new TextFileReader();

		List<AccountDto> accountDtos = fileReader.readFile();
		input.setOffers(OFFER_LIST);
		input.setAccounts(accountDtos);

		return input;
	}

	public List<AccountDto> readFile() {

		List<AccountDto> accountList = new ArrayList<>();
		File file = new File("D:\\Project\\builder\\builder\\src\\main\\resources\\Test.txt");
		BufferedReader br;
		String st;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
//				System.out.println(st);
				AccountDto accountDto = new AccountDto();
				accountDto.setSorId(SOR_ID);
				accountDto.setAccountId(st + "");
				accountList.add(accountDto);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return accountList;
	}

}
