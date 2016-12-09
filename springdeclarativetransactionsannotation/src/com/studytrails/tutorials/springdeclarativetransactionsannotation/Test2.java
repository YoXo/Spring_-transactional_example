package com.studytrails.tutorials.springdeclarativetransactionsannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
	private static final String fromAccountNumber = "ACC01";
	private static final String toAccountNumber = "ACC02";

	public static void main(String[] args) {
		System.out.println("------------------BEGINNING PROGRAM-----------------------");

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		AccountService accountService = (AccountService) context.getBean("accountService");

		System.out.println("Creating new accounts " + fromAccountNumber + " and " + toAccountNumber);
		Account fromAccount = new Account(fromAccountNumber, 100d);
		Account toAccount = new Account(toAccountNumber, 100d);
		accountService.create(fromAccount);
		accountService.create(toAccount);
		printAccountInformation(accountService);
		System.out.println("New accounts created successfully");
		System.out.println("----");

		// start to transfer
		Double transferAmount1 = 50d, transferAmount2 = 40d;

		try {
			accountService.multiTransfer(fromAccount, toAccount, transferAmount1, transferAmount2);
			System.out.println("First transferred successfully");

		} catch (Exception e) {
			System.out.println("**EXCEPTION IN TRANSACTION**");
		}
		printAccountInformation(accountService);

		System.out.println("----------------- ENDING PROGRAM ---------------------");
	}

	private static void printAccountInformation(AccountService accountService) {

		Account fromAccount = accountService.getAccount(fromAccountNumber);
		Account toAccount = accountService.getAccount(toAccountNumber);
		System.out.println("Balance in account " + fromAccountNumber + " = " + fromAccount.getBalance());
		System.out.println("Balance in account " + toAccountNumber + " = " + toAccount.getBalance());
	}

}
