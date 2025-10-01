// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.domain.model;

import io.soabase.recordbuilder.core.RecordBuilder;
import java.util.UUID;

@RecordBuilder
public record AttestationDto(UUID id, UUID hsmId, UUID wuaId) {
}

