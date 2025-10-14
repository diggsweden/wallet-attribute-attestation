// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.application.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateAttestationRequestDto(
    @NotNull(message = "HSM ID cannot be null") UUID hsmId,
    @NotNull(message = "WUA ID cannot be null") UUID wuaId,
    @NotEmpty(message = "Attestation data cannot be empty") String attestationData) {
}
