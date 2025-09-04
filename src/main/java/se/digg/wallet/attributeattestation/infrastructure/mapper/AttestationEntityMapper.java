// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.infrastructure.mapper;

import se.digg.wallet.attributeattestation.application.model.CreateAttestationRequest;
import se.digg.wallet.attributeattestation.domain.model.Attestation;
import se.digg.wallet.attributeattestation.domain.model.AttestationBuilder;
import se.digg.wallet.attributeattestation.infrastructure.model.AttestationEntity;

public final class AttestationEntityMapper {

  private AttestationEntityMapper() {}

  public static Attestation toDomain(AttestationEntity entity) {
    return AttestationBuilder.builder()
        .id(entity.getId())
        .hsmId(entity.getHsmId())
        .wuaId(entity.getWuaId())
        .build();
  }

  public static AttestationEntity toEntity(CreateAttestationRequest attestationRequest) {
    return new AttestationEntity(attestationRequest.hsmId(), attestationRequest.wuaId());
  }
}
