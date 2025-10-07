// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.domain.model;

import java.util.List;
import java.util.UUID;

public record AttestationListDto(List<AttestationDto> attestations, UUID hsmId) {} 
