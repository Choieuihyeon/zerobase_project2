package com.example.project2.Account5.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 클라이언트와 어플리케이션 서로 주고받음
public class AccountInfo {
    private String accountNumber;
    private Long balance;
}
