package com.aki.jsonbuilder.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import com.aki.jsonbuilder.builder.model.AccountDto;
import com.aki.jsonbuilder.builder.model.SyncInput;
import com.google.gson.Gson;

public class JsonBuilder {

	private static final Long SOR_ID = 7l;
	private static final List<Long> OFFER_LIST = Arrays.asList(100l, 101l);

	private static final String PATH = "D:\\Project\\builder\\builder\\src\\main\\resources\\demo.ods";

	public List<AccountDto> readODS(File file) {
		List<AccountDto> accountList = new ArrayList<>();

		Sheet sheet;
		try {
			sheet = SpreadSheet.createFromFile(file).getSheet(0);
			int nColCount = sheet.getColumnCount();
			int nRowCount = sheet.getRowCount();
			MutableCell cell = null;
			for (int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++) {
				int nColIndex = 0;
				for (; nColIndex < nColCount; nColIndex++) {
					cell = sheet.getCellAt(nColIndex, nRowIndex);
					AccountDto accountDto = new AccountDto();
					accountDto.setSorId(SOR_ID);
					accountDto.setAccountId(cell.getValue() + "");
					accountList.add(accountDto);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return accountList;
	}

	private File getFile() {
		return new File(PATH);
	}

	public SyncInput prepairObject() {
		SyncInput input = new SyncInput();

		List<AccountDto> accountDtos = readODS(getFile());
		input.setOffers(OFFER_LIST);
		input.setAccounts(accountDtos);

		return input;
	}

	public static void main(String[] args) {
		File file = new File(PATH);
		if (file.exists()) {
			JsonBuilder objODSReader = new JsonBuilder();
			SyncInput input = objODSReader.prepairObject();
			String gson = new Gson().toJson(input);
			System.out.println(gson);
		} else {
			System.out.println("File not found ... ");
		}

	}
}
