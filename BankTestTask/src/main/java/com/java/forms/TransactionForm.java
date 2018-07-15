package com.java.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class TransactionForm {
	@NonNull
	private long sender;
	@NonNull
	private long receiver;
	@NonNull
	private int amount;
}
