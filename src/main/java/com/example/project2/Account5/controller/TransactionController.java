package com.example.project2.Account5.controller;

import com.example.project2.Account5.aop.AccountLock;
import com.example.project2.Account5.dto.CancelBalance;
import com.example.project2.Account5.dto.QueryTransactionResponse;
import com.example.project2.Account5.dto.UseBalance;
import com.example.project2.Account5.exception.AccountException;
import com.example.project2.Account5.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 잔액 관련 컨트롤러
 * 1. 잔액 사용
 * 2. 잔액 사용 취소
 * 3. 거래 확인
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("transaction/use")
    @AccountLock
    public UseBalance.Response useBalance(
            @RequestBody @Valid UseBalance.Request request
    ) throws InterruptedException {

        try {
            Thread.sleep(3000L);
            return UseBalance.Response.from(
                    transactionService.useBalance(request.getUserId(),
                            request.getAccountNumber(), request.getAmount()));
        } catch (AccountException e) {
            log.error("Failed to use balance.");

            transactionService.saveFailedUseTransaction(
                    request.getAccountNumber(),
                    request.getAmount()
            );

            throw e;
        }
    }

    @PostMapping("transaction/cancel")
    @AccountLock
    public CancelBalance.Response cancelBalance(
            @RequestBody @Valid CancelBalance.Request request
    ) {

        try {
            return CancelBalance.Response.from(
                    transactionService.cancelBalance(request.getTransactionId(),
                            request.getAccountNumber(), request.getAmount()));
        } catch (AccountException e) {
            log.error("Failed to use balance.");

            transactionService.saveFailedCancelTransaction(
                    request.getAccountNumber(),
                    request.getAmount()
            );

            throw e;
        }
    }

    @GetMapping("/transaction/{transactionId}")
    public QueryTransactionResponse queryTransactionResponse(
            @PathVariable String transactionId) {
        return QueryTransactionResponse.from(
                transactionService.queryTransaction(transactionId)
        );
    }
}