package com.java.forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
	@NotNull
	private long sender;
	@NonNull
	@NotNull
	private long receiver;
	@NonNull
	@Positive
	private long amount;
}
