package com.example.project2.Account5.dto;

import com.example.project2.Account5.domain.Account;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 컨트롤러와 서비스간의 데이터를 주고 받는 Dto
public class AccountDto {
    private Long userId;
    private String accountNumber;
    private Long balance;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder()
                .userId(account.getAccountUser().getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .registeredAt(account.getRegisteredAt())
                .unregisteredAt(account.getUnregisteredAt())
                .build();
    }
}
